package com.chamwari.tech.xitique

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.chamwari.tech.xitique.ui.theme.XitiqueTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            XitiqueTheme {
                // A surface container using the 'background' color from the theme
                val state = viewModel.state.collectAsState()

                LazyColumn {
                    items(
                        state.value.signedUsers.size
                    ) { index ->
                        Greeting(
                            name = state.value.signedUsers[index],
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Text(
        text = "Hello $name!",
        modifier = modifier.clickable {
            onClick.invoke()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    XitiqueTheme {
        Greeting("Android")
    }
}