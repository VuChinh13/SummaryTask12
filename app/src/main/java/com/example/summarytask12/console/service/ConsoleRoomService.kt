package com.example.summarytask12.console.service

import com.example.summarytask12.console.input.InputReader
import com.example.summarytask12.console.output.OutputPrinter
import com.example.summarytask12.manager.HotelManager
import com.example.summarytask12.message.MessagesInput
import com.example.summarytask12.message.MessagesOutput
import com.example.summarytask12.model.room.Room
import com.example.summarytask12.model.room.RoomStatus

/**
 * Xử lý nghiệp vụ liên quan đến phòng (qua console)
 */
class ConsoleRoomService(private val hotelManager: HotelManager) {

    val inputReader = InputReader()
    val outputPrinter = OutputPrinter()

    fun addRoom() {
        inputReader.apply {
            val roomId = readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
            if (hotelManager.isRoomExists(roomId)) {
                println(MessagesOutput.ROOM_ID_ALREADY_EXISTS)
                return
            }
            val roomNumber = readNonBlank(MessagesInput.ENTER_ROOM_NUMBER)
            val price = readDoubleStrict(MessagesInput.ENTER_ROOM_PRICE) ?: return
            val capacity = readIntStrict(MessagesInput.ENTER_ROOM_CAPACITY) ?: return
            val typeText = readNonBlank(MessagesInput.ENTER_ROOM_TYPE)
            val roomType = parseRoomType(typeText) ?: run {
                println(MessagesOutput.INVALID_INPUT)
                return
            }
            val statusText = readNonBlank(MessagesInput.ENTER_ROOM_STATUS)
            val roomStatus = parseRoomStatus(statusText) ?: run {
                println(MessagesOutput.INVALID_INPUT)
                return
            }

            val added = hotelManager.addRoom(
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
    }

    fun removeRoom() {
        val roomId = inputReader.readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
        val removed = hotelManager.removeRoom(roomId)
        println(if (removed) MessagesOutput.ROOM_REMOVED_SUCCESS else MessagesOutput.NOT_ROOM)
    }

    fun editRoom() {
        val roomId = inputReader.readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
        val existingRoom = hotelManager.findRoomById(roomId) ?: run {
            println(MessagesOutput.NOT_ROOM)
            return
        }

        print(MessagesInput.ENTER_ROOM_PRICE_OPTIONAL)
        val priceInput = readLine()?.trim().orEmpty()
        val newPrice: Double? =
            if (priceInput.isBlank()) null else priceInput.toDoubleOrNull().also {
                if (it == null) println(MessagesOutput.ENTER_VALID_DECIMAL_NUMBER)
            }

        print(MessagesInput.ENTER_ROOM_STATUS_OPTIONAL)
        val statusInput = readLine()?.trim().orEmpty()
        val newStatus: RoomStatus? =
            if (statusInput.isBlank()) null else inputReader.parseRoomStatus(statusInput).also {
                if (it == null) println(MessagesOutput.INVALID_INPUT)
            }

        val updated = hotelManager.updateRoom(roomId, newPrice, newStatus)
        println(if (updated) MessagesOutput.UPDATE_SUCCESS else MessagesOutput.NOT_ROOM)
        outputPrinter.showRoomList(listOf(existingRoom))
    }

    fun displayAllRooms() {
        outputPrinter.showRoomList(hotelManager.getAllRooms())
    }

    fun searchRoomById() {
        val roomId = inputReader.readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
        val room = hotelManager.findRoomById(roomId)
        if (room == null) println(MessagesOutput.NOT_ROOM) else outputPrinter.showRoomList(
            listOf(
                room
            )
        )
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
        val sortedRooms = hotelManager.sortByPrice(ascending)
        outputPrinter.showRoomList(sortedRooms)
    }
}