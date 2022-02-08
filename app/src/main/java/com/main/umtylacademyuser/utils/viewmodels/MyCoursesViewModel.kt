package com.main.umtylacademyuser.utils.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.main.umtylacademyuser.utils.models.Course
import com.main.umtylacademyuser.utils.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MyCoursesViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    var lst = MutableLiveData<ArrayList<Course>>()
    var courseList = arrayListOf<Course>()
    private var mDbRef: DatabaseReference = FirebaseDatabase.getInstance().reference
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var listId = ""
    var lvd = MutableLiveData<String>()


    fun getDostupCourseId() {
        var email = mAuth.currentUser?.email?.replace(".", "-").toString()
        mDbRef.child("users").child("clients").child(email)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    val item = p0.getValue(User::class.java)

                    listId = item?.dostup_course.toString()
                    lvd.value = listId
                }

                override fun onCancelled(p0: DatabaseError) {

                }

            })
    }

    fun getListOfMyCourses(array: Array<String>) {
        mDbRef.child("courses").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for (element in p0.children) {
                    val item = element.getValue(Course::class.java)
                    for (con in array) {
                        var con1 = con.replace(" ", "")
                        Log.i("AAA", con1)
                        if (con1 == item!!.courseId) {
                            courseList.add(item)
                        }
                    }
                }
                lst.value = courseList
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}