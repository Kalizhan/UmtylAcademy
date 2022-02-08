package com.main.umtylacademyuser.utils.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_table")
data class CourseRoom(
    @PrimaryKey(autoGenerate = false)
    var courseId: String = "",
    var courseAty: String? = "",
    var courseImgUri: String? = "",
    var coursePrice: String? = "",
    var courseRating: Double = 0.0
)