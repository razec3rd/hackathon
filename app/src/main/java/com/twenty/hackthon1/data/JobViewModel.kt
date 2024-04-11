package com.twenty.hackthon1.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JobViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData: LiveData<List<Job>>
    private val repository:  jobRepository

    init{
        val jobDao = jobDatabase.getDatabase(application).jobDao()
        repository = jobRepository(jobDao)
        readAllData = repository.readAllData
    }

    fun addJob(job: Job){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addJob(job)
        }
    }

}