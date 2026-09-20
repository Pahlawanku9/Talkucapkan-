package com.rambutku.patokmu.ui.job

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun JobScreen(jobId: String, onDone: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Video Selesai Dibuat!", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))
        Text("ID Job: $jobId")
        Spacer(Modifier.height(8.dp))
        Text("Teks tadi sudah diucapkan pakai suara HP.")
        Spacer(Modifier.height(24.dp))
        Button(onClick = onDone, modifier = Modifier.fillMaxWidth()) {
            Text("Buat Lagi")
        }
        Text("Nanti di langkah 3 kita bikin bisa save jadi video beneran ya Mas.", modifier = Modifier.padding(top=16.dp))
    }
}
