package com.example.jobaggregator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdRequest
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)

        setContent {
            JobAggregatorTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    JobScreen()
                }
            }
        }
    }
}

@Composable
fun JobScreen(vm: JobViewModel = viewModel()) {
    val jobs by vm.jobs.collectAsState()
    Column {
        AndroidView(factory = { context ->
            AdView(context).apply {
                adSize = AdSize.BANNER
                adUnitId = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxx" // TODO replace
                loadAd(AdRequest.Builder().build())
            }
        })
        LazyColumn {
            items(jobs.size) { index ->
                Text(text = jobs[index].title)
            }
        }
    }
}

data class JobPosting(val title: String, val approved: Boolean)

class JobViewModel : ViewModel() {
    private val _jobs = MutableStateFlow<List<JobPosting>>(emptyList())
    val jobs: StateFlow<List<JobPosting>> = _jobs

    init {
        fetchJobs()
    }

    private fun fetchJobs() {
        // TODO: replace with real implementation to fetch from sources
        _jobs.value = listOf(
            JobPosting("Sample Job 1", approved = true),
            JobPosting("مثال وظيفة", approved = true)
        )
    }
}
