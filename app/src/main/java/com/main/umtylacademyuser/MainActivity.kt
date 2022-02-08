package com.main.umtylacademyuser

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.main.umtylacademyuser.ui.activities.ProfileActivity
import com.main.umtylacademyuser.ui.fragments.*
import com.main.umtylacademyuser.utils.CheckInternetStatus
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*
import java.util.*


class MainActivity : AppCompatActivity() {
    var bottomNavigationView: BottomNavigationView? = null
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabaseReference: DatabaseReference
//    private lateinit var refStorage: FirebaseStorage
    lateinit var emailPath: String
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main)

        checkInternet()

        checkLanguage()

        supportActionBar?.hide()

        bottomNavigationView = findViewById<View>(R.id.bottomnavigationbar) as BottomNavigationView
        bottomNavigationView!!.background = null
        bottomNavigationView!!.menu.getItem(1).isEnabled = false
        supportFragmentManager.beginTransaction().replace(R.id.framecontainer, CourseFragment())
            .commit()
        bottomNavigationView!!.setOnNavigationItemSelectedListener { item ->
            var temp: Fragment? = null
            when (item.itemId) {
                R.id.mCourse -> temp = CourseFragment()
                R.id.mTest -> temp = TestFragment()
//                R.id.mGame -> temp = GameFragment()
//                R.id.mMusic -> temp = MusicFragment()
            }
            supportFragmentManager.beginTransaction().replace(R.id.framecontainer, temp!!).commit()
            true
        }

        to_profile_page.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            finish()
            startActivity(intent)
        }

        initView()
        getImageToToolbar()
    }

    private fun checkLanguage() {
        val sharedPreferences = getSharedPreferences("Language", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "ru")
        setLocate(language.toString())
    }


    private fun setLocate(lang: String){
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Language", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", lang)
        editor.apply()
    }

    private fun initView() {
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setTitle(resources.getString(R.string.loading))
        progressDialog.setMessage(resources.getString(R.string.pls_wait))
        mAuth = FirebaseAuth.getInstance()
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("users").child("clients")
        emailPath = mAuth.currentUser!!.email!!.replace(".", "-")
    }

    private fun getImageToToolbar() {
        progressDialog.show()
        mDatabaseReference.child(emailPath)
            .addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
//                        val user = snapshot.getValue(User::class.java)

//                        if (user!!.imgUri.isNotEmpty()) {
//                            Glide
//                                .with(applicationContext)
//                                .load(user.imgUri)
//                                .centerCrop()
//                                .into(to_profile_page)
//                        }
                        progressDialog.cancel()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun checkInternet() {
        val mCheckInternetStatus = CheckInternetStatus()
        val is_internet_connected: Boolean = mCheckInternetStatus.isOnline(this)
        if (!is_internet_connected) {
            Toast.makeText(this, resources.getString(R.string.check_internet), Toast.LENGTH_SHORT)
                .show()
            return
        }
    }
}