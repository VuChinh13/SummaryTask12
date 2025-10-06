package com.example.summarytask12.model.employee

import com.example.summarytask12.model.person.Person
import java.time.LocalDate

class Employee(
    val id: String,
    fullName: String,
    birthDate: LocalDate? = null,
    address: String,
    gender: String,
    phone: String,
    email: String?,
    val role: EmployeeRole,
    var hireDate: LocalDate = LocalDate.now(),
) : Person(fullName, birthDate, address, gender, phone, email)
