package com.example.summarytask12.main

import com.example.summarytask12.message.MessagesOutput
import com.example.summarytask12.utils.ConsoleHelpers

/**
 * Điểm chạy chương trình (console loop)
 * - Không định nghĩa các hàm trong file này
 * - Các hàm dùng trong file được định nghĩa trong ConsoleHelpers
 */
fun main() {
    while (true) {
        ConsoleHelpers.showMenu()
        when (readLine()?.trim()) {
            "1" -> ConsoleHelpers.addRoom()
            "2" -> ConsoleHelpers.removeRoom()
            "3" -> ConsoleHelpers.editRoom()
            "4" -> ConsoleHelpers.displayAllRooms()
            "5" -> ConsoleHelpers.searchRoomById()
            "6" -> ConsoleHelpers.checkIn()
            "7" -> ConsoleHelpers.checkOut()
            "8" -> ConsoleHelpers.sortRoomsByPrice()
            "0" -> {
                println(MessagesOutput.GOODBYE)
                return
            }

            else -> println(MessagesOutput.INVALID_CHOICE)
        }
        println()
    }
}
