package com.main.umtylacademyuser.utils.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.main.umtylacademyuser.utils.models.CourseRoom

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCourse(course: CourseRoom)

    @Update
    suspend fun updateCourse(course: CourseRoom)

    @Delete
    suspend fun deleteCourse(course: CourseRoom)

    @Query("DELETE FROM course_table")
    suspend fun deleteAllCourses()

    @Query("SELECT * FROM course_table ORDER BY courseId ASC")
    fun readAllData(): LiveData<List<CourseRoom>>

}