package com.example.summarytask12.model.guest

import com.example.summarytask12.model.person.Person
import java.time.LocalDate

class Guest(
    val guestId: String,
    fullName: String,
    birthDate: LocalDate? = null,
    address: String,
    gender: String,
    phone: String,
    email: String?,
    var idNumber: String,
    var loyaltyPoints: Int = 0
) : Person(
    fullName = fullName,
    birthDate = birthDate,
    address = address,
    gender = gender,
    phone = phone,
    email = email
)
