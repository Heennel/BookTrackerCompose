package com.example.booktrackercompose.screens.authorization.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booktrackercompose.R
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.booktrackercompose.navigation.Screen

@Composable
fun RegistrationScreen(navController: NavController){
    RegistrationScreen(
        viewModel = hiltViewModel(),
        navController = navController
    )
}

@Composable
private fun RegistrationScreen(viewModel: RegistrationViewModel, navController: NavController){

    Column {
        val queryMail by viewModel.email
        val queryPassword by viewModel.password
        val queryPasswordAgain by viewModel.confirmPassword

        CreatingMessage()
        CreateTextField(
            value = queryMail,
            onValueChange = viewModel::updateEmail,
            basicText = "Введите почту",
            image = painterResource(R.drawable.mail_img),
            isEmailField = true,
            viewModel = viewModel
        )
        CreateTextField(
            value = queryPassword,
            onValueChange = viewModel::updatePassword,
            basicText = "Создайте пароль",
            image = painterResource(R.drawable.lock_img),
            isPasswordField = true,
            viewModel = viewModel
        )
        CreateTextField(
            value = queryPasswordAgain,
            onValueChange = viewModel::updateConfirmPassword,
            basicText = "Подтвердите пароль",
            image = painterResource(R.drawable.lock_img),
            isMatchField = true,
            viewModel = viewModel
        )
        SignUpButton(viewModel,navController)
    }
}

@Composable
private fun CreatingMessage(){
    Column(
        modifier = Modifier.systemBarsPadding().padding(top = 44.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Создайте аккаунт",
            style = TextStyle(
                fontSize = 32.sp,
                fontFamily = FontFamily(
                    Font(R.font.opensans_regular)
                ),
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.appBlack)
            )
        )
        Text(
            text = "Чтобы синхронизировать свою библиотеку с другими устройствами",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(
                    Font(R.font.opensans_regular)
                ),
                color = colorResource(R.color.appBlack)
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}

@Composable
private fun CreateTextField(
    value: String,
    onValueChange: (String) -> Unit,
    basicText: String,
    image: Painter,
    isMatchField: Boolean = false,
    isPasswordField: Boolean = false,
    isEmailField: Boolean = false,
    viewModel: RegistrationViewModel
){
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }


    val correctPassword by viewModel.isPasswordValid
    val matchPassword by viewModel.isPasswordsMatch
    val correctMail by viewModel.isEmailValid

    Column{
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp)
                .border(
                    color = if((!correctPassword && isPasswordField)
                        || (!matchPassword && isMatchField)
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

@Composable
private fun SignUpButton(
    viewModel: RegistrationViewModel,
    navController: NavController
){

    val isAllValid by viewModel.isAllValid
    val navigationFlag by viewModel.navigateFlag

    LaunchedEffect(navigationFlag) {
        if(navigationFlag) {
            viewModel.dropFlag()
            navController.navigate(Screen.LogIn.route)
        }
    }

    Button(
        onClick = viewModel::sign,
        enabled = isAllValid,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp)
            .fillMaxWidth(),

        colors = if (isAllValid) {
            ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.lessLightBlue),
                contentColor = Color.White,
            )
        } else {
            ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.gray),
                contentColor = colorResource(R.color.appBlack)
            )
        },
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Создать аккаунт",
            style = TextStyle(
                fontFamily = FontFamily(
                    Font(R.font.inter_bold)
                ),
                fontSize = 16.sp
            )
        )
    }
}