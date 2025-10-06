package com.example.summarytask12.data

import com.example.summarytask12.model.employee.Employee
import com.example.summarytask12.model.employee.EmployeeRole
import com.example.summarytask12.model.room.Room
import com.example.summarytask12.model.room.RoomStatus
import com.example.summarytask12.model.room.RoomType
import com.example.summarytask12.service.EmployeeService
import com.example.summarytask12.service.RoomService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.random.Random

/**
 * Lớp dùng để khởi tạo dữ liệu
 */
class InitialHotelData {

    fun initializeDataEmployee(employeeService: EmployeeService) {
        val employee1 = Employee(
            "M01",
            "Nguyen Van A",
            LocalDate.of(2000, 12, 12),
            "272 Kim Nguu,Ha Noi",
            "Nam",
            "03568538456",
            "nguyenvana@gmai.com",
            EmployeeRole.MANAGER,
            LocalDate.of(2022, 12, 12)
        )
        val employee2 = Employee(
            "E01",
            "Nguyen Thi C",
            LocalDate.of(2003, 12, 12),
            "200 Hai Ba Trung,Ha Noi",
            "Nu",
            "03568538777",
            "nguyenvanc@gmai.com",
            EmployeeRole.RECEPTIONIST,
            LocalDate.of(2025, 5, 12)
        )
        val employee3 = Employee(
            "E02",
            "Nguyen Van D",
            LocalDate.of(2004, 4, 12),
            "100 Ham Long,Ha Noi",
            "Nam",
            "03568538444",
            "nguyenvanc@gmai.com",
            EmployeeRole.RECEPTIONIST,
            LocalDate.of(2025, 9, 12)
        )
        val employee4 = Employee(
            "E03",
            "Nguyen Van B",
            LocalDate.of(2003, 12, 12),
            "200 Xa Dan,Ha Noi",
            "Nam",
            "03568538999",
            "nguyenvanb@gmai.com",
            EmployeeRole.RECEPTIONIST,
            LocalDate.of(2025, 3, 12)
        )
        employeeService.addEmployee(employee1)
        employeeService.addEmployee(employee2)
        employeeService.addEmployee(employee3)
        employeeService.addEmployee(employee4)
    }

    fun initializeDataRoom(roomService: RoomService) {
        // Nạp dữ liệu phòng mẫu
        roomService.addRoom(Room(1, "101", 100.0, 2, RoomType.SINGLE, RoomStatus.AVAILABLE))
        roomService.addRoom(Room(2, "102", 150.0, 2, RoomType.DOUBLE, RoomStatus.AVAILABLE))
        roomService.addRoom(Room(3, "103", 200.0, 3, RoomType.SUITE, RoomStatus.OCCUPIED))
        roomService.addRoom(Room(4, "104", 120.0, 1, RoomType.SINGLE, RoomStatus.AVAILABLE))
        roomService.addRoom(Room(5, "105", 180.0, 2, RoomType.DOUBLE, RoomStatus.CLEANING))
        roomService.addRoom(Room(6, "106", 220.0, 3, RoomType.SUITE, RoomStatus.MAINTENANCE))
        roomService.addRoom(Room(7, "107", 130.0, 2, RoomType.DOUBLE, RoomStatus.AVAILABLE))
        roomService.addRoom(Room(8, "108", 140.0, 2, RoomType.SINGLE, RoomStatus.AVAILABLE))
        roomService.addRoom(Room(9, "109", 160.0, 4, RoomType.SUITE, RoomStatus.OCCUPIED))
        roomService.addRoom(Room(10, "110", 250.0, 5, RoomType.SUITE, RoomStatus.AVAILABLE))
    }

    // Giả lập khi mà thêm dữ liệu dùng Coroutine
    fun initializeLargeRoomData(roomService: RoomService) {
        // Thêm 10.000 phòng
        CoroutineScope(Dispatchers.Default).launch {
            val totalRooms = 10_000
            repeat(totalRooms) { i ->
                val id = i + 1
                val roomNumber = (100 + i).toString()
                val price = Random.nextDouble(80.0, 300.0)
                val capacity = Random.nextInt(1, 5)
                val type = RoomType.values().random()
                val status = RoomStatus.values().random()

                val room = Room(id, roomNumber, price, capacity, type, status)
                roomService.addRoom(room)
            }
        }
    }
}
