package com.main.umtylacademyuser.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.ui.fragments.FirstChangeProfileFragment

class ChangeProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_profile)

        supportFragmentManager.beginTransaction().replace(R.id.nav_container, FirstChangeProfileFragment()).commit()
//        val mFragmentManager = supportFragmentManager
//        val mFragmentTransaction = mFragmentManager.beginTransaction()
//        val mFragment = FirstChangeProfileFragment()

//        val userName = intent.getStringExtra("userNameToPass").toString()
//        val userSurname = intent.getStringExtra("userSurnameToPass").toString()
//        val userEmail = intent.getStringExtra("userEmailToPass").toString()
//        val userPhone = intent.getStringExtra("userPhoneToPass").toString()

//        Log.i("AAA", "$userName//$userSurname//$userEmail")

//        val bundle = Bundle()
//        bundle.putString("userNameToPass", userName)
//        bundle.putString("userSurnameToPass", userSurname)
//        bundle.putString("userEmailToPass", userEmail)
//        bundle.putString("userPhoneToPass", userPhone)
//        val fragment = FirstChangeProfileFragment()
//        mFragment.arguments = bundle
//
//        mFragmentTransaction.add(R.id.nav_container, mFragment).commit()

    }
}