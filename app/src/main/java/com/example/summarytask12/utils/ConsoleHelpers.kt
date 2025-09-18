package com.example.summarytask12.utils

import com.example.summarytask12.message.MessagesInput
import com.example.summarytask12.message.MessagesOutput
import com.example.summarytask12.model.room.Room
import com.example.summarytask12.model.room.RoomStatus
import com.example.summarytask12.model.room.RoomType
import com.example.summarytask12.manager.HotelManager
import com.example.summarytask12.model.payment.PaymentMethod

/**
 * Lớp hỗ trợ nhập/xuất qua console
 *
 * - Hiển thị menu và danh sách phòng
 * - Đọc dữ liệu từ người dùng (số nguyên, số thực, chuỗi)
 * - Gọi các hàm trong HotelManager để xử lý nghiệp vụ
 */
object ConsoleHelpers {

    fun showMenu() {
        println(MessagesOutput.MENU)
        print(MessagesInput.ENTER_MENU_CHOICE)
    }

    private fun readIntOrNull(promptMessage: String): Int? {
        print(promptMessage)
        val input = readLine()?.trim()
        return input?.toIntOrNull()
    }

    private fun readIntStrict(promptMessage: String): Int? {
        val value = readIntOrNull(promptMessage)
        if (value == null) println(MessagesOutput.ENTER_VALID_INTEGER_NUMBER)
        return value
    }

    private fun readDoubleOrNull(promptMessage: String): Double? {
        print(promptMessage)
        val input = readLine()?.trim()
        return input?.toDoubleOrNull()
    }

    private fun readDoubleStrict(promptMessage: String): Double? {
        val value = readDoubleOrNull(promptMessage)
        if (value == null) println(MessagesOutput.ENTER_VALID_DECIMAL_NUMBER)
        return value
    }

    private fun readNonBlank(promptMessage: String): String {
        while (true) {
            print(promptMessage)
            val input = readLine()?.trim().orEmpty()
            if (input.isNotBlank()) return input
            println(MessagesOutput.CANNOT_BE_EMPTY)
        }
    }

    private fun readOptionalLine(promptMessage: String): String? {
        print(promptMessage)
        val input = readLine()?.trim().orEmpty()
        return input.ifBlank { null }
    }

