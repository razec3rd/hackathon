package com.twenty.hackthon1

import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.twenty.hackthon1.databinding.ActivityRegisterPageBinding

class RegisterPage : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_page)

        binding.registerButton.setOnClickListener {
            val userEmail=binding.emailInput.text.toString()
            val userUserName = binding.userNameInputS.text.toString()
            val userPassword = binding.passwordInputS.text.toString()
            if(checkEmailDuplicate(userEmail)){
                register()
                intent= Intent(this,LoginPage::class.java)
                startActivity(intent)
                finish()
            }else{
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

                }
                binding.radioEmployer.id ->{

                }
            }
        }
    }

    private fun checkEmailDuplicate(email:String): Boolean {



        return true
    }

    fun register(){

    }
}