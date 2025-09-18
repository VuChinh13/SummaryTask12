package com.example.summarytask12.model.payment
import java.time.LocalDateTime

/**
 * Hóa đơn/Thanh toán cho một booking
 */
data class Payment(
    val paymentId: String,
    val bookingId: String,
    val amount: Double,
    val method: PaymentMethod,
    val paidAt: LocalDateTime = LocalDateTime.now()
)
