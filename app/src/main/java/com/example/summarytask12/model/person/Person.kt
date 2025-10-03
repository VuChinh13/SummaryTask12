package com.example.summarytask12.model.person

abstract class Person(
    val id: String,
    val fullName: String,
    val phone: String,
    val email: String?
) {
    open fun getContactInfo(): String =
        "$fullName - $phone" + (email?.let { " - $it" } ?: "")
}
