package com.twenty.hackthon1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.twenty.hackthon1.databinding.ActivityRegisterPageBinding
import com.twenty.hackthon1.models.Post
import com.twenty.hackthon1.network.RetrofitClient
import kotlin.properties.Delegates

class RegisterPage : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_page)
        lateinit var role:String
        binding.registerButton.setOnClickListener {
            val userEmail=binding.emailInput.text.toString()
            val userUserName = binding.userNameInputS.text.toString()
            val userPassword = binding.passwordInputS.text.toString()
            if(!checkEmailDuplicate(userEmail)){
                Toast.makeText(this,"nice",Toast.LENGTH_SHORT).show()
                register(userEmail,userUserName,userPassword,role)
                intent= Intent(this,LoginPage::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
                binding.emailInput.setError("Email already Registered")
            }

        }
        binding.signInText.setOnClickListener {
            intent= Intent(this,LoginPage::class.java)
            startActivity(intent)
            finish()
        }
        binding.roleGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                binding.radioAplicant.id ->{
                    role="Applicant"
                }
                binding.radioEmployer.id ->{
                    role="Employer"
                }
            }
        }
    }

    private fun checkEmailDuplicate(email:String): Boolean {
            val activity = this
        var duplicateFound by Delegates.notNull<Boolean>()
        RetrofitClient.apiService.getPostList().enqueue(object: Callback<List<Post>> {
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    if (response.isSuccessful) {
                        val data: List<Post>? = response.body()
                        if(data != null) {
                            duplicateFound = data.any { it.name == email}
                        }
                    } else {
                        showError(response.toString())
                    }
                }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                showError(t.toString())
            }
        })
        return duplicateFound
    }


    fun register(email:String,name:String,pass:String,role:String){
        val details= "$name,$pass,$role"
        createPost(email,details)
    }

    private fun createPost(email:String, details:String) {
        val post = Post(
            id = "",
            name = email,
            description = details
        )
        RetrofitClient.apiService.createPost(post).enqueue(object: Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    finish()
                } else {
                    response.errorBody()?.string()?.let { showError(it) }
                }
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
                t.message?.let { showError(it) }
            }
        })
    }
    private fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}