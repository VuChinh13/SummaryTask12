package com.example.summarytask12.model.room

/**
 * Trạng thái hiện tại của phòng trong khách sạn
 *
 * - AVAILABLE:   Phòng trống, sẵn sàng để nhận khách
 * - OCCUPIED:    Phòng đang có khách ở (đã check-in)
 * - CLEANING:    Phòng đang được dọn dẹp, chưa sẵn sàng để đón khách mới
 * - MAINTENANCE: Phòng đang bảo trì/sửa chữa, tạm thời không sử dụng được
 */
enum class RoomStatus {
    AVAILABLE,
    OCCUPIED,
    CLEANING,
    MAINTENANCE
}
