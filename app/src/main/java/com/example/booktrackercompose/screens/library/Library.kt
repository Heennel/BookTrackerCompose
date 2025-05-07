package com.example.booktrackercompose.screens.library

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.booktrackercompose.R

@Composable
fun Library(navController: NavController){
    val colorScheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Библиотека",
            fontSize = 32.sp,
            color = colorScheme.primary,
            fontFamily = FontFamily(
                Font(R.font.opensans_regular)
            ),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.library_fragment_saved_img),
                contentDescription = "Сохраненные",
                colorFilter = ColorFilter.tint(colorResource(R.color.gray)),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(24.dp)
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Сохраненные",
                    style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.opensans_regular),
                        ),
                        fontSize = 16.sp
                    ),
                    color = colorScheme.primary
                )
                Text(
                    text = "0 в коллекции",
                    style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.opensans_regular),
                        ),
                        fontSize = 13.sp
                    ),
                    color = colorScheme.secondary,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Spacer(
                Modifier.weight(1f)
            )
            Image(
                painter = painterResource(R.drawable.settings_arrow_img),
                contentDescription = "гоуту",
                colorFilter = ColorFilter.tint(colorResource(R.color.gray)),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(24.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(colorScheme.secondary)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.favourite_img),
                contentDescription = "Любимое",
                colorFilter = ColorFilter.tint(colorResource(R.color.gray)),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(24.dp)
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Любимое",
                    style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.opensans_regular),
                        ),
                        fontSize = 16.sp
                    ),
                    color = colorScheme.primary
                )
                Text(
                    text = "0 в коллекции",
                    style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.opensans_regular),
                        ),
                        fontSize = 13.sp
                    ),
                    color = colorScheme.secondary,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Spacer(
                Modifier.weight(1f)
            )
            Image(
                painter = painterResource(R.drawable.settings_arrow_img),
                contentDescription = "гоуту",
                colorFilter = ColorFilter.tint(colorResource(R.color.gray)),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(24.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(colorScheme.secondary)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.readed_img),
                contentDescription = "Прочитано",
                colorFilter = ColorFilter.tint(colorResource(R.color.gray)),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(24.dp)
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Прочитано",
                    style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.opensans_regular),
                        ),
                        fontSize = 16.sp
                    ),
                    color = colorScheme.primary
                )
                Text(
                    text = "0 в коллекции",
                    style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.opensans_regular),
                        ),
                        fontSize = 13.sp
                    ),
                    color = colorScheme.secondary,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Spacer(
                Modifier.weight(1f)
            )
            Image(
                painter = painterResource(R.drawable.settings_arrow_img),
                contentDescription = "гоуту",
                colorFilter = ColorFilter.tint(colorResource(R.color.gray)),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(24.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(colorScheme.secondary)
        )
    }
}