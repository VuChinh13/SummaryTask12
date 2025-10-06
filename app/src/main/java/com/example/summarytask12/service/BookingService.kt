package com.example.summarytask12.service

import com.example.summarytask12.message.MessagesOutput
import com.example.summarytask12.model.booking.Booking
import com.example.summarytask12.model.booking.BookingStatus
import com.example.summarytask12.model.payment.Payment
import com.example.summarytask12.model.payment.PaymentMethod
import com.example.summarytask12.model.room.RoomStatus
import java.util.UUID

class BookingService(
    private val roomService: RoomService,
    private val guestService: GuestService,
    private val paymentService: PaymentService
) {
    private val bookings = mutableMapOf<String, Booking>()

    fun findActiveBookingByRoom(roomId: Int): Booking? =
        bookings.values.find { it.roomId == roomId && it.status == BookingStatus.CHECKED_IN }

    fun hasActiveBooking(roomId: Int, status: BookingStatus): Boolean =
        bookings.values.any { it.roomId == roomId && it.status == status }

    fun checkIn(
        roomId: Int,
        guestId: String,
        guestName: String,
        guestPhone: String,
        nights: Int
    ): String {
        val room = roomService.findRoomById(roomId) ?: return MessagesOutput.NOT_ROOM
        if (room.status != RoomStatus.AVAILABLE) return MessagesOutput.ROOM_NOT_AVAILABLE

        // đảm bảo guest tồn tại
        guestService.getOrCreateGuest(guestId, guestName, guestPhone)

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

        // đánh dấu phòng chiếm dụng
        if (!roomService.markOccupied(roomId)) {
            bookings.remove(bookingId)
            return MessagesOutput.ROOM_NOT_AVAILABLE
        }
        return MessagesOutput.CHECKIN_SUCCESSFUL
    }

    fun checkOut(
        roomId: Int,
        method: PaymentMethod
    ): Pair<String, Double?> {
        roomService.findRoomById(roomId) ?: return MessagesOutput.NOT_ROOM to null

        val booking = findActiveBookingByRoom(roomId)
            ?: return MessagesOutput.ROOM_NOT_IN_RENTAL_STATUS to null

        val amount = booking.nights * booking.ratePerNight
        val payment: Payment = paymentService.createPayment(
            bookingId = booking.bookingId,
            amount = amount,
            method = method
        )

        booking.status = BookingStatus.CHECKED_OUT
        roomService.markAfterCheckout(roomId)

        return MessagesOutput.CHECKOUT_SUCCESSFUL to payment.amount
    }

    fun get(bookingId: String): Booking? = bookings[bookingId]
}
