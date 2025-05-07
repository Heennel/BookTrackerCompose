package com.example.booktrackercompose.screens.authorization.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.booktrackercompose.R
import com.example.booktrackercompose.navigation.Screen


@Composable
fun LogInScreen(navController: NavController){
    LogInScreen(
        viewModel = hiltViewModel(),
        navController = navController
    )
}
@Composable
private fun LogInScreen(
    viewModel: LogInViewModel,
    navController: NavController
){

    val email by viewModel.email
    val password by viewModel.password

    val emailUpdater = viewModel::updateEmail
    val passwordUpdater = viewModel::updatePassword

    val navigationFlag by viewModel.navigateFlag

    LaunchedEffect(navigationFlag) {
        if(navigationFlag) {
            viewModel.dropFlag()
            navController.navigate(Screen.Library.route)
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        WelcomeMessage()

        AuthorizationButton()

        Row(
            modifier = Modifier.padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Spacer(
                Modifier
                    .height(2.dp)
                    .width(80.dp)
                    .background(
                        colorResource(R.color.grayDivider),
                        shape = RoundedCornerShape(2.dp)
                    ),
            )
            Text(
                text = "Или",
                color = colorResource(R.color.grayDivider),
                fontSize = 16.sp,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.inter_bold))
                ),
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Spacer(
                Modifier
                    .height(2.dp)
                    .width(80.dp)
                    .background(colorResource(R.color.grayDivider))
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
        ) {
            CreateTextField(
                value = email,
                onValueChange = emailUpdater,
                basicText = "Ваша электронная почта",
                image = painterResource(R.drawable.mail_img),
                viewModel = viewModel,
                isEmailField = true
            )
            CreateTextField(
                value = password,
                onValueChange = passwordUpdater,
                basicText = "Ваш пароль",
                image = painterResource(R.drawable.lock_img),
                viewModel = viewModel,
                isPasswordField = true
            )
        }

        Button(
            onClick = {
                viewModel.auth()
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 12.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.lessLightBlue),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Войти в аккаунт",
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.inter_bold)
                    ),
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Composable
private fun AuthorizationButton(){
    Button(
        onClick = {},
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.redBackground),
            contentColor = colorResource(R.color.redText)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(R.drawable.google_img), contentDescription = "Гугл",
                Modifier.size(18.dp),
                colorFilter = ColorFilter.tint(colorResource(R.color.redText))
            )
            Text(
                text = "Продолжить через Google",
                modifier = Modifier.padding(start = 6.dp),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Composable
private fun WelcomeMessage(){
    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxHeight(0.33f)
            .fillMaxWidth()
            .padding(bottom = 40.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "Добро пожаловать!",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = colorResource(id = R.color.appBlack),
            fontFamily = FontFamily(
                Font(R.font.inter_bold)
            )
        )
        Spacer(
            Modifier.height(8.dp)
        )
        Text(
            text = "Войдите, чтобы создать свою библиотеку",
            fontSize = 16.sp,
            color = colorResource(id = R.color.gray),
            fontFamily = FontFamily(
                Font(R.font.inter_regular)
            )
        )
    }
}

@Composable
private fun CreateTextField(
    value: String,
    onValueChange: (String) -> Unit,
    basicText: String,
    image: Painter,
    isPasswordField: Boolean = false,
    isEmailField: Boolean = false,
    viewModel: LogInViewModel
){
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    val correctPassword by viewModel.isPasswordValid
    val correctMail by viewModel.isEmailValid

    Column{
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp)
                .border(
                    color = if((!correctPassword && isPasswordField)
                        || (!correctMail && isEmailField)
                    )
                        colorResource(R.color.red)
                    else colorResource(R.color.grayDivider),
                    width = 1.dp,
                    shape = RoundedCornerShape(8.dp),

                    ),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = image,
                        contentDescription = "Пароль",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequester),
                        textStyle = TextStyle(
                            fontFamily = FontFamily(Font(R.font.inter_regular)),
                            fontSize = 16.sp,
                            color = colorResource(R.color.appBlack)
                        ),
                        decorationBox = { innerTextField ->
                            if (value.isEmpty()) {
                                Text(
                                    text = basicText,
                                    color = colorResource(R.color.gray),
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_regular))
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
                        ),
                    )
                }
            }
            if(value.isNotBlank()) {
                Image(
                    painter = painterResource(R.drawable.edit_text_clear_img),
                    contentDescription = "Стереть",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(24.dp)
                        .clickable(
                            onClick = { onValueChange("") },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                )
            }
        }
        if(isPasswordField && !correctPassword){
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = "6 и более символов",
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.opensans_regular)
                    ),
                    fontSize = 12.sp,
                    color = colorResource(R.color.redText)
                )
            )
        }
    }
}

