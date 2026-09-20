package com.rambutku.patokmu.ui.create

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.speech.tts.TextToSpeech
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

@Composable
fun CreateScreen(onJobStarted: (String) -> Unit) {
    val context = LocalContext.current
    var text by remember { mutableStateOf("Halo, ini Talkucapkan dari Pedamaran!") }
    var tts by remember { mutableStateOf<TextToSpeech?>(null) }
    
    DisposableEffect(Unit) {
        tts = TextToSpeech(context) { tts?.language = Locale("id","ID") }
        onDispose { tts?.shutdown() }
    }

    Column(Modifier.padding(16.dp)) {
        Text("TalkUcapkan - Versi Rumah")
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value = text, onValueChange = { text = it }, modifier = Modifier.fillMaxWidth().height(120.dp))
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            onJobStarted("test_123")
        }, modifier = Modifier.fillMaxWidth()) { Text("Ucapkan Sekarang") }
    }
}
