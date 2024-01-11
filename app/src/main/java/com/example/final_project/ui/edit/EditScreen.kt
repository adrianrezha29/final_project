package com.example.final_project.ui.edit

import com.example.final_project.navigation.DestinasiNavigasi

object EditDestination : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes ="Edit Laptop"
    const val kontakId = "itemId"
    val routeWithArgs = "${EditDestination.route}/{$kontakId}"
}