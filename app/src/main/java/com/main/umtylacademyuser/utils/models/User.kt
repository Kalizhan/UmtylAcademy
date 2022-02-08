package com.main.umtylacademyuser.utils.models

data class User(
    var name: String = "",
    var surname: String = "",
    var phone: String = "",
    var email: String = "",
    var password: String = "",
//    var imgUri: String = "",
//    var score: String = "0",
    var dostup_course: String? = ""
)
