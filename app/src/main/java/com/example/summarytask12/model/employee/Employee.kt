package com.example.summarytask12.model.employee

import com.example.summarytask12.model.person.Person

abstract class Employee(
    id: String,
    fullName: String,
    phone: String,
    email: String?,
    val role: EmployeeRole
) : Person(id, fullName, phone, email) {

    // Hook (tuỳ chọn) để log/kiểm tra trước/sau các tác vụ quản trị
    open fun beforeAddRoom(roomId: Int) {}
    open fun afterAddRoom(roomId: Int) {}
}
