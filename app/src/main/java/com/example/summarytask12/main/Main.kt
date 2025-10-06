package com.example.summarytask12.main

import com.example.summarytask12.console.controller.BookingConsoleController
import com.example.summarytask12.console.controller.EmployeeConsoleController
import com.example.summarytask12.console.controller.RoomConsoleController
import com.example.summarytask12.console.output.OutputPrinter
import com.example.summarytask12.manager.HotelManagerService
import com.example.summarytask12.message.MessagesOutput
import com.example.summarytask12.model.employee.EmployeeRole

/**
 * Điểm chạy chương trình (console loop)
 * - Không định nghĩa các hàm nghiệp vụ ở đây
 * - Luồng: khởi tạo -> đăng nhập -> vòng lặp menu -> Check quyền -> ủy quyền cho controller
 */
fun main() {
    val output = OutputPrinter()
    val hotelManagerService = HotelManagerService()
    hotelManagerService.initData()
    val employeeConsoleController = EmployeeConsoleController(hotelManagerService)
    val roomConsoleController = RoomConsoleController(hotelManagerService)
    val bookingConsoleController = BookingConsoleController(hotelManagerService)
    val currentEmployee = employeeConsoleController.login()

    while (true) {
        output.showMenu()
        when (readLine()?.trim()) {
            "1" -> {
                if (employeeConsoleController.checkPermission(
                        currentEmployee,
                        EmployeeRole.MANAGER
                    )
                ) roomConsoleController.addRoom()
            }

            "2" -> {
                if (employeeConsoleController.checkPermission(
                        currentEmployee,
                        EmployeeRole.MANAGER
                    )
                ) roomConsoleController.removeRoom()
            }

            "3" -> {
                if (employeeConsoleController.checkPermission(
                        currentEmployee,
                        EmployeeRole.MANAGER
                    )
                ) roomConsoleController.editRoom()
            }

            "4" -> roomConsoleController.displayAllRooms()

            "5" -> roomConsoleController.searchRoomById()

            "6" -> bookingConsoleController.checkIn()

            "7" -> bookingConsoleController.checkOut()

            "8" -> roomConsoleController.sortRoomsByPrice()

            "9" -> {
                if (employeeConsoleController.checkPermission(
                        currentEmployee,
                        EmployeeRole.MANAGER
                    )
                ) employeeConsoleController.addEmployee()
            }

            "10" -> {
                if (employeeConsoleController.checkPermission(
                        currentEmployee,
                        EmployeeRole.MANAGER
                    )
                ) employeeConsoleController.editEmployee()
            }

            "11" -> {
                if (employeeConsoleController.checkPermission(
                        currentEmployee,
                        EmployeeRole.MANAGER
                    )
                ) employeeConsoleController.removeEmployee()
            }

            "12" -> {
                if (employeeConsoleController.checkPermission(
                        currentEmployee,
                        EmployeeRole.MANAGER
                    )
                ) employeeConsoleController.listEmployees()
            }

            "0" -> {
                println(MessagesOutput.GOODBYE)
                return
            }

            else -> println(MessagesOutput.INVALID_CHOICE)
        }
        println()
    }
}
