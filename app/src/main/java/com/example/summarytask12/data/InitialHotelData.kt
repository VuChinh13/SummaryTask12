package com.example.summarytask12.data

import com.example.summarytask12.manager.HotelManager
import com.example.summarytask12.model.room.Room
import com.example.summarytask12.model.room.RoomStatus
import com.example.summarytask12.model.room.RoomType

/**
 * Lớp dùng để khởi tạo dữ liệu
 */
class InitialHotelData {

    // Phương thức nạp dữ liệu vào HotelManager
    fun initializeData(hotelManager: HotelManager) {
        // Nạp dữ liệu phòng mẫu
        hotelManager.addRoom(Room(1, "101", 100.0, 2, RoomType.SINGLE, RoomStatus.AVAILABLE))
        hotelManager.addRoom(Room(2, "102", 150.0, 2, RoomType.DOUBLE, RoomStatus.AVAILABLE))
        hotelManager.addRoom(Room(3, "103", 200.0, 3, RoomType.SUITE, RoomStatus.OCCUPIED))
        hotelManager.addRoom(Room(4, "104", 120.0, 1, RoomType.SINGLE, RoomStatus.AVAILABLE))
        hotelManager.addRoom(Room(5, "105", 180.0, 2, RoomType.DOUBLE, RoomStatus.CLEANING))
        hotelManager.addRoom(Room(6, "106", 220.0, 3, RoomType.SUITE, RoomStatus.MAINTENANCE))
        hotelManager.addRoom(Room(7, "107", 130.0, 2, RoomType.DOUBLE, RoomStatus.AVAILABLE))
        hotelManager.addRoom(Room(8, "108", 140.0, 2, RoomType.SINGLE, RoomStatus.AVAILABLE))
        hotelManager.addRoom(Room(9, "109", 160.0, 4, RoomType.SUITE, RoomStatus.OCCUPIED))
        hotelManager.addRoom(Room(10, "110", 250.0, 5, RoomType.SUITE, RoomStatus.AVAILABLE))

        // Nạp dữ liệu khách mẫu
        hotelManager.getOrCreateGuest("guest1", "John Doe", "123456789")
        hotelManager.getOrCreateGuest("guest2", "Jane Doe", "987654321")
        hotelManager.getOrCreateGuest("guest3", "Alice Smith", "111222333")
        hotelManager.getOrCreateGuest("guest4", "Bob Johnson", "444555666")
        hotelManager.getOrCreateGuest("guest5", "Charlie Brown", "777888999")
        hotelManager.getOrCreateGuest("guest6", "David Williams", "101112131")
        hotelManager.getOrCreateGuest("guest7", "Eva Davis", "141516171")
        hotelManager.getOrCreateGuest("guest8", "Frank Miller", "181920212")
        hotelManager.getOrCreateGuest("guest9", "Grace Lee", "222324252")
        hotelManager.getOrCreateGuest("guest10", "Hannah Clark", "262728292")

        // Nạp dữ liệu đặt phòng mẫu
        hotelManager.checkIn(1, "John Doe", "123456789", "guest1", 2)
        hotelManager.checkIn(2, "Jane Doe", "987654321", "guest2", 3)
        hotelManager.checkIn(3, "Alice Smith", "111222333", "guest3", 5)
        hotelManager.checkIn(4, "Bob Johnson", "444555666", "guest4", 1)
        hotelManager.checkIn(5, "Charlie Brown", "777888999", "guest5", 4)
        hotelManager.checkIn(6, "David Williams", "101112131", "guest6", 2)
        hotelManager.checkIn(7, "Eva Davis", "141516171", "guest7", 3)
        hotelManager.checkIn(8, "Frank Miller", "181920212", "guest8", 1)
        hotelManager.checkIn(9, "Grace Lee", "222324252", "guest9", 6)
        hotelManager.checkIn(10, "Hannah Clark", "262728292", "guest10", 2)
    }
}
