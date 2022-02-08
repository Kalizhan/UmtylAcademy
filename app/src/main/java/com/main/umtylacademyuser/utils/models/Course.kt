package com.main.umtylacademyuser.utils.models

data class Course(
    var courseId: String? = "",
    var courseAty: String? = "",
    var courseImgUri: String? = "",
    var courseSabaqSany: Int? = 0,
    var courseTime: String? = "",
    var coursePrice: String? = "",
    var date: String? = "",
    var courseRating: Double = 0.0,
    var courseLanguage: String? = "",
    var courseDescription: String? = "",
    var courseTasks: String? = "",
    var courseVersion: String? = "",
    var courses: ArrayList<UniqueCourse>? = null
)


