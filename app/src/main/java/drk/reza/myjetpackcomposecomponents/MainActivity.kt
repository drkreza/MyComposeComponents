package drk.reza.myjetpackcomposecomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import drk.reza.myjetpackcomposecomponents.combine.CombineViewModel
import drk.reza.myjetpackcomposecomponents.combine.CombineViewModel2
import drk.reza.myjetpackcomposecomponents.combine.MergeViewModel
import drk.reza.myjetpackcomposecomponents.combine.ZipViewModel
import drk.reza.myjetpackcomposecomponents.ui.theme.MyJetpackComposeComponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyJetpackComposeComponentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /* Greeting(
                         name = "Android",
                         modifier = Modifier.padding(innerPadding)
                     )*/

//                    combineApp1()
//                    combineApp2()
                    zipApp()
//                    mergeApp()


                }
            }
        }
    }

    @Composable
    private fun combineApp1(myViewModel: CombineViewModel = viewModel()) {
        val combinedData by myViewModel.combinedFlow.collectAsState("Loading ...")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = combinedData)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                myViewModel.updateData(
                    (0..10).random(),
                    listOf("Hello", "World", "Compose").random()
                )
            }) {
                Text(text = "Update Data")
            }
        }
    }

    @Composable
    fun combineApp2(myViewModel: CombineViewModel2 = viewModel()) {
        val combinedData by myViewModel.combinedFlow.collectAsState(initial = "Loading...")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = combinedData)
        }
    }

    @Composable
    fun zipApp(myViewModel: ZipViewModel = viewModel()) {
        val combinedData by myViewModel.combinedFlow.collectAsState(initial = "Loading...")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = combinedData)
        }
    }

    @Composable
    fun mergeApp(myViewModel: MergeViewModel = viewModel()) {
        val mergedData by myViewModel.mergedFlow.collectAsState(initial = "Loading...")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = mergedData)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyJetpackComposeComponentsTheme {
        Greeting("Android")
    }
}