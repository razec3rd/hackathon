package com.twenty.hackthon1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.twenty.hackthon1.databinding.ActivityLoginPageBinding
import com.twenty.hackthon1.models.Post
import com.twenty.hackthon1.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class LoginPage : AppCompatActivity() {
    private lateinit var binding:ActivityLoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            intent= Intent(this,EmployerDashboard::class.java)
            startActivity(intent)
            finish()
            var emailInput=binding.userEmailInput.text.toString()
            var passInput=binding.passwordInput.text.toString()
            if(checkAccount(emailInput)){

                if(checkPassword(emailInput,passInput)){
                    intent= Intent(this,EmployerDashboard::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    binding.passwordInput.setError("Incorrect Password")
                }

            }else{
                binding.userEmailInput.setError("Account does not exist")
            }
        }

        binding.signUpText.setOnClickListener{
            intent = Intent(this,RegisterPage::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun checkPassword(email:String, pass:String):Boolean{
        val activity = this
        var emailCheck by Delegates.notNull<Boolean>()
        var passCheck by Delegates.notNull<Boolean>()
        RetrofitClient.apiService.getPostList().enqueue(object: Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val data: List<Post>? = response.body()
                    if (data != null) {
                        for (user in data){
                            val (name,password,role) = user.description.split(",")
                            emailCheck = user.name ==email
                            passCheck=password == pass
                            if(passCheck&&emailCheck){
                                break
                            }
                        }
                    }
                } else {
                    showError(response.toString())
                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                showError(t.toString())
            }
        })

        return passCheck&&emailCheck
    }

    fun checkAccount(email:String): Boolean {
        var accountExists = false
        RetrofitClient.apiService.getPostList().enqueue(object: Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val data: List<Post>? = response.body()
                    if(data != null) {
                        for (user in data){
                            if(email==user.name){
                                accountExists=true
                                break
                            }
                        }
                    }
                } else {
                    showError(response.toString())
                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                showError(t.toString())
            }
        })
        return accountExists
    }
    private fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}