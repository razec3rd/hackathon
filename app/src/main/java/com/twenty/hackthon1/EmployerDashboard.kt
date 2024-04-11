package com.twenty.hackthon1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.twenty.hackthon1.databinding.ActivityEmployerDashboardBinding

class EmployerDashboard : AppCompatActivity() {
    private lateinit var binding: ActivityEmployerDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_employer_dashboard)

        val logout: Button = findViewById(R.id.logout)
        val postJob: Button = findViewById(R.id.postjobs)
        val home: Button = findViewById(R.id.home)
        val profile: Button = findViewById(R.id.profile)

        home.isEnabled = false

        logout.setOnClickListener(){
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        postJob.setOnClickListener(){
            intent = Intent(this, EmployerPostAJob::class.java)
            startActivity(intent)
        }


        profile.setOnClickListener(){
            intent = Intent(this, EmployerProfile::class.java)
            startActivity(intent)
        }
    }
}