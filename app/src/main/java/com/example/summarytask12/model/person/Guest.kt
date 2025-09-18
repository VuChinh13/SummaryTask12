package com.example.summarytask12.model.person

import java.time.LocalDate

/**
 * Khách lưu trú
 * - guestId: định danh duy nhất, dùng để liên kết Booking
 * - idNumber: số giấy tờ tùy thân
 * - email/address/birthDate: optional
 * - loyaltyPoints: điểm tích lũy
 */
class Guest(
    val guestId: String,
    fullName: String,
    phone: String,
    val email: String?,
    var idNumber: String,
    var address: String? = null,
    var birthDate: LocalDate? = null,
    var loyaltyPoints: Int = 0
) : Person(fullName, phone)
