package com.twenty.hackthon1.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface jobDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addJob(job: Job)

    @Query("SELECT * FROM job_details ORDER BY name ASC")
    fun readAllData(): LiveData<List<Job>>

}