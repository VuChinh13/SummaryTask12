package com.example.summarytask12.model.booking

/**
 * Phiếu đặt
 * - bookingId: mã phiếu
 * - roomId / guestId: liên kết đến Room & Guest
 * - nights: số đêm
 * - ratePerNight: "chốt giá" tại thời điểm check-in
 * - status: trạng thái
 */
data class Booking(
    val bookingId: String,
    val roomId: Int,
    val guestId: String,
    val nights: Int,
    val ratePerNight: Double,
    var status: BookingStatus = BookingStatus.NEW
)
