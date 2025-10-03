package com.example.summarytask12.manager

import com.example.summarytask12.message.MessagesOutput
import com.example.summarytask12.model.booking.Booking
import com.example.summarytask12.model.booking.BookingStatus
import com.example.summarytask12.model.payment.Payment
import com.example.summarytask12.model.payment.PaymentMethod
import com.example.summarytask12.model.Guest.Guest
import com.example.summarytask12.model.employee.Employee
import com.example.summarytask12.model.employee.EmployeeRole
import com.example.summarytask12.model.room.Room
import com.example.summarytask12.model.room.RoomStatus
import java.time.LocalDateTime
import java.util.UUID

/**
 * Lớp quản lý:
 * - Quản lý phòng (Room)
 * - Quản lý khách (Guest)
 * - Quản lý đặt phòng (Booking)
 * - Quản lý thanh toán (Payment)
 */
class HotelManager {

    // State tập trung
    private val rooms = mutableListOf<Room>()
    private val guests = mutableMapOf<String, Guest>()
    private val employees = mutableMapOf<String, Employee>()
    private val bookings = mutableMapOf<String, Booking>()
    private val payments = mutableMapOf<String, Payment>()

    // ========= Quản lý nhân viên =========
    fun hireEmployee(employee: Employee) {
        employees[employee.id] = employee
    }

    fun getEmployee(id: String): Employee? = employees[id]

    private fun ensureEmployeeAllowed(by: Employee, vararg allow: EmployeeRole) {
        require(employees.containsKey(by.id)) { MessagesOutput.PERMISSION_DENIED }
        require(allow.contains(by.role)) { MessagesOutput.PERMISSION_DENIED }
    }

    // ========= ROOM =========
    fun isRoomExists(roomId: Int): Boolean = rooms.any { it.id == roomId }

    fun addRoom(room: Room, by: Employee): Boolean {
        ensureEmployeeAllowed(by, EmployeeRole.MANAGER)
        if (rooms.any { it.id == room.id }) return false
        by.beforeAddRoom(room.id)
        rooms.add(room)
        by.afterAddRoom(room.id)
        return true
    }

    fun removeRoom(roomId: Int, by: Employee): Boolean {
        ensureEmployeeAllowed(by, EmployeeRole.MANAGER)
        // Không cho xoá phòng đang OCCUPIED hoặc có booking chưa checkout
        val activeBooking = bookings.values.any { it.roomId == roomId && it.status == BookingStatus.CHECKED_IN }
        if (activeBooking) return false
        val room = rooms.find { it.id == roomId } ?: return false
        if (room.status == RoomStatus.OCCUPIED) return false
        return rooms.remove(room)
    }

    fun updateRoom(roomId: Int, newPrice: Double?, newStatus: RoomStatus?, by: Employee): Boolean {
        ensureEmployeeAllowed(by, EmployeeRole.MANAGER)
        val room = rooms.find { it.id == roomId } ?: return false

        newPrice?.let { room.price = it }
        newStatus?.let { status ->
            // Ràng buộc đơn giản: không set AVAILABLE nếu còn booking active
            if (status == RoomStatus.AVAILABLE) {
                val hasActive = bookings.values.any { it.roomId == roomId && it.status == BookingStatus.CHECKED_IN }
                if (hasActive) return false
            }
            room.status = status
        }
        return true
    }

    fun updateRoomStatus(roomId: Int, newStatus: RoomStatus, by: Employee): Boolean {
        // Với 2 vai trò gọn: chỉ Manager được đổi trạng thái thủ công.
        ensureEmployeeAllowed(by, EmployeeRole.MANAGER)
        val room = rooms.find { it.id == roomId } ?: return false
        if (newStatus == RoomStatus.AVAILABLE) {
            val hasActive = bookings.values.any { it.roomId == roomId && it.status == BookingStatus.CHECKED_IN }
            if (hasActive) return false
        }
        room.status = newStatus
        return true
    }

    fun setMaintenance(roomId: Int, by: Employee): Boolean {
        ensureEmployeeAllowed(by, EmployeeRole.MANAGER)
        val room = rooms.find { it.id == roomId } ?: return false
        if (room.status == RoomStatus.OCCUPIED) return false
        room.status = RoomStatus.MAINTENANCE
        return true
    }

    fun getAllRooms(): List<Room> = rooms.toList()

    fun findRoomById(roomId: Int): Room? = rooms.find { it.id == roomId }

    fun sortByPrice(ascending: Boolean): List<Room> =
        rooms.sortedBy { it.price }.let { if (ascending) it else it.reversed() }

    // ========= GUEST =========
    fun getOrCreateGuest(
        guestId: String,
        fullName: String,
        phone: String,
        email: String? = null,
        idNumber: String = ""
    ): Guest {
        val existed = guests[guestId]
        if (existed != null) return existed
        val g = Guest(
            guestId = guestId,
            fullName = fullName,
            phone = phone,
            email = email,
            idNumber = idNumber
        )
        guests[guestId] = g
        return g
    }

    // ========= BOOKING / PAYMENT =========
    fun checkIn(roomId: Int, guestName: String, guestPhone: String, guestId: String, nights: Int, by: Employee): String {
        // Cho phép lễ tân hoặc manager
        ensureEmployeeAllowed(by, EmployeeRole.RECEPTIONIST, EmployeeRole.MANAGER)

        val room = rooms.find { it.id == roomId } ?: return MessagesOutput.NOT_ROOM
        if (room.status != RoomStatus.AVAILABLE) return MessagesOutput.ROOM_NOT_AVAILABLE

        // Bảo đảm guest tồn tại/được cập nhật thông tin cơ bản
        getOrCreateGuest(guestId, guestName, guestPhone)

        val bookingId = UUID.randomUUID().toString()
        val booking = Booking(
            bookingId = bookingId,
            roomId = roomId,
            guestId = guestId,
            nights = nights,
            ratePerNight = room.price,
            status = BookingStatus.CHECKED_IN
        )
        bookings[bookingId] = booking
        room.status = RoomStatus.OCCUPIED
        return MessagesOutput.CHECKIN_SUCCESSFUL
    }

    fun checkOut(roomId: Int, method: PaymentMethod, by: Employee): Pair<String, Double?> {
        ensureEmployeeAllowed(by, EmployeeRole.RECEPTIONIST, EmployeeRole.MANAGER)

        val room = rooms.find { it.id == roomId } ?: return MessagesOutput.NOT_ROOM to null
        // Tìm booking đang ở trong phòng này
        val booking = bookings.values.find { it.roomId == roomId && it.status == BookingStatus.CHECKED_IN }
            ?: return MessagesOutput.ROOM_NOT_IN_RENTAL_STATUS to null

        val amount = booking.nights * booking.ratePerNight
        val payment = Payment(
            paymentId = UUID.randomUUID().toString(),
            bookingId = booking.bookingId,
            amount = amount,
            method = method,
            paidAt = LocalDateTime.now()
        )
        payments[payment.paymentId] = payment

        booking.status = BookingStatus.CHECKED_OUT
        room.status = RoomStatus.CLEANING // sau checkout, chuyển sang dọn

        return MessagesOutput.CHECKOUT_SUCCESSFUL to amount
    }
}
