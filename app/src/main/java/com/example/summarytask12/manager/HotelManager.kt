package com.example.summarytask12.manager

import com.example.summarytask12.message.MessagesOutput
import com.example.summarytask12.model.booking.Booking
import com.example.summarytask12.model.booking.BookingStatus
import com.example.summarytask12.model.payment.Payment
import com.example.summarytask12.model.payment.PaymentMethod
import com.example.summarytask12.model.person.Guest
import com.example.summarytask12.model.room.Room
import com.example.summarytask12.model.room.RoomStatus
import java.util.UUID

/**
 * Lớp quản lý:
 * - Quản lý phòng (Room)
 * - Quản lý khách (Guest)
 * - Quản lý đặt phòng (Booking)
 * - Quản lý thanh toán (Payment)
 */
object HotelManager {

    private val roomList: MutableList<Room> = mutableListOf()
    private val guestList: MutableList<Guest> = mutableListOf()
    private val bookingByRoomId: MutableMap<Int, Booking> = mutableMapOf()
    private val paymentByBookingId: MutableMap<String, Payment> = mutableMapOf()

    // ROOM
    fun addRoom(room: Room): Boolean {
        if (roomList.any { it.id == room.id }) return false
        roomList.add(room)
        return true
    }

    fun removeRoom(roomId: Int): Boolean {
        val booking = bookingByRoomId[roomId]
        if (booking != null && booking.status == BookingStatus.CHECKED_IN) {
            // Không cho xóa phòng đang có khách
            return false
        }
        bookingByRoomId.remove(roomId)
        return roomList.removeIf { it.id == roomId }
    }

    fun updateRoom(roomId: Int, price: Double?, status: RoomStatus?): Boolean {
        val room = roomList.find { it.id == roomId } ?: return false
        price?.let { room.price = it }
        status?.let { room.status = it }
        return true
    }

    fun getAllRooms(): List<Room> = roomList.toList()

    fun findRoomById(roomId: Int): Room? = roomList.find { it.id == roomId }

    fun sortByPrice(ascending: Boolean): List<Room> =
        if (ascending) roomList.sortedBy { it.price } else roomList.sortedByDescending { it.price }

    fun isRoomExists(roomId: Int): Boolean = roomList.any { it.id == roomId }

    // GUEST
    private fun findGuestByNameAndPhone(fullName: String, phone: String): Guest? =
        guestList.find { it.fullName.equals(fullName, ignoreCase = true) && it.phone == phone }

    fun getOrCreateGuest(
        guestId: String,
        fullName: String,
        phone: String,
        email: String? = null,
        idNumber: String = ""
    ): Guest {
        val existing = findGuestByNameAndPhone(fullName, phone)
        if (existing != null) return existing

        val newGuest = Guest(
            guestId = guestId,
            fullName = fullName,
            phone = phone,
            email = email,
            idNumber = idNumber
        )
        guestList.add(newGuest)
        return newGuest
    }

    // BOOKING
    fun checkIn(
        roomId: Int,
        guestName: String,
        guestPhone: String,
        guestId: String,
        nights: Int
    ): String {
        val room = findRoomById(roomId) ?: return MessagesOutput.NOT_ROOM
        if (room.status != RoomStatus.AVAILABLE) return MessagesOutput.ROOM_NOT_AVAILABLE
        if (nights <= 0) return MessagesOutput.INVALID_INPUT

        val guest = getOrCreateGuest(guestId, guestName, guestPhone)

        val booking = Booking(
            bookingId = UUID.randomUUID().toString(),
            roomId = room.id,
            guestId = guest.guestId,
            nights = nights,
            ratePerNight = room.price,
            status = BookingStatus.CHECKED_IN
        )
        bookingByRoomId[room.id] = booking
        room.status = RoomStatus.OCCUPIED
        return MessagesOutput.CHECKIN_SUCCESSFUL
    }

    fun checkOut(roomId: Int, method: PaymentMethod): Pair<String, Double?> {
        val room = findRoomById(roomId) ?: return MessagesOutput.NOT_ROOM to null
        val booking =
            bookingByRoomId[roomId] ?: return MessagesOutput.ROOM_NOT_IN_RENTAL_STATUS to null

        // Tính tiền dựa trên giá đã chốt lúc check-in
        val totalAmount = booking.ratePerNight * booking.nights

        // Tạo hóa đơn
        val payment = Payment(
            paymentId = UUID.randomUUID().toString(),
            bookingId = booking.bookingId,
            amount = totalAmount,
            method = method
        )
        paymentByBookingId[booking.bookingId] = payment

        // Cập nhật trạng thái
        booking.status = BookingStatus.CHECKED_OUT
        bookingByRoomId.remove(roomId)
        room.status = RoomStatus.AVAILABLE

        // Cộng điểm khách hàng (1 điểm / 100k VND)
        val guest = guestList.find { it.guestId == booking.guestId }
        guest?.let { it.loyaltyPoints += (totalAmount / 100_000).toInt() }

        return MessagesOutput.CHECKOUT_SUCCESSFUL to totalAmount
    }
}
