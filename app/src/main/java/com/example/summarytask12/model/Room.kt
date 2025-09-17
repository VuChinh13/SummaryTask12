package com.example.summarytask12.model

import java.util.Locale

data class Room(
    val id: String,
    var type: String,
    var price: Double,
    var status: RoomStatus = RoomStatus.AVAILABLE
) {

    // Nguoi dung nhap
    companion object {
        fun fromInput(idInt: Int, type: String, price: Double, statusRaw: String): Room {
            val status = when (statusRaw.trim().lowercase(Locale.getDefault())) {
                "trong" -> RoomStatus.AVAILABLE
                "dang thue" -> RoomStatus.OCCUPIED
                "dang don" -> RoomStatus.CLEANING
                "sua chua" -> RoomStatus.MAINTENANCE
                else -> RoomStatus.AVAILABLE
            }
            return Room(idInt.toString(), type.trim(), price, status)
        }
    }
}