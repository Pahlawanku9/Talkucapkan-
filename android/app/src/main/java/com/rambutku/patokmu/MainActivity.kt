package com.rambutku.patokmu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.rambutku.patokmu.ui.create.CreateScreen
import com.rambutku.patokmu.ui.job.JobScreen
import com.rambutku.patokmu.ui.theme.TalkucapkanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TalkucapkanTheme {
                var currentJobId by remember { mutableStateOf<String?>(null) }
                
                if (currentJobId == null) {
                    CreateScreen(onJobStarted = { jobId ->
                        currentJobId = jobId
                    })
                } else {
                    JobScreen(jobId = currentJobId!!, onDone = {
                        currentJobId = null
                    })
                }
            }
        }
    }
}
