package com.example.summarytask12.model.Guest

import java.time.LocalDate

/**
 * Khách lưu trú
 * - guestId: định danh duy nhất, dùng để liên kết Booking
 * - idNumber: số giấy tờ tùy thân
 * - loyaltyPoints: điểm tích lũy
 */
class Guest(
    val guestId: String,
    val fullName: String,
    val phone: String,
    val email: String?,
    var idNumber: String,
    var address: String? = null,
    var birthDate: LocalDate? = null,
    var loyaltyPoints: Int = 0
)
