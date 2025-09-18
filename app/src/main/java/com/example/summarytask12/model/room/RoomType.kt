package com.example.summarytask12.model.room

/**
 * Loại phòng trong khách sạn
 *
 * - SINGLE: Phòng đơn, 1 giường cho 1 khách
 * - DOUBLE: Phòng đôi, 1 giường lớn (queen/king) cho 2 khách
 * - TWIN: Phòng có 2 giường đơn tách biệt, cho 2 khách muốn ngủ riêng
 * - DELUXE: Phòng cao cấp hơn tiêu chuẩn, diện tích rộng, tiện nghi tốt, có thể có view đẹp
 * - SUITE: Phòng hạng sang, thường gồm nhiều không gian (phòng khách + phòng ngủ), dành cho khách VIP hoặc gia đình
 */
enum class RoomType {
    SINGLE,
    DOUBLE,
    TWIN,
    DELUXE,
    SUITE
}
