package com.main.umtylacademyuser.utils.viewmodels

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.main.umtylacademyuser.MainActivity
import com.main.umtylacademyuser.utils.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var mDbReference: DatabaseReference

    fun register(user: User) {
        mAuth.createUserWithEmailAndPassword(user.email!!, user.password!!)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    addUserToDatabase(user)
                } else {
                    Log.i("AAA", task.exception.toString())

                }
            }
    }

    private fun addUserToDatabase(user: User) {
        var emailPath: String = user.email.replace(".", "-")
        Log.i("AAA", user.toString())
        mDbReference = FirebaseDatabase.getInstance().reference

        mDbReference.child("users").child("clients").child(emailPath).setValue(user).addOnCompleteListener {
            if (it.isSuccessful){
                val intent = Intent(context, MainActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
                Toast.makeText(
                    context,
                    "Здраствуйте, ${user.name}",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                Log.i("AAA", it.exception.toString())
            }
        }
    }
}