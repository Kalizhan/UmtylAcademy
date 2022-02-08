package com.main.umtylacademyuser.utils.viewmodels

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import com.main.umtylacademyuser.ui.activities.StartActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel(application: Application): AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    fun logOut(){
        mAuth.signOut()
        val intent = Intent(context, StartActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

}