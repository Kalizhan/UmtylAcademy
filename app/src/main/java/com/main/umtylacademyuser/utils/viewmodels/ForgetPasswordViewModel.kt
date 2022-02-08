package com.main.umtylacademyuser.utils.viewmodels

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.ui.activities.LogInActivity
import com.google.firebase.auth.FirebaseAuth

class ForgetPasswordViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun sendMessage(email: String) {
        mAuth.sendPasswordResetEmail(email).addOnSuccessListener {
            Toast.makeText(context, context.resources.getString(R.string.check_inbox), Toast.LENGTH_LONG).show()
            val intent = Intent(context, LogInActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }.addOnFailureListener { e ->
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

}