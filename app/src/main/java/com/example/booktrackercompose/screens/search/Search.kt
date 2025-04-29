package com.example.booktrackercompose.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booktrackercompose.R
import com.example.booktrackercompose.api.Book


@Composable
fun TopTextField(
    value: String,
    onChangeValue: (String) -> Unit
){

    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    Row(
        modifier = Modifier
            .padding(top = 24.dp)
            .padding(horizontal = 16.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(5.dp),
                color = colorResource(R.color.grayDivider)
            ),
        verticalAlignment = Alignment.CenterVertically
    ){
        Row(
            modifier = Modifier.padding(vertical = 10.dp).weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(R.drawable.search_img),
                contentDescription = "Поиск",
                modifier = Modifier.padding(start = 10.dp).size(22.dp)
            )

            Spacer(
                Modifier.width(8.dp)
            )

            BasicTextField(
                value = value,
                onValueChange = onChangeValue,

                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),

                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(
                        Font(R.font.inter_regular)
                    ),
                    color = colorResource(R.color.appBlack)
                ),
                decorationBox = { innerTextField ->
                    if (value.isBlank()){
                        Text(
                            text = "Поиск...",
                            style = TextStyle(
                                color = colorResource(R.color.gray),
                                fontSize = 16.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.inter_regular)
                                )
                            )
                        )
                    }
                    innerTextField()
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
        }
        if(value.isNotBlank()){
            Image(
                painter = painterResource(R.drawable.edit_text_clear_img),
                contentDescription = "Стереть",
                Modifier.padding(8.dp)
                    .clickable (
                        onClick = { onChangeValue("")},
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ),
                colorFilter = ColorFilter.tint(colorResource(R.color.gray))
            )
        }
    }
}


@Composable
fun SearchResults(books: List<Book>, searchStatus: SearchStatus){
    when(searchStatus){
        SearchStatus.SUCCESS -> SuccessBookList(books)
        SearchStatus.LOADING -> {ProgressBar()}
        SearchStatus.NOT_FOUND -> NotFound()
        SearchStatus.ERROR -> Error()
        else -> {}
    }
}

@Composable
private fun ProgressBar(){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.4f)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp),
            color = colorResource(R.color.appBlack),
            strokeWidth = 3.dp
        )
    }
}

@Composable
private fun Error(){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.4f)
    ) {
        Text(
            text = "Проблемы с соединением...",
            style = TextStyle(
                fontSize = 22.sp,
                color = colorResource(R.color.appBlack),
                fontFamily = FontFamily(
                    Font(R.font.opensans_regular)
                )
            )
        )
    }
}

@Composable
private fun NotFound(){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.4f)
    ) {
        Text(
            text = "Ничего не найдено =(",
            style = TextStyle(
                fontSize = 22.sp,
                color = colorResource(R.color.appBlack),
                fontFamily = FontFamily(
                    Font(R.font.opensans_regular)
                )
            )
        )
    }
}

@Composable
private fun SuccessBookList(books: List<Book>){
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(books){ item ->
            Row(
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                AsyncImage(
                    model = item.imageLinks?.maybeNormImage?.replace("http","https"),
                    contentDescription = "привет",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(width = 80.dp, height = 120.dp)
                        .clip(RoundedCornerShape(5.dp))

                )
                Spacer(
                    Modifier.width(8.dp)
                )
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = item.title?:"Неизвестно",
                        maxLines = 1,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = colorResource(R.color.appBlack),
                            fontFamily = FontFamily(
                                Font(R.font.opensans_regular)
                            ),
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = when{
                            item.authors.isNullOrEmpty() -> "Неизвестно"
                            else -> item.authors.joinToString()
                        },
                        maxLines = 1,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = colorResource(R.color.gray),
                            fontFamily = FontFamily(
                                Font(R.font.opensans_regular)
                            )
                        ),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "Страниц: ${item.pageCount?:"Неизвестно"}",
                        maxLines = 1,
                        style = TextStyle(
                            fontSize = 15.sp,
                            color = colorResource(R.color.appBlack),
                            fontFamily = FontFamily(
                                Font(R.font.opensans_regular)
                            )
                        ),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

