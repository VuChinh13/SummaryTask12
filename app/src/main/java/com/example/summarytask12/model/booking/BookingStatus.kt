package com.example.summarytask12.model.booking

/**
 * Trạng thái đặt phòng / lưu trú của khách
 *
 * - NEW: Đặt phòng mới, đã ghi nhận nhưng khách chưa đến
 * - CHECKED_IN: Khách đã nhận phòng, đang ở trong khách sạn
 * - CHECKED_OUT: Khách đã trả phòng, đặt phòng đã hoàn tất
 * - CANCELLED: Đặt phòng bị hủy (khách hoặc khách sạn hủy)
 */
enum class BookingStatus {
    NEW,
    CHECKED_IN,
    CHECKED_OUT,
    CANCELLED
}
