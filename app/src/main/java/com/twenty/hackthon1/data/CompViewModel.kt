package com.twenty.hackthon1.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData: LiveData<List<Company>>
    private val repository:  companyRepository

    init{
        val compDao = companyDatabase.getDatabase(application).compDao()
        repository = companyRepository(compDao)
        readAllData = repository.readAllData
    }

    fun addCompany(company: Company){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addComp(company)
        }
    }

}