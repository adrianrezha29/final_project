package com.example.final_project.tampilan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.final_project.navigation.DestinasiNavigasi
import com.example.final_project.ui.add.DestinasiEntry
import com.example.final_project.ui.home.DestinasiMenu
import com.example.final_project.ui.theme.Final_projectTheme

object LoginDestination : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes ="Edit Laptop"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(
    navController: NavController,
    authviewmodel: authviewmodel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                authviewmodel.createUserWithEmailAndPassword(email, password) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Sign Up")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                navController.navigate(DestinasiMenu.route)
                authviewmodel.signInWithEmailAndPassword(email, password)},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Sign In")
        }
    }
}

