package com.example.summarytask12.model

class Guest(
    id: String,
    fullName: String,
    phone: String,
    email: String?,
    var idNumber: String
) : Person(id, fullName, phone, email) {
    override fun toString(): String = "Guest[$id] $fullName ($phone)"
}