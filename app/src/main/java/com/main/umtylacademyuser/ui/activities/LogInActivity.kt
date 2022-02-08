package com.main.umtylacademyuser.ui.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.viewmodelfactories.LoginViewModelFactory
import com.main.umtylacademyuser.utils.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_register.*

class LogInActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory(application))[LoginViewModel::class.java]
        val progressDialog = ProgressDialog(this)

        btnEnterLogin.setOnClickListener {
            val email = etEmailLogin.text.toString()
            val password = etPasswordLogin.text.toString()

            if (etEmailLogin.text.isEmpty()){
                etEmailLogin.error = resources.getString(R.string.error_complete)
                return@setOnClickListener
            }

            if (etPasswordLogin.text.isEmpty()){
                etPasswordLogin.error = resources.getString(R.string.error_complete)
                return@setOnClickListener
            }

            progressDialog.show()
            progressDialog.setContentView(R.layout.progressdialog_custom)
            progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            loginViewModel.login(email, password)

//            val progressRunnable = Runnable { progressDialog.cancel() }
//
//            val pdCanceller = Handler()
//            pdCanceller.postDelayed(progressRunnable, 3000)
        }

        btnForgetPasswordLogin.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}