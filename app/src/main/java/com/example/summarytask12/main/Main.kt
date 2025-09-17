package com.example.summarytask12.main

import com.example.summarytask12.model.Room
import com.example.summarytask12.service.HotelManager


// Hàm main chỉ để chạy không khai báo hàm nào bên trong
fun main() {
    // Thêm dữ liệu ban đầu
    HotelManager.addRoom(Room.fromInput(101, "Single", 300_000.0, "Trong"))
    HotelManager.addRoom(Room.fromInput(102, "Double", 450_000.0, "Trong"))
    HotelManager.addRoom(Room.fromInput(201, "VIP", 900_000.0, "Dang thue"))

    while (true) {

        /// Hiển thị Menu

        val choice = readlnOrNull()?.toIntOrNull() ?: -1
        when (choice) {

            // Chon cac chuc nang
            // Them phong
            1 -> {

            }

            2 -> {

            }

            3 -> {

            }

            4 -> {

            }

            5 -> {

            }

            6 -> {

            }

            7 -> {

            }

            8 -> {

            }

            9 -> {

            }

            0 -> {

            }

            else -> println(" Lựa chọn không hợp lệ.")
        }
        println()
    }
}
