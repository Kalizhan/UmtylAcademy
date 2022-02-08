package com.main.umtylacademyuser.utils.viewmodels

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.main.umtylacademyuser.MainActivity
import com.main.umtylacademyuser.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(application: Application): AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun login(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    val intent = Intent(context, MainActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(intent)
                } else {
                    val text: String = context.resources.getString(R.string.error_user)
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                }
            }
    }
}