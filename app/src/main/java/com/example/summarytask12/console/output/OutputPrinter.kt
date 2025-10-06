package com.example.summarytask12.console.output

import com.example.summarytask12.message.MessagesInput
import com.example.summarytask12.message.MessagesOutput
import com.example.summarytask12.model.employee.Employee
import com.example.summarytask12.model.room.Room
import com.example.summarytask12.model.room.RoomStatus
import java.time.format.DateTimeFormatter

/**
 * Lớp OutputPrinter chịu trách nhiệm in các dữ liệu ra console.
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

    fun showEmployeeList(employees: List<Employee>) {
        if (employees.isEmpty()) {
            println(MessagesOutput.EMPLOYEES_EMPTY); return
        }
        println(MessagesOutput.EMPLOYEES_HEADER)

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        employees.forEach { e ->
            val hireDate = e.hireDate.format(formatter)
            val email = e.email ?: MessagesOutput.NOT_AVAILABLE
            println(
                """
                ID: ${e.id}
                Ho ten: ${e.fullName}
                Gioi tinh: ${e.gender}
                SDT: ${e.phone}
                Email: $email
                Chuc vu: ${e.role}
                Ngay vao lam: $hireDate
                ---------------------------
                """.trimIndent()
            )
        }
    }
}
