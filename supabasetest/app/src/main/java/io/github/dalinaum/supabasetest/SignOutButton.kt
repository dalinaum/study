package io.github.dalinaum.supabasetest

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.launch

@Composable
fun SignOutButton() {
    val coroutineScope = rememberCoroutineScope()

    val onClick: () -> Unit = {
        coroutineScope.launch {
            supabase.auth.signOut()
        }
    }

    Button(
        onClick = onClick,
    ) {
        Text("Sign Out")
    }
}