package com.example.summarytask12.console.controller

import com.example.summarytask12.console.input.InputReader
import com.example.summarytask12.console.output.OutputPrinter
import com.example.summarytask12.manager.HotelManagerService
import com.example.summarytask12.message.MessagesInput
import com.example.summarytask12.message.MessagesOutput
import com.example.summarytask12.model.room.Room
import com.example.summarytask12.model.room.RoomStatus

class RoomConsoleController(
    private val hotelManagerService: HotelManagerService,
    private val inputReader: InputReader = InputReader(),
    private val outputPrinter: OutputPrinter = OutputPrinter()
) {

    fun addRoom() {
        inputReader.apply {
            val roomId = readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return

            if (hotelManagerService.findRoomById(roomId) != null) {
                println(MessagesOutput.ROOM_ID_ALREADY_EXISTS)
                return
            }

            val roomNumber = readNonBlank(MessagesInput.ENTER_ROOM_NUMBER)
            val price = readDoubleStrict(MessagesInput.ENTER_ROOM_PRICE) ?: return
            val capacity = readIntStrict(MessagesInput.ENTER_ROOM_CAPACITY) ?: return

            val typeText = readNonBlank(MessagesInput.ENTER_ROOM_TYPE)
            val roomType = inputReader.parseRoomType(typeText)
                ?: run { println(MessagesOutput.INVALID_INPUT); return }

            val statusText = readNonBlank(MessagesInput.ENTER_ROOM_STATUS)
            val roomStatus = inputReader.parseRoomStatus(statusText)
                ?: run { println(MessagesOutput.INVALID_INPUT); return }

            val added = hotelManagerService.addRoom(
                Room(roomId, roomNumber, price, capacity, roomType, roomStatus)
            )
            println(if (added) MessagesOutput.ROOM_ADDED_SUCCESS else MessagesOutput.ROOM_ALREADY_EXISTS)
        }
    }

    fun removeRoom() {
        val roomId = inputReader.readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
        val removed = hotelManagerService.removeRoom(roomId)
        println(if (removed) MessagesOutput.ROOM_REMOVED_SUCCESS else MessagesOutput.NOT_ROOM)
    }

    fun editRoom() {
        val roomId = inputReader.readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
        val existing = hotelManagerService.findRoomById(roomId)
            ?: run { println(MessagesOutput.NOT_ROOM); return }

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

        val updated = hotelManagerService.updateRoom(roomId, newPrice, newStatus)
        println(if (updated) MessagesOutput.UPDATE_SUCCESS else MessagesOutput.NOT_ROOM)
        outputPrinter.showRoomList(listOf(existing))
    }

    fun displayAllRooms() = outputPrinter.showRoomList(hotelManagerService.getAllRooms())

    fun searchRoomById() {
        val roomId = inputReader.readIntStrict(MessagesInput.ENTER_ROOM_ID) ?: return
        val room = hotelManagerService.findRoomById(roomId)
        if (room == null) println(MessagesOutput.NOT_ROOM) else outputPrinter.showRoomList(listOf(room))
    }

    fun sortRoomsByPrice() {
        print(MessagesInput.ENTER_SORT_ORDER)
        val ascending = when (readLine()?.trim()) {
            "1" -> true
            "2" -> false
            else -> {
                println(MessagesOutput.INVALID_CHOICE); return
            }
        }
        val sorted = hotelManagerService.sortByPrice(ascending)
        outputPrinter.showRoomList(sorted)
    }
}
