package com.main.umtylacademyuser.utils.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.main.umtylacademyuser.utils.models.Course
import com.google.firebase.database.*

class AllCourseViewModel(application: Application): AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    var lst = MutableLiveData<ArrayList<Course>>()
    var courseList = arrayListOf<Course>()
    private var mDbRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("courses")

    fun getListOfCourses(){
        mDbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                courseList.clear()
                for (postSnapshot in snapshot.children) {
                    val courses = postSnapshot.getValue(Course::class.java)

                    courseList.add(courses!!)
                    lst.value = courseList
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}