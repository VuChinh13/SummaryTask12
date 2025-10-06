package com.example.summarytask12.service

import com.example.summarytask12.model.payment.Payment
import com.example.summarytask12.model.payment.PaymentMethod
import java.time.LocalDateTime
import java.util.UUID

class PaymentService {
    private val payments = mutableMapOf<String, Payment>()

    fun createPayment(bookingId: String, amount: Double, method: PaymentMethod): Payment {
        val payment = Payment(
            paymentId = UUID.randomUUID().toString(),
            bookingId = bookingId,
            amount = amount,
            method = method,
            paidAt = LocalDateTime.now()
        )
        payments[payment.paymentId] = payment
        return payment
    }

    fun get(paymentId: String): Payment? = payments[paymentId]
}
