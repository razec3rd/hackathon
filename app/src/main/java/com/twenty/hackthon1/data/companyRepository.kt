package com.twenty.hackthon1.data

import androidx.lifecycle.LiveData

class companyRepository(private val compDao: compDao){
    val readAllData: LiveData<List<Company>> = compDao.readAllData()

    suspend fun addComp(company: Company){
        compDao.addCompany(company)
    }
}