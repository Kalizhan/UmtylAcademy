package com.main.umtylacademyuser.ui.activities

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.main.umtylacademyuser.MainActivity
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.adapters.CourseRoomAdapter
import com.main.umtylacademyuser.utils.models.CourseRoom
import com.main.umtylacademyuser.utils.models.User
import com.main.umtylacademyuser.utils.viewmodels.CourseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.about_us.view.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.cart_items.view.*
import kotlinx.android.synthetic.main.cart_items.view.btn245
import kotlinx.android.synthetic.main.custom_dialog.*
import java.util.*
import kotlin.collections.ArrayList


class ProfileActivity : AppCompatActivity() {
    private lateinit var viewModel: CourseViewModel
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var refStorage: FirebaseStorage
//    private val PICK_IMAGE_REQUEST = 1
    lateinit var emailPath: String
    private var imgUrl = ""
    lateinit var progressDialog: ProgressDialog
    private lateinit var display: View
    lateinit var dialog: Dialog
    var userNameToPass = ""
    var userSurnameToPass = ""
    var userEmailToPass = ""
    var userPhoneToPass = ""
    var userPasswordToPass = ""
    var userDostup_CoursesToPass = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        loadLocate()

        init()
        getData()

//        tv_logout_profile.setOnClickListener {
//            viewModel.logOut()
//        }

//        profile_image.setOnClickListener {
//            showDialog()
//        }

        btnAboutUs.setOnClickListener {
            val display2 = layoutInflater.inflate(R.layout.about_us, null)
            val dialog2 = Dialog(this, R.style.FullScreenDialog)
            window.setGravity(Gravity.NO_GRAVITY)

            display2.btn128.setOnClickListener{
                dialog2.cancel()
            }

            dialog2.setContentView(display2)
            dialog2.show()
        }

