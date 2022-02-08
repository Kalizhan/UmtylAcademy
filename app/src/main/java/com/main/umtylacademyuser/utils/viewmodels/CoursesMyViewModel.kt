package com.main.umtylacademyuser.utils.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.main.umtylacademyuser.utils.models.UniqueCourse
import com.google.firebase.database.*

class CoursesMyViewModel(application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private var mDbRef: DatabaseReference = FirebaseDatabase.getInstance().reference
//    private var mAuth: String =
//        FirebaseAuth.getInstance().currentUser!!.email.toString().replace(".", "-")
    var lst = MutableLiveData<ArrayList<UniqueCourse>>()
    var courseList = arrayListOf<UniqueCourse>()

    fun getList(courseId:String){
        mDbRef.child("courses").child(courseId).child("courses").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for(snapShot in p0.children){
                    val course = snapShot.getValue(UniqueCourse::class.java)

                    courseList.add(course!!)
                    lst.value = courseList
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}