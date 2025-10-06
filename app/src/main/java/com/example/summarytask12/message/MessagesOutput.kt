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
    const val PERMISSION_DENIED = "Khong co quyen thuc hien chuc nang nay."
    const val EMPLOYEE_ADD_HEADER = "===== THEM NHAN VIEN MOI ====="
    const val EMPLOYEE_ADDED_SUCCESS = "Da them nhan vien thanh cong."
    const val EMPLOYEE_ADDED_ERROR = "Da co loi xay ra!!."
    const val EMPLOYEE_EDIT_HEADER = "===== CAP NHAT THONG TIN NHAN VIEN ====="
    const val EMPLOYEE_EDIT_HINT = "De trong neu khong muon thay doi."
    const val EMPLOYEE_REMOVE_HEADER = "===== XOA NHAN VIEN ====="
    const val EMPLOYEE_REMOVED_SUCCESS = "Da xoa nhan vien."
    const val ERROR_PREFIX = "Loi:"
    const val INVALID_EMPLOYEE_ID = "ID nhan vien khong ton tai. Vui long thu lai!"
    const val LOGIN_SUCCESS = "Dang nhap thanh cong."
    const val NOT_AVAILABLE = "N/A"
    const val EMPLOYEES_EMPTY = "Chua co nhan vien nao trong he thong."
    const val EMPLOYEES_HEADER = "===== DANH SACH NHAN VIEN ====="
    const val MENU: String = """
===== QUAN LY KHACH SAN =====
1. Them phong
2. Xoa phong
3. Chinh sua thong tin phong
4. Hien thi danh sach phong
5. Tim kiem phong theo ID
6. Dat phong (Check-in)
7. Tra phong (Check-out)
8. Sap xep theo gia
9. Them nhan vien
10. Chinh sua thong tin nhan vien
11. Xoa nhan vien
12. Hien thi danh sach nhan vien
0. Thoat
"""
}
