package com.example.summarytask12.main

import com.example.summarytask12.console.output.OutputPrinter
import com.example.summarytask12.console.service.ConsoleBookingService
import com.example.summarytask12.console.service.ConsoleRoomService
import com.example.summarytask12.data.InitialHotelData
import com.example.summarytask12.manager.HotelManager
import com.example.summarytask12.message.MessagesOutput

/**
 * Điểm chạy chương trình (console loop)
 * - Không định nghĩa các hàm trong file này
 */
fun main() {

    val hotelManager = HotelManager()
    val initialHotelData = InitialHotelData()

    // Nạp dữ liệu vào hotelManager
    initialHotelData.initializeData(hotelManager)

    val outputPrinter = OutputPrinter()

    // Khởi tạo class liên quan đến nhập/xuất
    val consoleBookingService = ConsoleBookingService(hotelManager)
    val consoleRoomService = ConsoleRoomService(hotelManager)

    while (true) {
        outputPrinter.showMenu()
        when (readLine()?.trim()) {
            "1" -> consoleRoomService.addRoom()
            "2" -> consoleRoomService.removeRoom()
            "3" -> consoleRoomService.editRoom()
            "4" -> consoleRoomService.displayAllRooms()
            "5" -> consoleRoomService.searchRoomById()
            "6" -> consoleBookingService.checkIn()
            "7" -> consoleBookingService.checkOut()
            "8" -> consoleRoomService.sortRoomsByPrice()
            "0" -> {
                println(MessagesOutput.GOODBYE)
                return
            }

            else -> println(MessagesOutput.INVALID_CHOICE)
        }
        println()
    }
}
