package com.main.umtylacademyuser.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.main.umtylacademyuser.MainActivity
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.CheckInternetStatus
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        checkInternet()

        mAuth = FirebaseAuth.getInstance()
        checkUser()

        btnEnterLoginPage.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        btnRegistrationPage.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkInternet() {
        val mCheckInternetStatus = CheckInternetStatus()
        val is_internet_connected: Boolean = mCheckInternetStatus.isOnline(this)
        if (!is_internet_connected) {
            Toast.makeText(this, resources.getString(R.string.check_internet), Toast.LENGTH_SHORT).show()
            return
        }
    }

    private fun checkUser() {
        val currentUser = mAuth.currentUser

        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}