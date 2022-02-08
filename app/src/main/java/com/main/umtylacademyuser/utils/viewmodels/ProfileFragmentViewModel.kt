package com.main.umtylacademyuser.utils.viewmodels

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import com.main.umtylacademyuser.ui.activities.StartActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfileFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val mDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")

    fun startProfile(emailPath: String){
//        mDatabaseReference.child("clients").child(emailPath).addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    val user = snapshot.getValue(User::class.java)
//                    if (!user.getImgUri().isEmpty()) {
//                        Glide
//                            .with(context)
//                            .load(user?.imgUri)
//                            .centerCrop()
//                            .placeholder()
//                            .into(imgView)
//                    }
//                } else {
//                    Toast.makeText(
//                        getApplicationContext(),
//                        "Бұл қолданушы туралы инфо жоқ",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(this@ProfileActivity, error.message, Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    fun logout(){
        mAuth.signOut()
        val intent = Intent(context, StartActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

}