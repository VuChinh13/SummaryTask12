package com.example.summarytask12.model.room

/**
 * Thông tin phòng
 * - id: khóa nội bộ
 * - roomNumber: số phòng hiển thị cho khách
 * - price: đơn giá mặc định theo đêm
 * - capacity: số khách tối đa
 * - type: loại phòng
 * - status: trạng thái hiện tại
 * - description: mô tả bổ sung
 */
data class Room(
    val id: Int,
    val roomNumber: String,
    var price: Double,
    var capacity: Int,
    var type: RoomType,
    var status: RoomStatus = RoomStatus.AVAILABLE,
    var description: String? = null
)
