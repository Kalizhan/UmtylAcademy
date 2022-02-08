package com.main.umtylacademyuser.utils.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.main.umtylacademyuser.utils.models.CourseRoom

@Database(entities = [CourseRoom::class], version = 1, exportSchema = false)
abstract class CourseDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao?

    companion object{
        @Volatile
        private var INSTANCE: CourseDatabase? = null

        fun getDatabase(ctx: Context): CourseDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(ctx.applicationContext, CourseDatabase::class.java, "course_database")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}