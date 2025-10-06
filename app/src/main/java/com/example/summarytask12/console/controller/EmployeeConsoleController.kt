package com.example.summarytask12.console.controller

import com.example.summarytask12.console.input.InputReader
import com.example.summarytask12.console.output.OutputPrinter
import com.example.summarytask12.manager.HotelManagerService
import com.example.summarytask12.message.MessagesInput
import com.example.summarytask12.message.MessagesOutput
import com.example.summarytask12.model.employee.Employee
import com.example.summarytask12.model.employee.EmployeeRole
import java.time.LocalDate

class EmployeeConsoleController(
    private val hotelManagerService: HotelManagerService,
    private val inputReader: InputReader = InputReader(),
    private val outputPrinter: OutputPrinter = OutputPrinter()
) {
    fun login(): Employee {
        var employee: Employee? = null
        while (employee == null) {
            println(MessagesInput.LOGIN_HEADER)
            val employeeId = inputReader.readNonBlank(MessagesInput.ENTER_EMPLOYEE_LOGIN_ID)
            val foundEmployee = hotelManagerService.getEmployee(employeeId)
            if (foundEmployee != null) {
                employee = foundEmployee
                println("${MessagesOutput.LOGIN_SUCCESS} Xin chào, ${employee.fullName} (${employee.role})")
            } else {
                println(MessagesOutput.INVALID_EMPLOYEE_ID)
            }
        }
        return employee
    }

    fun addEmployee() {
        println(MessagesOutput.EMPLOYEE_ADD_HEADER)
        with(inputReader) {
            val id = readNonBlank(MessagesInput.ENTER_EMPLOYEE_ID)
            val fullName = readNonBlank(MessagesInput.ENTER_EMPLOYEE_NAME)
            val address = readNonBlank(MessagesInput.ENTER_EMPLOYEE_ADDRESS)
            val gender = readNonBlank(MessagesInput.ENTER_EMPLOYEE_GENDER)
            val phone = readNonBlank(MessagesInput.ENTER_EMPLOYEE_PHONE)
            val email = readOptionalLine(MessagesInput.ENTER_EMPLOYEE_EMAIL)
            val roleInput = readNonBlank(MessagesInput.ENTER_EMPLOYEE_ROLE)

            val role = when (normalize(roleInput)) {
                "receptionist" -> EmployeeRole.RECEPTIONIST
                "manager" -> EmployeeRole.MANAGER
                else -> {
                    println(MessagesOutput.INVALID_INPUT)
                    return
                }
            }

            val employee = Employee(
                id = id,
                fullName = fullName,
                birthDate = null,
                address = address,
                gender = gender,
                phone = phone,
                email = email,
                role = role,
                hireDate = LocalDate.now()
            )
            if (hotelManagerService.addEmployee(employee)) {
                println(MessagesOutput.EMPLOYEE_ADDED_SUCCESS)
            } else println(MessagesOutput.EMPLOYEE_ADDED_ERROR)
        }
    }

    fun editEmployee() {
        println(MessagesOutput.EMPLOYEE_EDIT_HEADER)

        val id = inputReader.readNonBlank(MessagesInput.ENTER_EMPLOYEE_ID_EDIT)
        val updated = hotelManagerService.updateEmployee(id, updater = { emp ->
            println(MessagesOutput.EMPLOYEE_EDIT_HINT)
            inputReader.readOptionalLine(MessagesInput.ENTER_EMPLOYEE_PHONE_NEW)
                ?.let { emp.phone = it }
            inputReader.readOptionalLine(MessagesInput.ENTER_EMPLOYEE_EMAIL_NEW)
                ?.let { emp.email = it }
            inputReader.readOptionalLine(MessagesInput.ENTER_EMPLOYEE_ADDRESS_NEW)
                ?.let { emp.address = it }
        })
        println(if (updated) MessagesOutput.UPDATE_SUCCESS else MessagesOutput.NO_DATA)
    }

    fun removeEmployee() {
        println(MessagesOutput.EMPLOYEE_REMOVE_HEADER)

        val id = inputReader.readNonBlank(MessagesInput.ENTER_EMPLOYEE_ID_REMOVE)
        val removed = hotelManagerService.removeEmployee(id)
        println(if (removed) MessagesOutput.EMPLOYEE_REMOVED_SUCCESS else MessagesOutput.NO_DATA)
    }

    fun listEmployees() {
        val employees = hotelManagerService.listEmployees()
        outputPrinter.showEmployeeList(employees)
    }

    // Hàm check quyền
    fun checkPermission(by: Employee, allowedRoles: EmployeeRole): Boolean {
        val isAllowed = allowedRoles == by.role
        return if (!isAllowed) {
            println(MessagesOutput.PERMISSION_DENIED)
            false
        } else true
    }
}
