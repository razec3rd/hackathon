package com.twenty.hackthon1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [Company::class], version = 1, exportSchema = false)
abstract class  companyDatabase: RoomDatabase(){

    abstract fun compDao(): compDao

    companion object{
        @Volatile
        private  var INSTANCE: companyDatabase? = null

        fun getDatabase(context: Context): companyDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized( this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    companyDatabase::class.java,
                    "company_details"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }

}