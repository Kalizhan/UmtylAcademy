package com.main.umtylacademyuser.ui.activities

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.models.User
import com.main.umtylacademyuser.utils.viewmodelfactories.RegisterViewModelFactory
import com.main.umtylacademyuser.utils.viewmodels.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerViewModel: RegisterViewModel
    private val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        registerViewModel = ViewModelProvider(this, RegisterViewModelFactory(application))[RegisterViewModel::class.java]

        btnRegisterRegister.setOnClickListener {
            if (etNameRegister.text.isEmpty()){
                etNameRegister.error = resources.getString(R.string.error_complete)
                return@setOnClickListener
            }

            if (etSurnameRegister.text.isEmpty()){
                etSurnameRegister.error = resources.getString(R.string.error_complete)
                return@setOnClickListener
            }

            if (etEmailRegister.text.isEmpty()){
                etEmailRegister.error = resources.getString(R.string.error_complete)
                return@setOnClickListener
            }

            if (etPhoneRegister.text?.isEmpty() == true){
                etPhoneRegister.error = resources.getString(R.string.error_complete)
                return@setOnClickListener
            }

            if (etPasswordRegister.text.isEmpty()){
                etPasswordRegister.error = resources.getString(R.string.error_complete)
                return@setOnClickListener
            }

            val pattern = Pattern.compile(regex)

            val matcher = pattern.matcher(etEmailRegister.text)

            if ((if (matcher.matches()) "valid" else "invalid") == "invalid") {
                etEmailRegister.error = resources.getString(R.string.correct_email)
                return@setOnClickListener
            }

            val name = etNameRegister.text.toString()
            val surname = etSurnameRegister.text.toString()
            val phone = etPhoneRegister.text.toString()
            val email = etEmailRegister.text.toString()
            val password = etPasswordRegister.text.toString()

            val user = User(name = name, surname = surname, email = email, password = password, phone = phone, dostup_course = "")

            val progressDialog = ProgressDialog(this)
            progressDialog.show()
            progressDialog.setContentView(R.layout.progressdialog_custom)
            progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            registerViewModel.register(user)
        }
    }
}