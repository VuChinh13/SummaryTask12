package com.example.summarytask12.console.output

import com.example.summarytask12.message.MessagesInput
import com.example.summarytask12.message.MessagesOutput
import com.example.summarytask12.model.room.Room
import com.example.summarytask12.model.room.RoomStatus

/**
 * Lớp OutputPrinter chịu trách nhiệm in các dữ liệu ra console.
 * Cung cấp các phương thức để:
 * - In menu lựa chọn cho người dùng.
 * - In danh sách phòng và các thông tin chi tiết liên quan đến phòng.
 * - In các thông báo.
 */
class OutputPrinter {
    fun showMenu() {
        println(MessagesOutput.MENU)
        print(MessagesInput.ENTER_MENU_CHOICE)
    }

    fun showRoomList(roomList: List<Room>) {
        if (roomList.isEmpty()) {
            println(MessagesOutput.NO_DATA)
            return
        }
        println(MessagesOutput.ROOMS_HEADER)
        roomList.forEach { room ->
            val statusText = when (room.status) {
                RoomStatus.AVAILABLE -> "Trong"
                RoomStatus.OCCUPIED -> "Dang thue"
                RoomStatus.CLEANING -> "Dang don"
                RoomStatus.MAINTENANCE -> "Dang sua chua"
            }
            println(
                "${room.id.toString().padEnd(4)}| " +
                        "${room.roomNumber.padEnd(8)} | " +
                        "${"%(,.0f".format(room.price).padEnd(14)} | " +
                        "${room.capacity.toString().padEnd(8)} | " +
                        "${room.type.name.padEnd(7)} | $statusText"
            )
        }
    }

}
