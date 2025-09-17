package com.example.summarytask12.utils

import com.example.summarytask12.model.Room
import com.example.summarytask12.model.RoomStatus
import kotlin.collections.forEach

object ConsoleHelpers {


    fun readInt(prompt: String): Int? {
        print(prompt)
        val input = readLine()?.trim()
        return input?.toIntOrNull().also { if (it == null) println("Nhập số nguyên hợp lệ!") }
    }

    private fun readDouble(prompt: String): Double? {
        print(prompt)
        val input = readLine()?.trim()
        return input?.toDoubleOrNull().also { if (it == null) println(" Nhập số thực hợp lệ!") }
    }

    private fun readNonBlank(prompt: String): String {
        while (true) {
            print(prompt)
            val input = readLine()?.trim().orEmpty()
            if (input.isNotBlank()) return input
            println(" Không được để trống.")
        }
    }

    private fun showRooms(list: List<Room>) {
        if (list.isEmpty()) {
            println("Không có dữ liệu.")
            return
        }
        println("ID  | Loại       | Giá (VND)      | Trạng thái")
        list.forEach {
            val statusRoom = when (it.status) {
                RoomStatus.AVAILABLE -> "Trong"
                RoomStatus.OCCUPIED -> "Đang thuê"
                RoomStatus.CLEANING -> "Đang dọn"
                RoomStatus.MAINTENANCE -> "Bảo trì"
            }
            println("${it.id.padEnd(3)} | ${it.type.padEnd(10)} | ${"%,.0f".format(it.price)} | $statusRoom")
        }
    }

    fun readRoomFromInput(): Room? {
        val id = readInt("Nhap ID phong: ") ?: run { println("Nhap khong hop le.")  }
        if (hm.rooms.any { it.id == id }) { println("ID da ton tai."); continue }
        val type = ConsoleIO.readNonBlank("Nhap loai phong: ")
        val price = ConsoleIO.readDouble("Nhap gia phong: ") ?: run { println("Nhap khong hop le."); continue }
        val statusText = ConsoleIO.readNonBlank("Nhap tinh trang (Trong/Dang thue/Cleaning/Maintenance): ")
        val added = hm.addRoomIfNotExists(Room.fromInput(id, type, price, statusText))
        println(if (added) "Da them phong." else "Phong da ton tai.")
    }

}