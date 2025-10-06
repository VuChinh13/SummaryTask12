package com.example.summarytask12.model.booking

data class Booking(
    val bookingId: String,
    val roomId: Int,
    val guestId: String,
    val nights: Int,
    val ratePerNight: Double,
    var status: BookingStatus = BookingStatus.NEW
)
