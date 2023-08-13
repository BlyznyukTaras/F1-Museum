package com.litekreu.museum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.litekreu.museum.ui.theme.MuseumTheme
import com.litekreu.museum.ui.theme.Purple40
import com.litekreu.museum.ui.theme.regular
import com.litekreu.museum.ui.theme.torque
import com.litekreu.museum.ui.theme.turbo
import java.util.Locale

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuseumTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ImageMuseum()
                }
            }
        }
    }
}

val imgList = listOf(
    MagnificentPicture(R.drawable.ladno_norris, "Lando Norris",
        f1Team("McLaren"), 2023),
    MagnificentPicture(R.drawable.vettel, "Sebastian Vettel",
        f1Team("Aston Martin Aramco"),  2022),
    MagnificentPicture(R.drawable.checo_racing_point, "Sergio Perez",
        f1Team("BWT Racing Point"), 2020),
    MagnificentPicture(R.drawable.hulk, "Nico Hülkenberg",
        f1Team("Renault"), 2019)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageMuseum(modifier: Modifier = Modifier) {
    var index by remember { mutableStateOf(0) }
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    fontFamily = regular
                )
            },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = null,
                        tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Purple40,
                titleContentColor = Color.White
            )
        )
    }
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(bottom = 120.dp)
    ) {
        Image(
            painterResource(imgList[index].image),
            contentDescription = null,
            modifier = Modifier
                .border(width = 20.dp, color = Color.White)
                .shadow(16.dp)
                .size(width = 270.dp, height = 450.dp)
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(modifier = Modifier.padding(bottom = 24.dp)) {
            Column(
                modifier = Modifier
                    .background(colorResource(R.color.info_color))
                    .padding(12.dp)
            ) {
                Text(
                    text = imgList[index].name,
                    fontFamily = turbo,
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp
                )
                Row {
                    Text(
                        text = imgList[index].team,
                        fontFamily = torque,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "(${imgList[index].year})",
                        fontFamily = regular,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Button(
                onClick = { index = valueChange(index, false) },
                enabled = index != 0,
                modifier = Modifier
                    .alpha(
                        if (index == 0)
                            0.0f else 1.0f
                    )
                    .width(140.dp)
            ) {
                Text(
                    text = stringResource(R.string.prev),
                    fontFamily = regular
                )
            }
            Button(
                onClick = { index = valueChange(index, true) },
                enabled = index != imgList.size - 1,
                modifier = Modifier
                    .alpha(
                        if (index == imgList.size - 1)
                            0.0f else 1.0f
                    )
                    .width(140.dp)
            ) {
                Text(
                    text = stringResource(R.string.next),
                    fontFamily = regular
                )
            }
        }
    }
}

fun f1Team(string: String) = "$string F1 Team"

fun valueChange(id: Int, isIncrementing: Boolean): Int {
    return when (isIncrementing) {
        true -> id + 1
        false -> id - 1
    }
}

@Composable
fun TestSwitch(modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Switch(checked = checked, onCheckedChange = { checked = it })
        Text(
            text = checked.toString()
                .replaceFirstChar { if (it.isLowerCase())
                    it.titlecase(Locale.getDefault()) else it.toString() },
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}

@Preview (
    showSystemUi = true,
    showBackground = true
)
@Composable
fun F1Preview() {
    ImageMuseum()
}