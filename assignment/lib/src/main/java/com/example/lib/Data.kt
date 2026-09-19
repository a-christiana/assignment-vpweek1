package com.example.lib

data class Menu(
    val nama: String,
    val harga: Int,
    val deskripsi: String
)

data class Order(
    val namaMenu: String,
    val qty: Int
)

data class Receipt(
    val pembeli: String,
    val pesanan: List<Order>
)