        changeProfile.setOnClickListener {
            val sharedPreferences = getSharedPreferences("profileData", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply{
                putString("userNameToPass", userNameToPass)
                putString("userSurnameToPass", userSurnameToPass)
                putString("userEmailToPass", userEmailToPass)
                putString("userPhoneToPass", userPhoneToPass)
                putString("userPasswordToPass", userPasswordToPass)
                putString("userDostup_CoursesToPass", userDostup_CoursesToPass)
            }.apply()


            val intent = Intent(this, ChangeProfileActivity::class.java)
            startActivity(intent)
        }

        changeLangBtn.setOnClickListener {
            val listItems = arrayOf("Русский", "Қазақша")

            val mBuilder = AlertDialog.Builder(this)
            mBuilder.setTitle("Выберите язык")
            mBuilder.setSingleChoiceItems(listItems, -1) {dialog, which ->
                if (which == 0){
                    setLocate("ru")
                    recreate()
                }else if (which == 1){
                    setLocate("kk")
                    recreate()
                }

                dialog.dismiss()
            }

            val mDialog = mBuilder.create()
            mDialog.show()
        }


        backBtn.setOnClickListener {
            toGoBack()
        }
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

    private fun loadLocate(){
        val sharedPreferences = getSharedPreferences("Language", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", null)
        setLocate(language.toString())
    }

    private fun init() {
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setTitle(resources.getString(R.string.loading))
        progressDialog.setMessage(resources.getString(R.string.pls_wait))

        mAuth = FirebaseAuth.getInstance()
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("users").child("clients")
        refStorage = FirebaseStorage.getInstance()
        emailPath = mAuth.currentUser!!.email!!.replace(".", "-")
        imgUrl = mDatabaseReference.child(emailPath).child("imgUri").toString()


        viewModel = ViewModelProvider(this)[CourseViewModel::class.java]
    }

    private fun getData() {
        progressDialog.show()
        mDatabaseReference.child(emailPath)
            .addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val user = snapshot.getValue(User::class.java)
                        userNameToPass = user?.name.toString()
                        userSurnameToPass = user?.surname.toString()
                        userEmailToPass = user?.email.toString()
                        userPhoneToPass = user?.phone.toString()
                        userPasswordToPass = user?.password.toString()
                        userDostup_CoursesToPass = user?.dostup_course.toString()
                        tv_fullname_profile.text = "$userNameToPass $userSurnameToPass"
                        tv_email_profile.text = userEmailToPass

//                        if (user?.imgUri?.isNotEmpty() == true) {
//                            Glide
//                                .with(applicationContext)
//                                .load(user.imgUri)
//                                .centerCrop()
//                                .into(profile_image)
//
//                            imgUrl = user.imgUri
//                        }
                        progressDialog.cancel()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@ProfileActivity, error.message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun toGoBack() {
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
    }

//    private fun showDialog() {
//        val dialog = Dialog(this)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.custom_dialog)
//
//        dialog.newPhotoLayout.setOnClickListener {
//            openFileChooser()
//        }
//
//        dialog.removePhotoLayout.setOnClickListener {
//            removePhotoMethod()
//        }
//
//        dialog.show()
//        dialog.window?.setLayout(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
//        dialog.window?.setGravity(Gravity.BOTTOM)
//    }

//    private fun removePhotoMethod() {
//        progressDialog.show()
//        if (imgUrl.isNotEmpty()) {
//            refStorage.getReferenceFromUrl(imgUrl).delete()
//                .addOnSuccessListener { // File deleted successfully
//                    Log.i("AAA", imgUrl)
//                    mDatabaseReference.child(emailPath).child("imgUri").setValue("")
//                    Glide
//                        .with(this@ProfileActivity)
//                        .load(R.drawable.user)
//                        .centerCrop()
//                        .into(profile_image)
//                    progressDialog.cancel()
//                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
//                }.addOnFailureListener { // Uh-oh, an error occurred!
//                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
//                }
//        } else {
//            progressDialog.cancel()
//            Toast.makeText(this, "First upload new photo", Toast.LENGTH_SHORT).show()
//        }
//    }

//    private fun openFileChooser() {
//        val intent = Intent()
//        intent.type = "image/*"
//        intent.action = Intent.ACTION_GET_CONTENT
//        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), 1)
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == PICK_IMAGE_REQUEST
//            && resultCode == Activity.RESULT_OK
//            && data != null
//            && data.data != null
//        ) {
//            val file_uri = data.data
//            if (file_uri != null) {
//                uploadImageToFirebase(file_uri)
//            }
//        }
//    }

//    private fun uploadImageToFirebase(fileUri: Uri) {
//        progressDialog.show()
//        val fileName = UUID.randomUUID().toString() + ".jpg"
//
//        refStorage.reference.child("UserImage/$fileName").putFile(fileUri)
//            .addOnSuccessListener { taskSnapshot ->
//                taskSnapshot.storage.downloadUrl.addOnSuccessListener {
//                    val imageUrl = it.toString()
//                    mDatabaseReference.child(emailPath).child("imgUri").setValue(imageUrl)
//                    progressDialog.cancel()
//                    Toast.makeText(this, "New image uploaded", Toast.LENGTH_SHORT).show()
//                }
//            }
//            .addOnFailureListener { e ->
//                print(e.message)
//            }
//    }

    fun goToCart(view: View) {

//        val db: CourseDatabase? = CourseClass.getInstance1()!!.getDatabase()
//        val courseDao: CourseDao? = db!!.courseDao()

//        val courses: LiveData<List<CourseRoom>> = courseDao!!.readAllData()
        display = layoutInflater.inflate(R.layout.cart_items, null)
        dialog = Dialog(this, R.style.FullScreenDialog)
        window.setGravity(Gravity.NO_GRAVITY)

        display.recyclerCartItem.layoutManager = LinearLayoutManager(this)
        viewModel.readAllData.observe(this, androidx.lifecycle.Observer {course ->
            display.recyclerCartItem.adapter = CourseRoomAdapter(course as ArrayList<CourseRoom>, this)
        })
        display.btn245.setOnClickListener{
            dialog.cancel()
        }
        display.btnDelete.setOnClickListener {
            viewModel.deleteAllCourses()
            display.recyclerCartItem.adapter?.notifyDataSetChanged()
        }

        dialog.setContentView(display)
        dialog.show()
    }
}