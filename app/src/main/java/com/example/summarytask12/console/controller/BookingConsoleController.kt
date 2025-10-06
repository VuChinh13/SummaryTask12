package com.example.summarytask12.console.controller

import com.example.summarytask12.console.input.InputReader
import com.example.summarytask12.manager.HotelManagerService
import com.example.summarytask12.message.MessagesInput
import com.example.summarytask12.message.MessagesOutput

class BookingConsoleController(
    private val hotelManagerService: HotelManagerService,
    private val inputReader: InputReader = InputReader()
) {
    fun checkIn() {
        with(inputReader) {
            val roomId = readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
            val guestName = readNonBlank(MessagesInput.ENTER_GUEST_NAME)
            val guestPhone = readNonBlank(MessagesInput.ENTER_GUEST_PHONE)
            val guestEmail = readOptionalLine(MessagesInput.ENTER_GUEST_EMAIL)
            val guestId = readNonBlank(MessagesInput.ENTER_GUEST_ID)
            val guestIdNumber = readOptionalLine(MessagesInput.ENTER_GUEST_ID_NUMBER)
            val nights = readIntStrict(MessagesInput.ENTER_NIGHTS) ?: return

            val message = hotelManagerService.checkIn(
                roomId = roomId,
                guestName = guestName,
                guestPhone = guestPhone,
                guestId = guestId,
                nights = nights
            )
            println(message)
        }
    }

    fun checkOut() {
        val roomId = inputReader.readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
        val paymentMethod =
            inputReader.parsePaymentMethod(inputReader.readNonBlank(MessagesInput.ENTER_PAYMENT_METHOD))
                ?: run { println(MessagesOutput.INVALID_INPUT); return }

        val (message, totalAmount) = hotelManagerService.checkOut(roomId, paymentMethod)
        println(message)
        totalAmount?.let { println("${MessagesOutput.TOTAL_AMOUNT_PREFIX}${"%,.0f".format(it)} VND") }
    }
}
