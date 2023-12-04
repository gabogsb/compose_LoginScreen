package co.gabogsb.loginscreen.Screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.gabogsb.loginscreen.R
import co.gabogsb.loginscreen.components.TabLayout


@Composable
fun AuthScreen(navController: NavController) {
    val context = LocalContext.current
    val SharedPreferences = context.getSharedPreferences("main", Context.MODE_PRIVATE)


    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val selectedTab = remember {
            mutableIntStateOf(value = 0)
        }

        TabLayout(
            selectedIndex = selectedTab.intValue,
            items = listOf(
                "Sign in" to {
                    SignIn(
                        navController, sharedPreferences = SharedPreferences
                    )
                },
                "Sign Up" to {
                    SignUp(
                        navController, sharedPreferences = SharedPreferences
                    )
                }
            ),
            onTabClick = {
                selectedTab.intValue = it
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn(
    navController: NavController,
    sharedPreferences: SharedPreferences
) {
    val rememberMeChecked = remember {
        mutableStateOf(false)
    }

    val email = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    val showPassword = remember {
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        AppTextField(
            value = email.value,
            onValueChange = {
                email.value = it
            },
            label = "Email Address"
        )
        Spacer(modifier = Modifier.height(16.dp))
        AppTextField(
            value = password.value,
            onValueChange = {
                password.value = it
            },
            label = "Password",
            visualTransformation = if (showPassword.value)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailing = {
                Icon(
                    modifier = Modifier.clickable {
                        showPassword.value = !showPassword.value
                    },
                    painter = painterResource(
                        id = if (showPassword.value)
                            R.drawable.if_eye_off else R.drawable.ic_eye_open
                    ),
                    contentDescription = null
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                "Forgot Password?",
                color = Color.Gray,
                fontSize = 11.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                Checkbox(checked = rememberMeChecked.value, onCheckedChange = {
                    rememberMeChecked.value = it
                })
            }
            Text(
                "Remember Me",
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            onClick = {
                sharedPreferences.edit().apply {
                    putBoolean("loggedIn", true)
                    putString("email", email.value)
                }
                    .apply()
                navController.navigate("home") {
                    popUpTo(0)
                }
            },
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Login")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(
    navController: NavController,
    sharedPreferences: SharedPreferences
) {
    val rememberMeChecked = remember {
        mutableStateOf(false)
    }

    val email = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    val passwordRepeat = remember {
        mutableStateOf("")
    }

    val showPassword = remember {
        mutableStateOf(false)
    }
    val showPasswordRepeat = remember {
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        AppTextField(
            value = email.value,
            onValueChange = {
                email.value = it
            },
            label = "Email Address"
        )
        Spacer(modifier = Modifier.height(16.dp))
        AppTextField(
            value = password.value,
            onValueChange = {
                password.value = it
            },
            label = "Password",
            visualTransformation = if (showPassword.value)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailing = {
                Icon(
                    modifier = Modifier.clickable {
                        showPassword.value = !showPassword.value
                    },
                    painter = painterResource(
                        id = if (showPassword.value)
                            R.drawable.if_eye_off else R.drawable.ic_eye_open
                    ),
                    contentDescription = null
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        AppTextField(
            value = passwordRepeat.value,
            onValueChange = {
                passwordRepeat.value = it
            },
            label = "Password",
            visualTransformation = if (showPasswordRepeat.value)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailing = {
                Icon(
                    modifier = Modifier.clickable {
                        showPasswordRepeat.value = !showPasswordRepeat.value
                    },
                    painter = painterResource(
                        id = if (showPasswordRepeat.value)
                            R.drawable.if_eye_off else R.drawable.ic_eye_open
                    ),
                    contentDescription = null
                )
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            onClick = {
                sharedPreferences.edit().apply {
                    putBoolean("loggedIn", true)
                    putString("email", email.value)
                }
                    .apply()
                navController.navigate("home") {
                    popUpTo(0)
                }
            },
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Register")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailing: (@Composable () -> Unit)? = null
) {
    Column {
        Text(label)
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .shadow(3.dp, RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                visualTransformation = visualTransformation,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                trailingIcon = trailing
            )
        }
    }


}