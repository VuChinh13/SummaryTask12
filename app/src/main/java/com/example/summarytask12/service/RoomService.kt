package com.example.summarytask12.service

import com.example.summarytask12.model.room.Room
import com.example.summarytask12.model.room.RoomStatus
import com.example.summarytask12.model.booking.BookingStatus

class RoomService {
    private val rooms = mutableListOf<Room>()

    fun addRoom(room: Room): Boolean {
        if (rooms.any { it.id == room.id }) return false
        rooms.add(room)
        return true
    }

    fun removeRoom(
        roomId: Int,
        hasActiveBooking: (roomId: Int, activeStatus: BookingStatus) -> Boolean
    ): Boolean {
        val room = rooms.find { it.id == roomId } ?: return false
        if (room.status == RoomStatus.OCCUPIED) return false
        if (hasActiveBooking(roomId, BookingStatus.CHECKED_IN)) return false
        return rooms.remove(room)
    }

    fun updateRoom(
        roomId: Int,
        newPrice: Double? = null,
        newStatus: RoomStatus? = null,
        hasActiveBooking: (roomId: Int, activeStatus: BookingStatus) -> Boolean
    ): Boolean {
        val room = rooms.find { it.id == roomId } ?: return false
        newPrice?.let { room.price = it }
        newStatus?.let { status ->
            if (status == RoomStatus.AVAILABLE &&
                hasActiveBooking(roomId, BookingStatus.CHECKED_IN)
            ) return false
            room.status = status
        }
        return true
    }

    fun getAllRooms(): List<Room> = rooms.toList()

    fun findRoomById(roomId: Int): Room? = rooms.find { it.id == roomId }

    fun sortByPrice(ascending: Boolean): List<Room> =
        rooms.sortedBy { it.price }.let { if (ascending) it else it.reversed() }

    // Hỗ trợ BookingService cập nhật trạng thái phòng
    fun markOccupied(roomId: Int): Boolean {
        val room = rooms.find { it.id == roomId } ?: return false
        if (room.status != RoomStatus.AVAILABLE) return false
        room.status = RoomStatus.OCCUPIED
        return true
    }

    fun markAfterCheckout(roomId: Int): Boolean {
        val room = rooms.find { it.id == roomId } ?: return false
        room.status = RoomStatus.CLEANING
        return true
    }
}
