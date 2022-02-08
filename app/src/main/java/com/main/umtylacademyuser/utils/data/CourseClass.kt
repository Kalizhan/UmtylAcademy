package com.main.umtylacademyuser.utils.data

import android.app.Application
import androidx.room.Room


object CourseClass : Application() {

    private var instance: CourseClass? = null

    private var database: CourseDatabase? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, CourseDatabase::class.java, "database")
            .build()
    }

    fun getInstance1(): CourseClass? {
        return instance
    }

    fun getDatabase(): CourseDatabase? {
        return database
    }
}