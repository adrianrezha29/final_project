package com.example.final_project.ui

import com.example.final_project.model.Laptop

data class AddUIState(
    val addEvent: AddEvent = AddEvent(),
)

data class AddEvent(
    val id: String = "",
    val nama: String = "",
    val harga: String = "",
    val jenis: String = "",
)

fun AddEvent.toLaptop() = Laptop(
    id = id,
    nama = nama,
    harga = harga,
    Jenis = jenis
)

data class DetailUIState(
    val addEvent: AddEvent = AddEvent(),
)

fun Laptop.toDetailLaptop(): AddEvent =
    AddEvent(
        id = id,
        nama = nama,
        jenis = Jenis,
        harga = harga
    )

fun Laptop.toUIStateLaptop(): AddUIState = AddUIState(
    addEvent = this.toDetailLaptop()
)

data class HomeUIState(
    val listKontak: List<Laptop> = listOf(),
    val dataLength: Int = 0
)