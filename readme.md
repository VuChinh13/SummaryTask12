# Hệ thống Quản lý Khách sạn (Basic)

Đây là **Hệ thống Quản lý Khách sạn** đơn giản.  
Hệ thống này cho phép quản lý **phòng**, **khách**, **đặt phòng**, và **thanh toán** cho khách sạn.

## Tính năng

### **Quản lý phòng (Room)**:
- Thêm, xóa, chỉnh sửa thông tin phòng.
- Hiển thị danh sách phòng.
- Tìm kiếm phòng theo **ID**.
- Sắp xếp phòng theo **giá**.

### **Quản lý khách (Guest)**:
- Tạo mới hoặc lấy thông tin khách đã tồn tại.
- Lưu thông tin khách: tên, số điện thoại, email, giấy tờ tùy thân.
- Tích điểm khách hàng (loyalty points) sau khi check-out.

### **Đặt phòng (Booking)**:
- **Check-in**: Nhận phòng cho khách, cập nhật trạng thái phòng.
- **Check-out**: Trả phòng, tính tiền dựa trên số đêm và giá phòng.

### **Thanh toán (Payment)**:
- Sinh **hóa đơn thanh toán**.
- Hỗ trợ nhiều phương thức thanh toán: **Tiền mặt**, **Thẻ tín dụng**, **Thẻ ghi nợ**, **Thanh toán trực tuyến**.

### **Quản lý trạng thái phòng (Room Status)**:
- **AVAILABLE**: Phòng trống, có thể đặt.
- **OCCUPIED**: Phòng đang có khách.
- **CLEANING**: Phòng đang được dọn.
- **MAINTENANCE**: Phòng đang bảo trì.

## Cấu trúc Dự án

### **1. console/**
Chứa các lớp xử lý giao diện người dùng qua console.
- **input/**: Chứa các lớp nhập liệu từ người dùng.
  - **InputReader.kt**: Cung cấp các phương thức đọc dữ liệu từ người dùng, kiểm tra tính hợp lệ của dữ liệu.
- **output/**: Chứa các lớp hiển thị thông tin ra console.
  - **OutputPrinter.kt**: In menu, thông báo, danh sách phòng ra console.
- **service/**: Chứa các lớp dịch vụ cho các hành động trong hệ thống.
  - **ConsoleBookingService.kt**: Xử lý đặt phòng và trả phòng.
  - **ConsoleRoomService.kt**: Xử lý các chức năng liên quan đến phòng như thêm, xóa, sửa, và hiển thị phòng.

### **2. data/**
Chứa các lớp xử lý dữ liệu và nạp dữ liệu mẫu.
- **InitialHotelData.kt**: Nạp dữ liệu mẫu vào hệ thống khi khởi động.

### **3. main/**
Chứa điểm bắt đầu của chương trình.
- **Main.kt**: Hàm `main` khởi chạy ứng dụng, hiển thị menu chức năng, vòng lặp chính.

### **4. manager/**
Chứa các lớp quản lý nghiệp vụ.
- **HotelManager.kt**: Quản lý các hoạt động nghiệp vụ của khách sạn như quản lý phòng, khách, đặt phòng và thanh toán.

### **5. message/**
Chứa các thông báo và chuỗi cần thiết để giao tiếp với người dùng.
- **MessagesInput.kt**: Các chuỗi prompt yêu cầu người dùng nhập dữ liệu.
- **MessagesOutput.kt**: Các thông báo kết quả hoặc lỗi cho người dùng.

### **6. model/**
Chứa các lớp mô hình dữ liệu.
- **booking/**:
  - **Booking.kt**: Lớp đại diện cho thông tin đặt phòng.
  - **BookingStatus.kt**: Trạng thái của đặt phòng.
- **payment/**:
  - **Payment.kt**: Lớp đại diện cho thông tin thanh toán.
  - **PaymentMethod.kt**: Các phương thức thanh toán.
- **guest/**:
  - **Guest.kt**: Lớp đại diện cho khách lưu trú.
- **room/**:
  - **Room.kt**: Lớp đại diện cho phòng.
  - **RoomStatus.kt**: Trạng thái phòng.
  - **RoomType.kt**: Các loại phòng (Single, Double, Suite, ...).

## Cấu trúc các lớp chính:

- **Main.kt**: Khởi động ứng dụng và hiển thị menu chính.
- **HotelManager.kt**: Quản lý các nghiệp vụ chính của khách sạn như thêm/xóa/sửa phòng, quản lý khách và thanh toán.
- **ConsoleBookingService.kt** và **ConsoleRoomService.kt**: Cung cấp các dịch vụ liên quan đến đặt phòng và phòng qua console.
- **InputReader.kt** và **OutputPrinter.kt**: Xử lý việc nhập và in dữ liệu trên giao diện dòng lệnh.

