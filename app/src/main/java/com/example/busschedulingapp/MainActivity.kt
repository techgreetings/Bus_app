package com.example.busschedulingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.busschedulingapp.data.Bus
import com.example.busschedulingapp.data.buses
import com.example.busschedulingapp.ui.theme.BusSchedulingAppTheme
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusSchedulingAppTheme {
                 Surface(
                        modifier = Modifier.fillMaxSize()
                        ) {
                    BusApp()
                }
            }
        }
    }
}

@Composable
fun BusApp() {
    Scaffold (topBar={ BusTopAppBar()}
    ){ it ->

        LazyColumn(contentPadding = it) {
            items(buses) {
                BusItem(
                    bus = it,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }
}

@Composable
fun BusItem(bus: Bus,
            modifier:Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded)  MaterialTheme.colorScheme.tertiaryContainer
                      else MaterialTheme.colorScheme.primaryContainer
    )

    Card(modifier = modifier) {
        Column(modifier = Modifier.animateContentSize (animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium
        )).background(color =color)) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_small))
//                 .padding(top = dimensionResource(id = R.dimen.padding_large))
            ) {
                BusIcon(bus.ImageResourceId)
                BusInfo(bus.name, bus.description)
                Spacer(modifier = Modifier.weight(1f))
                BusItemButton(expanded = expanded, onClick = { expanded =!expanded })
            }
            if (expanded) {
                BusDetail(
                    bus.details,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(id = R.dimen.padding_medium),
                        top = dimensionResource(id = R.dimen.padding_small),
                        bottom = dimensionResource(id = R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}
@Composable
private fun BusItemButton(
    expanded: Boolean,
    onClick:() -> Unit,
    modifier: Modifier = Modifier
){
    IconButton(onClick = onClick,
        modifier=Modifier) {
        Icon(
            imageVector = if(expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(id = R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun BusDetail(
    @StringRes busDetail:Int,
    modifier: Modifier =Modifier
){
    Column(modifier = modifier) {
           Text(text = stringResource(id = R.string.about), style = MaterialTheme.typography.labelSmall)
           Text(text = stringResource(busDetail), style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun BusIcon(
    @DrawableRes busIcon: Int,
    modifier: Modifier = Modifier
)
{
Image(
    modifier = modifier
        .size(100.dp)
        .padding(dimensionResource(id = R.dimen.padding_small))
        .clip(MaterialTheme.shapes.small)
    ,painter = painterResource(id = busIcon), contentDescription = null, contentScale = ContentScale.Crop)

}

@Composable
fun BusInfo(
    @StringRes busName:Int,
    @StringRes busDecs :Int,
    modifier: Modifier = Modifier
){
Column(modifier = modifier) {
    Text(
        text = stringResource(id = busName),
        style = MaterialTheme.typography.displayMedium,
    modifier = Modifier
        .padding(top = dimensionResource(id = R.dimen.padding_small))
    )
    Text(
        text = stringResource(id = busDecs),
        style = MaterialTheme.typography.bodyLarge)
}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        modifier = modifier
            .padding(vertical = 14.dp)
            .padding(bottom = 4.dp),
         title = {
         Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.image_size))
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clip(RoundedCornerShape(24.dp)),
             painter = painterResource(id = R.drawable.bus_logo), contentDescription = null,
                contentScale = ContentScale.Crop,

            )
             Text(text = stringResource(id = R.string.app_name),
                 style = MaterialTheme.typography.displayLarge,
             )
         }
}
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusSchedulingAppTheme {
        BusApp()
    }
}

@Preview
@Composable
fun BusDarkThemePreview(){
    BusSchedulingAppTheme(darkTheme = true){
        BusApp()
    }
}