package io.github.dalinaum.supabasetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.dalinaum.supabasetest.ui.theme.SupaBaseTestTheme
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

val supabase = createSupabaseClient(
    supabaseUrl = "https://ewyskhgaoqvtuhusjrrl.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImV3eXNraGdhb3F2dHVodXNqcnJsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjYzNjY0ODIsImV4cCI6MjA0MTk0MjQ4Mn0.KScx0l_T4DFSO94_PFfFCnreoe5L78A6OsBsdbQPMjI"
) {
    install(Postgrest)
    install(Auth)
}

@Serializable
data class Country(
    val id: Int,
    val name: String,
)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SupaBaseTestTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Test")
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .background(MaterialTheme.colorScheme.background),
                ) { padidng ->
                    LaunchedEffect(Unit) {
                        supabase.auth.retrieveUserForCurrentSession()
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues = padidng)
                    ) {
                        val sessionStatus by supabase.auth.sessionStatus.collectAsState()
                        when (sessionStatus) {
                            is SessionStatus.Authenticated -> {
                                Text("Authenticated")
                            }

                            is SessionStatus.NotAuthenticated -> {
                                Text("Not Authenticated")
                            }

                            else -> {
                                Text("Else")
                            }
                        }
                        GoogleSignInButton()
                        SignOutButton()
                        CountriesList()
                    }
                }
            }
        }
    }
}

@Composable
fun CountriesList() {
    var countries by remember { mutableStateOf<List<Country>>(listOf()) }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            countries = supabase.from("countries")
                .select().decodeList()
        }
    }
    LazyColumn {
        items(
            countries,
            key = { country -> country.id }
        ) { country ->
            Text(
                country.name,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}