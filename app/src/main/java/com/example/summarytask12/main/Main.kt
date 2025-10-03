package com.example.summarytask12.main

import com.example.summarytask12.console.input.InputReader
import com.example.summarytask12.console.output.OutputPrinter
import com.example.summarytask12.console.service.ConsoleBookingService
import com.example.summarytask12.console.service.ConsoleRoomService
import com.example.summarytask12.data.InitialHotelData
import com.example.summarytask12.manager.HotelManager
import com.example.summarytask12.message.MessagesOutput
import com.example.summarytask12.model.employee.Employee
import com.example.summarytask12.model.employee.ManagerEmp
import com.example.summarytask12.model.employee.Receptionist

/**
 * Điểm chạy chương trình (console loop)
 * - Không định nghĩa các hàm trong file này
 */

fun main() {
    val hotelManager = HotelManager()
    val initialHotelData = InitialHotelData()

    // Seed dữ liệu + tự tạo 1 manager & 1 receptionist bên trong
    initialHotelData.initializeData(hotelManager)

    // (Tuỳ chọn) tạo thêm nhân viên rồi hire để đăng nhập
    val extraManager = ManagerEmp("e-mgr-02", "Alice Manager", "090000001", null)
    val extraRecep = Receptionist("e-rec-02", "Bob Reception", "090000002", null)
    hotelManager.hireEmployee(extraManager)
    hotelManager.hireEmployee(extraRecep)

    // Chọn nhân viên đăng nhập
    val input = InputReader()
    println("Dang nhap: 1.Manager  2.Receptionist")
    val choice = input.readNonBlank("Chon: ")
    val currentEmployee: Employee = when (choice) {
        "1" -> extraManager
        "2" -> extraRecep
        else -> extraRecep
    }

    val outputPrinter = OutputPrinter()
    val consoleBookingService = ConsoleBookingService(hotelManager, currentEmployee)
    val consoleRoomService = ConsoleRoomService(hotelManager, currentEmployee)

    while (true) {
        outputPrinter.showMenu()
        when (readLine()?.trim()) {
            "1" -> { // Thêm phòng
                if (currentEmployee.role.name == "MANAGER") consoleRoomService.addRoom()
                else println(MessagesOutput.PERMISSION_DENIED)
            }

            "2" -> { // Xoá phòng
                if (currentEmployee.role.name == "MANAGER") consoleRoomService.removeRoom()
                else println(MessagesOutput.PERMISSION_DENIED)
            }

            "3" -> { // Chỉnh sửa phòng
                if (currentEmployee.role.name == "MANAGER") consoleRoomService.editRoom()
                else println(MessagesOutput.PERMISSION_DENIED)
            }

            "4" -> consoleRoomService.displayAllRooms()
            "5" -> consoleRoomService.searchRoomById()
            "6" -> { // Check-in
                if (currentEmployee.role.name in listOf(
                        "RECEPTIONIST",
                        "MANAGER"
                    )
                ) consoleBookingService.checkIn()
                else println(MessagesOutput.PERMISSION_DENIED)
            }

            "7" -> { // Check-out
                if (currentEmployee.role.name in listOf(
                        "RECEPTIONIST",
                        "MANAGER"
                    )
                ) consoleBookingService.checkOut()
                else println(MessagesOutput.PERMISSION_DENIED)
            }

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

