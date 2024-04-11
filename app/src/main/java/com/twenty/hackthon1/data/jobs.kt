package com.twenty.hackthon1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company_details")
data class Company(
    @PrimaryKey(autoGenerate = true)
    val name: String,
    val desc: String,
    val created: String
)

@Entity(tableName = "job_details")
data class Job(
    @PrimaryKey(autoGenerate = true)
    val name: String,
    val desc: String,
    val skills: String,
    val salary: Int,
    val sched: String
)