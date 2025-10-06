package com.example.summarytask12.service

import com.example.summarytask12.model.employee.Employee

class EmployeeService {
    private val employees = mutableMapOf<String, Employee>()

    fun addEmployee(employee: Employee): Boolean {
        if (employees.containsKey(employee.id)) return false
        employees[employee.id] = employee
        return true
    }

    fun getEmployee(id: String): Employee? = employees[id]

    fun updateEmployee(id: String, updater: (Employee) -> Unit): Boolean {
        val emp = employees[id] ?: return false
        updater(emp)
        return true
    }

    fun removeEmployee(id: String): Boolean {
        return employees.remove(id) != null
    }

    fun getAllEmployees(): List<Employee> = employees.values.toList()
}
