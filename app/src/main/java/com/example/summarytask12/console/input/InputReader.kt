package com.example.summarytask12.console.input

import com.example.summarytask12.message.MessagesOutput
import com.example.summarytask12.model.payment.PaymentMethod
import com.example.summarytask12.model.room.RoomStatus
import com.example.summarytask12.model.room.RoomType

/**
 * Lớp InputReader dùng để đọc dữ liệu từ console.
 * Cung cấp các phương thức giúp nhập số nguyên, số thực, chuỗi, và các lựa chọn từ người dùng.
 * Các phương thức sẽ kiểm tra tính hợp lệ của dữ liệu nhập vào và yêu cầu người dùng nhập lại nếu dữ liệu không hợp lệ.
 */
class InputReader {
    fun readNonBlank(promptMessage: String): String {
        while (true) {
            print(promptMessage)
            val input = readLine()?.trim().orEmpty()
            if (input.isNotBlank()) return input
            println(MessagesOutput.CANNOT_BE_EMPTY)
        }
    }

    fun readDoubleOrNull(promptMessage: String): Double? {
        print(promptMessage)
        val input = readLine()?.trim()
        return input?.toDoubleOrNull()
    }

    fun readDoubleStrict(promptMessage: String): Double? {
        val value = readDoubleOrNull(promptMessage)
        if (value == null) println(MessagesOutput.ENTER_VALID_DECIMAL_NUMBER)
        return value
    }

    fun readOptionalLine(promptMessage: String): String? {
        print(promptMessage)
        val input = readLine()?.trim().orEmpty()
        return input.ifBlank { null }
    }

    fun readIntStrict(promptMessage: String): Int? {
        val value = readIntOrNull(promptMessage)
        if (value == null) println(MessagesOutput.ENTER_VALID_INTEGER_NUMBER)
        return value
    }

    fun readIntOrNull(promptMessage: String): Int? {
        print(promptMessage)
        val input = readLine()?.trim()
        return input?.toIntOrNull()
    }

    fun parseRoomType(raw: String): RoomType? = when (normalize(raw)) {
        "single" -> RoomType.SINGLE
        "double" -> RoomType.DOUBLE
        "twin" -> RoomType.TWIN
        "deluxe" -> RoomType.DELUXE
        "suite" -> RoomType.SUITE
        else -> null
    }

    fun normalize(input: String): String = input.trim().lowercase()

    fun parsePaymentMethod(raw: String): PaymentMethod? = when (normalize(raw)) {
        "cash" -> PaymentMethod.CASH
        "credit_card", "credit card" -> PaymentMethod.CREDIT_CARD
        "debit_card", "debit card" -> PaymentMethod.DEBIT_CARD
        "online" -> PaymentMethod.ONLINE
        else -> null
    }

    fun parseRoomStatus(raw: String): RoomStatus? = when (normalize(raw)) {
        "available" -> RoomStatus.AVAILABLE
        "occupied" -> RoomStatus.OCCUPIED
        "cleaning" -> RoomStatus.CLEANING
        "maintenance" -> RoomStatus.MAINTENANCE
        else -> null
    }
}
