package com.twenty.hackthon1.data

import androidx.lifecycle.LiveData

class jobRepository(private val jobDao: jobDao){
    val readAllData: LiveData<List<Job>> = jobDao.readAllData()

    suspend fun addJob(job: Job){
        jobDao.addJob(job)
    }
}