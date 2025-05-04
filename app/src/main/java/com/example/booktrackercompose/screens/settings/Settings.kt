package com.example.booktrackercompose.screens.settings

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booktrackercompose.R


@Composable
fun SettingsScreen(){
    val viewModel: SettingsViewModel = hiltViewModel()
    SettingsScreen(viewModel)
}

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel
){
    val switchValue by viewModel.isDarkTheme
    val onClickSwitch = viewModel::setDarkTheme
    val colorScheme = MaterialTheme.colorScheme

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Настройки",
            style = TextStyle(
                fontFamily = FontFamily(
                    Font(R.font.opensans_regular),
                ),
                fontWeight = FontWeight.Bold,
                color = colorScheme.primary,
                fontSize = 22.sp
            ),
        )
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Светлая/Темная тема",
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.opensans_regular),
                    ),
                    color = colorScheme.primary,
                    fontSize = 18.sp
                )
            )
            Switch(
                checked = switchValue,
                onCheckedChange = onClickSwitch,
                modifier = Modifier.scale(0.65f)
            )
        }
        Button(
            onClick = {
                val intent = viewModel.getShareIntent()
                context.startActivity(intent)
            },
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = colorScheme.primary,
                    shape = RoundedCornerShape(5.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.share_img),
                    contentDescription = "Поделиться",
                    modifier = Modifier.size(18.dp),
                    colorFilter = ColorFilter.tint(colorScheme.primary)
                )
                Spacer(
                    Modifier.width(8.dp)
                )
                Text(
                    text = "Поделиться приложением",
                    style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.opensans_regular)
                        ),
                        fontSize = 18.sp,
                        color = colorScheme.primary
                    )
                )
            }
        }
        Button(
            onClick = {
                val intent = viewModel.getEmailIntent()
                context.startActivity(intent)
            },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = colorScheme.primary,
                    shape = RoundedCornerShape(5.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.mail_img),
                    contentDescription = "Поделиться",
                    modifier = Modifier.size(18.dp),
                    colorFilter = ColorFilter.tint(colorScheme.primary)
                )
                Spacer(
                    Modifier.width(8.dp)
                )
                Text(
                    text = "Напишите нам",
                    style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.opensans_regular)
                        ),
                        fontSize = 18.sp,
                        color = colorScheme.primary
                    )
                )
            }
        }
    }
}