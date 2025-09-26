package com.example.summarytask12.message

/**
 * Message cho Input
 */
object MessagesInput {
    const val ENTER_MENU_CHOICE = "Moi chon chuc nang: "

    // Room
    const val ENTER_ROOM_ID = "Nhap ID phong: "
    const val ENTER_ROOM_NUMBER = "Nhap so phong (vd: 101A): "
    const val ENTER_ROOM_PRICE = "Nhap gia phong: "
    const val ENTER_ROOM_CAPACITY = "Nhap suc chua (so nguoi): "
    const val ENTER_ROOM_TYPE = "Nhap loai phong (Single/Double/Twin/Deluxe/Suite): "
    const val ENTER_ROOM_STATUS = "Nhap tinh trang (Available/Occupied/Cleaning/Maintenance): "
    const val ENTER_ROOM_PRICE_OPTIONAL = "Nhap gia phong (bo qua neu khong doi): "
    const val ENTER_ROOM_STATUS_OPTIONAL = "Nhap tinh trang (bo qua neu khong doi): "

    // Guest
    const val ENTER_GUEST_NAME = "Nhap ten khach: "
    const val ENTER_GUEST_PHONE = "Nhap so dien thoai: "
    const val ENTER_GUEST_EMAIL = "Nhap email (bo qua neu khong co): "
    const val ENTER_GUEST_ID = "Nhap ma khach: "
    const val ENTER_GUEST_ID_NUMBER = "Nhap so CCCD/CMND (bo qua neu khong co): "

    // Booking
    const val ENTER_NIGHTS = "Nhap so dem: "
    const val ENTER_SORT_ORDER = "Chon thu tu (1. Tang dan | 2. Giam dan): "

    // Payment
    const val ENTER_PAYMENT_METHOD =
        "Chon phuong thuc thanh toan (Cash/Credit_Card/Debit_Card/Online): "
}
