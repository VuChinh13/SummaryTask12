package com.example.summarytask12.service

import com.example.summarytask12.message.Messages
import com.example.summarytask12.model.Booking
import com.example.summarytask12.model.Guest
import com.example.summarytask12.model.Room
import com.example.summarytask12.model.RoomStatus

object HotelManager {
    val rooms: MutableList<Room> = mutableListOf()
    val guests: MutableList<Guest> = mutableListOf()
    val bookings: MutableMap<String, Booking> = mutableMapOf() // key = roomId

    fun addRoom(room: Room): Boolean {
        if (rooms.any { it.id == room.id }) return false
        rooms.add(room); return true
    }

    fun removeRoom(id: String): Boolean {
        bookings.remove(id)
        return rooms.removeIf { it.id == id }
    }

    fun updateRoom(id: String, type: String?, price: Double?, status: RoomStatus?): Boolean {
        val r = rooms.find { it.id == id } ?: return false
        if (!type.isNullOrBlank()) r.type = type
        if (price != null) r.price = price
        if (status != null) r.status = status
        return true
    }

    fun findRoomById(id: String): Room? = rooms.find { it.id == id }

    // Tim kiem theo kieu phong
    fun searchByType(type: String): List<Room> =
        rooms.filter { it.type.equals(type, ignoreCase = true) }

    // Tim kiem theo khoang gia tri
    fun searchByPriceRange(from: Double, to: Double): List<Room> =
        rooms.filter { it.price in from..to }

    // Sap xep theo trang thai
    fun sortByPrice(ascending: Boolean): List<Room> =
        if (ascending) rooms.sortedBy { it.price } else rooms.sortedByDescending { it.price }

    // Loc phong theo trang thai
    fun filterByStatus(status: RoomStatus): List<Room> =
        rooms.filter { it.status == status }

    // Tim kiem khach hang
    fun getOrCreateGuestByName(name: String, phone: String): Guest? {
        return guests.find { it.fullName.equals(name, true) && it.phone == phone }
    }

    fun checkIn(
        roomId: String,
        guestName: String,
        guestPhone: String,
        nights: Int
    ): String {
        val room = findRoomById(roomId)
        if (room == null) return Messages.NOT_ROOM
        if (room.status != RoomStatus.AVAILABLE) return Messages.ROOM_NOT_AVAILABLE

        val guest = getOrCreateGuestByName(guestName, guestPhone)
        val booking = Booking(roomId = room.id, guestId = guest?.id ?: , nights = nights)
        bookings[room.id] = booking
        room.status = RoomStatus.OCCUPIED
        return Messages.CHECKIN_SUCCESSFUL
    }

    fun checkOut(roomId: String): String {
        val room = findRoomById(roomId)
        if (room == null) return Messages.NOT_ROOM
        val check = bookings[roomId]
        if (check == null) return Messages.ROOM_NOT_IN_RENTAL_STATUS
        val total = room.price * check.nights
        bookings.remove(roomId)
        room.status = RoomStatus.AVAILABLE
        return Messages.CHECKOUT_SUCCESSFUL
    }
}