package com.example.summarytask12.message

/**
 * Message cho Output
 */
object MessagesOutput {
    const val NOT_ROOM = "Khong the tim thay phong"
    const val ROOM_NOT_AVAILABLE = "Phong khong co san"
    const val ROOM_NOT_IN_RENTAL_STATUS = "Phong khong trong tinh trang thue"
    const val CHECKOUT_SUCCESSFUL = "Checkout thanh cong"
    const val CHECKIN_SUCCESSFUL = "Checkin thanh cong"
    const val INVALID_INPUT = "Nhap khong hop le"
    const val ROOM_ID_ALREADY_EXISTS = "Id phong da ton tai"
    const val CANNOT_BE_EMPTY = "Khong duoc de trong"
    const val ENTER_VALID_DECIMAL_NUMBER = "Nhap so thuc hop le!"
    const val ENTER_VALID_INTEGER_NUMBER = "Nhap so nguyen hop le!"
    const val ROOM_ADDED_SUCCESS = "Da them phong."
    const val ROOM_ALREADY_EXISTS = "Phong da ton tai."
    const val ROOM_REMOVED_SUCCESS = "Da xoa phong."
    const val UPDATE_SUCCESS = "Cap nhat thanh cong."
    const val NO_DATA = "Khong co du lieu."
    const val ROOMS_HEADER = "ID   | So phong | Gia (VND)      | Suc chua | Loai    | Trang thai"
    const val TOTAL_AMOUNT_PREFIX = "Tong tien: "
    const val INVALID_CHOICE = "Lua chon khong hop le."
    const val GOODBYE = "Tam biet!"

    // Menu
    val MENU: String = """
===== QUAN LY KHACH SAN =====
1. Them phong
2. Xoa phong
3. Chinh sua thong tin phong
4. Hien thi danh sach phong
5. Tim kiem phong theo ID
6. Dat phong (Check-in)
7. Tra phong (Check-out)
8. Sap xep theo gia
0. Thoat
""".trimIndent()
}