    private fun showRoomList(roomList: List<Room>) {
        if (roomList.isEmpty()) {
            println(MessagesOutput.NO_DATA)
            return
        }
        println(MessagesOutput.ROOMS_HEADER)
        roomList.forEach { room ->
            val statusText = when (room.status) {
                RoomStatus.AVAILABLE -> "Trong"
                RoomStatus.OCCUPIED -> "Dang thue"
                RoomStatus.CLEANING -> "Dang don"
                RoomStatus.MAINTENANCE -> "Dang sua chua"
            }
            println(
                "${room.id.toString().padEnd(4)}| " +
                        "${room.roomNumber.padEnd(8)} | " +
                        "${"%(,.0f".format(room.price).padEnd(14)} | " +
                        "${room.capacity.toString().padEnd(8)} | " +
                        "${room.type.name.padEnd(7)} | $statusText"
            )
        }
    }

    fun addRoom() {
        val roomId = readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
        if (HotelManager.isRoomExists(roomId)) {
            println(MessagesOutput.ROOM_ID_ALREADY_EXISTS)
            return
        }
        val roomNumber = readNonBlank(MessagesInput.ENTER_ROOM_NUMBER)
        val price = readDoubleStrict(MessagesInput.ENTER_ROOM_PRICE) ?: return
        val capacity = readIntStrict(MessagesInput.ENTER_ROOM_CAPACITY) ?: return
        val typeText = readNonBlank(MessagesInput.ENTER_ROOM_TYPE)
        val roomType = parseRoomType(typeText) ?: run {
            println(MessagesOutput.INVALID_INPUT); return
        }
        val statusText = readNonBlank(MessagesInput.ENTER_ROOM_STATUS)
        val roomStatus = parseRoomStatus(statusText) ?: run {
            println(MessagesOutput.INVALID_INPUT); return
        }

        val added = HotelManager.addRoom(
            Room(
                id = roomId,
                roomNumber = roomNumber,
                price = price,
                capacity = capacity,
                type = roomType,
                status = roomStatus
            )
        )
        println(if (added) MessagesOutput.ROOM_ADDED_SUCCESS else MessagesOutput.ROOM_ALREADY_EXISTS)
    }

    fun removeRoom() {
        val roomId = readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
        val removed = HotelManager.removeRoom(roomId)
        println(if (removed) MessagesOutput.ROOM_REMOVED_SUCCESS else MessagesOutput.NOT_ROOM)
    }

    fun editRoom() {
        val roomId = readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
        val existingRoom = HotelManager.findRoomById(roomId) ?: run {
            println(MessagesOutput.NOT_ROOM); return
        }

        print(MessagesInput.ENTER_ROOM_PRICE_OPTIONAL)
        val priceInput = readLine()?.trim().orEmpty()
        val newPrice: Double? = if (priceInput.isBlank()) null else priceInput.toDoubleOrNull().also {
            if (it == null) println(MessagesOutput.ENTER_VALID_DECIMAL_NUMBER)
        }

        print(MessagesInput.ENTER_ROOM_STATUS_OPTIONAL)
        val statusInput = readLine()?.trim().orEmpty()
        val newStatus: RoomStatus? =
            if (statusInput.isBlank()) null else parseRoomStatus(statusInput).also {
                if (it == null) println(MessagesOutput.INVALID_INPUT)
            }

        val updated = HotelManager.updateRoom(roomId, newPrice, newStatus)
        println(if (updated) MessagesOutput.UPDATE_SUCCESS else MessagesOutput.NOT_ROOM)
        showRoomList(listOf(existingRoom))
    }

    fun displayAllRooms() {
        showRoomList(HotelManager.getAllRooms())
    }

    fun searchRoomById() {
        val roomId = readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
        val room = HotelManager.findRoomById(roomId)
        if (room == null) println(MessagesOutput.NOT_ROOM) else showRoomList(listOf(room))
    }

    fun checkIn() {
        val roomId = readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
        val guestName = readNonBlank(MessagesInput.ENTER_GUEST_NAME)
        val guestPhone = readNonBlank(MessagesInput.ENTER_GUEST_PHONE)
        val guestEmail = readOptionalLine(MessagesInput.ENTER_GUEST_EMAIL)
        val guestId = readNonBlank(MessagesInput.ENTER_GUEST_ID)
        val guestIdNumber = readOptionalLine(MessagesInput.ENTER_GUEST_ID_NUMBER)
        val nights = readIntStrict(MessagesInput.ENTER_NIGHTS) ?: return

        HotelManager.getOrCreateGuest(
            guestId = guestId,
            fullName = guestName,
            phone = guestPhone,
            email = guestEmail,
            idNumber = guestIdNumber ?: ""
        )
        val message = HotelManager.checkIn(roomId, guestName, guestPhone, guestId, nights)
        println(message)
    }

    fun checkOut() {
        val roomId = readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
        val paymentMethod = parsePaymentMethod(readNonBlank(MessagesInput.ENTER_PAYMENT_METHOD))
            ?: run { println(MessagesOutput.INVALID_INPUT); return }
        val (message, totalAmount) = HotelManager.checkOut(roomId, paymentMethod)
        println(message)
        totalAmount?.let { println("${MessagesOutput.TOTAL_AMOUNT_PREFIX}${"%,.0f".format(it)} VND") }
    }

    fun sortRoomsByPrice() {
        print(MessagesInput.ENTER_SORT_ORDER)
        val ascending = when (readLine()?.trim()) {
            "1" -> true
            "2" -> false
            else -> {
                println(MessagesOutput.INVALID_CHOICE)
                return
            }
        }
        val sortedRooms = HotelManager.sortByPrice(ascending)
        showRoomList(sortedRooms)
    }

    private fun parseRoomStatus(raw: String): RoomStatus? = when (normalize(raw)) {
        "trong" -> RoomStatus.AVAILABLE
        "dang thue" -> RoomStatus.OCCUPIED
        "dang don" -> RoomStatus.CLEANING
        "dang sua chua" -> RoomStatus.MAINTENANCE
        else -> null
    }

    private fun parseRoomType(raw: String): RoomType? = when (normalize(raw)) {
        "single" -> RoomType.SINGLE
        "double" -> RoomType.DOUBLE
        "twin" -> RoomType.TWIN
        "deluxe" -> RoomType.DELUXE
        "suite" -> RoomType.SUITE
        else -> null
    }

    private fun parsePaymentMethod(raw: String): PaymentMethod? = when (normalize(raw)) {
        "cash" -> PaymentMethod.CASH
        "credit_card", "credit card" -> PaymentMethod.CREDIT_CARD
        "debit_card", "debit card" -> PaymentMethod.DEBIT_CARD
        "online" -> PaymentMethod.ONLINE
        else -> null
    }

    private fun normalize(input: String): String = input.trim().lowercase()
}
