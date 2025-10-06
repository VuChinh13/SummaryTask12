package com.example.summarytask12.manager

import com.example.summarytask12.data.InitialHotelData
import com.example.summarytask12.model.employee.Employee
import com.example.summarytask12.model.payment.PaymentMethod
import com.example.summarytask12.model.room.Room
import com.example.summarytask12.model.room.RoomStatus
import com.example.summarytask12.service.BookingService
import com.example.summarytask12.service.EmployeeService
import com.example.summarytask12.service.GuestService
import com.example.summarytask12.service.PaymentService
import com.example.summarytask12.service.RoomService

/**
 * Lớp trung tâm điều phối
 * - Quản lý phòng (RoomService)
 * - Quản lý khách (GuestService)
 * - Quản lý đặt phòng (BookingService)
 * - Quản lý thanh toán (PaymentService)
 * - Quản lý nhân viên (EmployeeService)
 */
class HotelManagerService {
    private val roomService: RoomService = RoomService()
    private val guestService: GuestService = GuestService()
    private val employeeService: EmployeeService = EmployeeService()
    private val paymentService: PaymentService = PaymentService()
    private val bookingService: BookingService =
        BookingService(roomService, guestService, paymentService)

    // EmployeeService
    fun addEmployee(e: Employee): Boolean {
        return employeeService.addEmployee(e)
    }

    fun updateEmployee(id: String, updater: (Employee) -> Unit): Boolean {
        return employeeService.updateEmployee(id, updater)
    }

    fun removeEmployee(id: String): Boolean {
        return employeeService.removeEmployee(id)
    }

    fun listEmployees(): List<Employee> = employeeService.getAllEmployees()

    fun getEmployee(id: String) = employeeService.getEmployee(id)

    fun addRoom(room: Room): Boolean {
        return roomService.addRoom(room)
    }

    // RoomService
    fun removeRoom(roomId: Int): Boolean {
        return roomService.removeRoom(roomId) { id, status ->
            bookingService.hasActiveBooking(id, status)
        }
    }

    fun updateRoom(roomId: Int, newPrice: Double?, newStatus: RoomStatus?): Boolean {
        return roomService.updateRoom(roomId, newPrice, newStatus) { id, status ->
            bookingService.hasActiveBooking(id, status)
        }
    }

    fun getAllRooms() = roomService.getAllRooms()

    fun findRoomById(roomId: Int) = roomService.findRoomById(roomId)

    fun sortByPrice(ascending: Boolean) = roomService.sortByPrice(ascending)

    // PaymentService
    fun checkIn(
        roomId: Int,
        guestName: String,
        guestPhone: String,
        guestId: String,
        nights: Int
    ): String {
        guestService.getOrCreateGuest(guestId, guestName, guestPhone)
        return bookingService.checkIn(roomId, guestId, guestName, guestPhone, nights)
    }

    fun checkOut(roomId: Int, method: PaymentMethod) =
        bookingService.checkOut(roomId, method)

    // Hàm để khởi tạo dữ liệu
    fun initData() {
        val initializeData = InitialHotelData()
        initializeData.initializeDataEmployee(employeeService)
        initializeData.initializeDataRoom(roomService)
        //initializeData.initializeLargeRoomData(roomService) // Test coroutine
    }
}
