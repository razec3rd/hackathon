package com.twenty.hackthon1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [Job::class], version = 1, exportSchema = false)
abstract class  jobDatabase: RoomDatabase(){

    abstract fun jobDao(): compDao

    companion object{
        @Volatile
        private  var INSTANCE: jobDatabase? = null

        fun getDatabase(context: Context): jobDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized( this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    jobDatabase::class.java,
                    "job_details"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }

}