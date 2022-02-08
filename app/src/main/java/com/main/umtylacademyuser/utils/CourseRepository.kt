package com.main.umtylacademyuser.utils

import androidx.lifecycle.LiveData
import com.main.umtylacademyuser.utils.data.CourseDao
import com.main.umtylacademyuser.utils.models.CourseRoom

class CourseRepository(private val courseDao: CourseDao) {

    val readAllData: LiveData<List<CourseRoom>> = courseDao.readAllData()

    suspend fun addCourse(course: CourseRoom){
        courseDao.addCourse(course)
    }

    suspend fun updateCourse(course: CourseRoom){
        courseDao.updateCourse(course)
    }

    suspend fun deleteCourse(course: CourseRoom){
        courseDao.deleteCourse(course)
    }

    suspend fun deleteAllCourse(){
        courseDao.deleteAllCourses()
    }
}