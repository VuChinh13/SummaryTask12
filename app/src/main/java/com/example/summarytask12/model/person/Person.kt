package com.example.summarytask12.model.person

import java.time.LocalDate

abstract class Person(
    val fullName: String,
    var birthDate: LocalDate? = null,
    var address: String,
    val gender: String,
    var phone: String,
    var email: String?
)
