package com.example.final_project.model

data class Laptop(
    val id: String,
    val nama: String,
    val Jenis: String,
    val harga: String
){
    constructor(): this("","","","")
}