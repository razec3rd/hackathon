package com.twenty.hackthon1.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface compDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCompany(company: Company)

    @Query("SELECT * FROM company_details ORDER BY name ASC")
    fun readAllData(): LiveData<List<Company>>

}