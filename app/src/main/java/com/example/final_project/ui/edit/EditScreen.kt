package com.example.final_project.ui.edit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.final_project.navigation.DestinasiNavigasi
import com.example.final_project.ui.LaptopTopAppBar
import com.example.final_project.ui.PenyediaViewModel
import com.example.final_project.ui.add.EntryBody
import com.example.final_project.ui.detail.DetailDestination.laptopId
import kotlinx.coroutines.launch

object EditDestination : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes ="Edit Laptop"
    const val laptopId = "itemId"
    val routeWithArgs = "${EditDestination.route}/{$laptopId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            LaptopTopAppBar(
                title =EditDestination.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryBody(
            addUIState = viewModel.laptopUiState,
            onSiswaValueChange = viewModel::updateUIState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateLaptop()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}