package com.chamwari.tech.xitique

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.chamwari.tech.xitique.presentation.EventSummaryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<EventSummaryViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { EventSummaryScreen() }
    }

    @Composable
    private fun EventSummaryScreen() {
        val state = viewModel.state.collectAsState()
        val context = LocalContext.current

        LazyColumn {
            items(
                state.value.signedUsers.size
            ) { index ->
                val name = state.value.signedUsers[index]

                EventTitle(
                    name = name
                ) {
                    Toast.makeText(context, "hello $name", Toast.LENGTH_SHORT).show()
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(1.dp)
                        .background(color = Color.Gray.copy(alpha = 0.5f))
                )
            }
        }
    }

    @Composable
    fun EventTitle(name: String, onClick: () -> Unit) {
        Box(modifier = Modifier
            .clickable {
                onClick.invoke()
            }
            .fillMaxWidth()
            .padding(16.dp)
            ) {
            Text(text = "Hello $name!")
        }
    }
}