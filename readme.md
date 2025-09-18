# Hệ thống Quản lý Khách sạn (Hotel Management System)

Đây là một dự án **Hệ thống Quản lý Khách sạn** đơn giản, dựa trên giao diện dòng lệnh (console-based) được viết bằng Kotlin.  
Hệ thống cho phép quản lý phòng, khách, đặt phòng, và thanh toán.

## Tính năng

**Quản lý phòng (Room)**:
- Thêm, xóa, chỉnh sửa thông tin phòng
- Hiển thị danh sách phòng
- Tìm kiếm phòng theo ID
- Sắp xếp phòng theo giá

**Quản lý khách (Guest)**:
- Tạo mới hoặc lấy khách đã tồn tại
- Lưu thông tin khách, số điện thoại, email, giấy tờ tùy thân
- Tích điểm khách hàng (loyalty points) sau khi checkout

**Đặt phòng (Booking)**:
- Check-in: Nhận phòng, cập nhật trạng thái phòng
- Check-out: Trả phòng, tính tiền dựa trên số đêm và giá phòng

**Thanh toán (Payment)**:
- Sinh hóa đơn thanh toán
- Hỗ trợ nhiều phương thức: Tiền mặt, Thẻ tín dụng, Thẻ ghi nợ, Online

**Quản lý trạng thái phòng (Room Status)**:
- AVAILABLE: Phòng trống, có thể đặt
- OCCUPIED: Đang có khách
- CLEANING: Đang dọn phòng
- MAINTENANCE: Đang bảo trì

## Cấu trúc Dự án

main/
- **Main.kt**: Hàm `main` khởi chạy ứng dụng, hiển thị menu chức năng, vòng lặp chính.

message/
- **MessagesInput.kt**: Chuỗi prompt cho người dùng nhập.
- **MessagesOutput.kt**: Chuỗi thông báo kết quả hoặc lỗi.

model/
- booking/
    - **Booking.kt**: Thông tin đặt phòng (booking) và trạng thái (BookingStatus).
- payment/
    - **Payment.kt**: Thông tin thanh toán và phương thức (PaymentMethod).
- person/
    - **Person.kt**: Lớp cơ sở cho thông tin cá nhân.
    - **Guest.kt**: Khách lưu trú (Guest).
- room/
    - **Room.kt**: Thông tin phòng (Room).
    - **RoomStatus.kt**: Trạng thái phòng.
    - **RoomType.kt**: Loại phòng (Single, Double, Twin, Deluxe, Suite).

manager/
- **HotelManager.kt**: Singleton quản lý nghiệp vụ (Room, Guest, Booking, Payment).

utils/
- **ConsoleHelpers.kt**: Hỗ trợ nhập/xuất console, gọi sang HotelManager để thực thi logic.

## Cách chạy

1. Mở dự án trong **IntelliJ IDEA** hoặc Android Studio.
2. Tìm đến file `Main.kt` trong thư mục `main/`.
3. Chạy chương trình, menu quản lý khách sạn sẽ xuất hiện trong cửa sổ **Run**.
4. Nhập lựa chọn theo menu để thao tác (thêm phòng, đặt phòng, trả phòng...).



