package com.example.summarytask12.console.service

import com.example.summarytask12.console.input.InputReader
import com.example.summarytask12.manager.HotelManager
import com.example.summarytask12.message.MessagesInput
import com.example.summarytask12.message.MessagesOutput
import com.example.summarytask12.model.employee.Employee

class ConsoleBookingService(
    val hotelManager: HotelManager,
    private val currentEmployee: Employee
) {
    val inputReader = InputReader()

    fun checkIn() {
        inputReader.apply {
            val roomId = readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
            val guestName = readNonBlank(MessagesInput.ENTER_GUEST_NAME)
            val guestPhone = readNonBlank(MessagesInput.ENTER_GUEST_PHONE)
            val guestEmail = readOptionalLine(MessagesInput.ENTER_GUEST_EMAIL)
            val guestId = readNonBlank(MessagesInput.ENTER_GUEST_ID)
            val guestIdNumber = readOptionalLine(MessagesInput.ENTER_GUEST_ID_NUMBER)
            val nights = readIntStrict(MessagesInput.ENTER_NIGHTS) ?: return

            hotelManager.getOrCreateGuest(
                guestId = guestId,
                fullName = guestName,
                phone = guestPhone,
                email = guestEmail,
                idNumber = guestIdNumber ?: ""
            )
            val message = hotelManager.checkIn(roomId, guestName, guestPhone, guestId, nights, by = currentEmployee)
            println(message)
        }
    }

    fun checkOut() {
        val roomId = inputReader.readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
        val paymentMethod =
            inputReader.parsePaymentMethod(inputReader.readNonBlank(MessagesInput.ENTER_PAYMENT_METHOD))
                ?: run { println(MessagesOutput.INVALID_INPUT); return }
        val (message, totalAmount) = hotelManager.checkOut(roomId, paymentMethod, by = currentEmployee)
        println(message)
        totalAmount?.let { println("${MessagesOutput.TOTAL_AMOUNT_PREFIX}${"%,.0f".format(it)} VND") }
    }
}
