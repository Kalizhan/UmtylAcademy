package com.main.umtylacademyuser.ui.activities

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.main.umtylacademyuser.R
import kotlinx.android.synthetic.main.activity_forget_password.*
import androidx.lifecycle.ViewModelProvider
import com.main.umtylacademyuser.utils.viewmodelfactories.ForgetPasswordViewModelFactory
import com.main.umtylacademyuser.utils.viewmodels.ForgetPasswordViewModel

class ForgetPasswordActivity : AppCompatActivity() {
    private lateinit var viewmodel: ForgetPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        viewmodel = ViewModelProvider(
            this,
            ForgetPasswordViewModelFactory(application)
        )[ForgetPasswordViewModel::class.java]

        val progressDialog = ProgressDialog(this)

        backButtonForgetPassword.setOnClickListener {
            finish()
        }

        btnEnterForgetPassword.setOnClickListener {
            if (etEmailForgetPassword.text.isEmpty()) {
                etEmailForgetPassword.error = resources.getString(R.string.error_complete)
                return@setOnClickListener
            }

            progressDialog.show()
            progressDialog.setContentView(R.layout.progressdialog_custom)
            progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            viewmodel.sendMessage(etEmailForgetPassword.text.toString())

            val progressRunnable = Runnable { progressDialog.cancel() }

            val pdCanceller = Handler()
            pdCanceller.postDelayed(progressRunnable, 3000)
        }
    }
}