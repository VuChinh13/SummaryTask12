package com.example.summarytask12.model.employee

class Receptionist(
    id: String,
    fullName: String,
    phone: String,
    email: String?
) : Employee(id, fullName, phone, email, role = EmployeeRole.RECEPTIONIST)
