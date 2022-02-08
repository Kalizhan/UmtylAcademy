package com.main.umtylacademyuser.utils.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.CourseRepository
import com.main.umtylacademyuser.utils.data.CourseDatabase
import com.main.umtylacademyuser.utils.models.CourseRoom
import com.main.umtylacademyuser.utils.models.UniqueCourse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CourseViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    var lst = MutableLiveData<ArrayList<UniqueCourse>>()
    var courseList = arrayListOf<UniqueCourse>()
    val courses = "courses"
    private var mDbRef: DatabaseReference = FirebaseDatabase.getInstance().reference
    private var mAuth: String =
        FirebaseAuth.getInstance().currentUser!!.email.toString().replace(".", "-")
    var listDostups = arrayListOf<String>()
    var lvd = MutableLiveData<String>()
    var str: String? = ""

    val readAllData: LiveData<List<CourseRoom>>
    private val repository: CourseRepository

    init {
        val courseDao = CourseDatabase.getDatabase(
            application
        ).courseDao()
        repository = CourseRepository(courseDao!!)
        readAllData = repository.readAllData
    }

    fun addCourse(course: CourseRoom){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCourse(course)
        }
    }

    fun updateCourse(course: CourseRoom){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCourse(course)
        }
    }

    fun deleteCourse(course: CourseRoom){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCourse(course)
        }
    }

    fun deleteAllCourses(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllCourse()
        }
    }

    fun listOfCourses(id: String) {
        Log.i("AAA", "success")
        mDbRef.child(courses).child(id).child(courses).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for (snapShot in p0.children) {
                    val course = snapShot.getValue(UniqueCourse::class.java)

                    courseList.add(course!!)
                    lst.value = courseList
                    Log.i("AAA", lst.toString())
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun buyCourse() {
        mDbRef.child("users").child("clients").child(mAuth).child("dostup_course")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    lvd.value = p0.value.toString()
//                    Log.i("BBB", courseId)
////                    lvd.value = "dialog"
//                    str = p0.value as String
//                    Log.i("BBB1", str.toString())
//                    if (str!!.isEmpty()) {
//                        lvd.value = "dialog"
//                    } else {
//                        listDostups = str!!.split(" ") as ArrayList<String>
//
//                        for (element in listDostups) {
//                            var element1 = element.replace(" ", "")
//                            Log.i("BBB2", element1)
//                            if (element1 == courseId) {
//                                lvd.value = "noDialog"
//                            }
//                        }
//
//
//                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.error),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }
}