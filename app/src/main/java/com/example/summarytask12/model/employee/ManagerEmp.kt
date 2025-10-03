package com.example.summarytask12.model.employee

class ManagerEmp(
    id: String,
    fullName: String,
    phone: String,
    email: String?
) : Employee(id, fullName, phone, email, role = EmployeeRole.MANAGER)
