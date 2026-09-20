package com.rambutku.patokmu

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

data class Rumah(val judul: String, val harga: String, val lokasi: String, val deskripsi: String)

class MainActivity : ComponentActivity() {
    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale("id", "ID")
            }
        }
        setContent {
            MaterialTheme {
                val listRumah = listOf(
                    Rumah("Minimalis 2 Lantai", "Rp 350 Juta", "Pedamaran, OKI", "Rumah minimalis 2 lantai di Pedamaran Ogan Komering Ilir, luas tanah 120 meter, harga 350 juta nego"),
                    Rumah("Subsidi Type 36", "Rp 180 Juta", "Kayuagung", "Rumah subsidi type 36 di Kayuagung, harga 180 juta, siap huni"),
                    Rumah("Mewah Hook", "Rp 850 Juta", "Palembang", "Rumah mewah posisi hook di Palembang, luas tanah 250 meter, harga 850 juta")
                )
                RumahBisaNgomongApp(listRumah) { teks ->
                    tts?.speak(teks, TextToSpeech.QUEUE_FLUSH, null, null)
                }
            }
        }
    }
    override fun onDestroy() {
        tts?.stop(); tts?.shutdown(); super.onDestroy()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RumahBisaNgomongApp(list: List<Rumah>, onSpeak: (String) -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("🔊 Talkucapkan Rumah", fontWeight = FontWeight.Bold) }) }) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(list) { rumah ->
                Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(6.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("🏠 ${rumah.judul}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(rumah.lokasi, color = MaterialTheme.colorScheme.primary)
                        Text(rumah.harga, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(onClick = { onSpeak(rumah.deskripsi) }, modifier = Modifier.weight(1f)) {
                                Text("🔊 Ucapkan")
                            }
                            OutlinedButton(onClick = {}, modifier = Modifier.weight(1f)) {
                                Text("💬 WA")
                            }
                        }
                    }
                }
            }
        }
    }
}
