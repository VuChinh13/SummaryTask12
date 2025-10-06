package com.example.summarytask12.service

import com.example.summarytask12.model.guest.Guest

class GuestService {
    private val guests = mutableMapOf<String, Guest>()

    fun get(guestId: String): Guest? = guests[guestId]

    fun getOrCreateGuest(
        guestId: String,
        fullName: String,
        phone: String,
        email: String? = null,
        idNumber: String = ""
    ): Guest {
        guests[guestId]?.let { return it }
        val guest = Guest(
            guestId = guestId,
            fullName = fullName,
            address = "",
            gender = "",
            phone = phone,
            email = email,
            idNumber = idNumber
        )
        guests[guestId] = guest
        return guest
    }
}
