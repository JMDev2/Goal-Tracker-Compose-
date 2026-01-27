package com.example.targetsavings.data.model


data class CreditAccount(
    val name: String,
    val number: String,
    val balance: Double = 0.0
)

val creditAccounts = listOf(
    CreditAccount(name = "John Doe", number = "00123456789", balance = 5000000.00),
    CreditAccount(name = "Jane Smith", number = "00987654321", balance = 5000000.00),
    CreditAccount(name = "Alice Johnson", number = "00234567890", balance = 5000000.00),
    CreditAccount(name = "Bob Williams", number = "00345678901", balance = 5000000.00)
)
