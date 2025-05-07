package com.harry.sokomart.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewModel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.billy.taskflow.data.UserDatabase
import com.billy.taskflow.repository.UserRepository
import com.billy.taskflow.resipitory.UserRepository
import com.billy.taskflow.ui.screens.AddTask.AddTaskScreen
import com.billy.taskflow.ui.screens.MainActivity.MainActivity
import com.billy.taskflow.ui.screens.auth.LoginScreen
import com.billy.taskflow.ui.screens.auth.RegisterScreen
import com.billy.taskflow.ui.screens.splash.SplashScreen
import com.billy.taskflow.ui.screens.taskDetail.TaskDetailScreen
import com.billy.taskflow.ui.screens.tasklist.TaskListItem
import com.billy.taskflow.viewmodel.AuthViewModel
import com.billy.taskflow.viewmodel.ProductViewModel
import com.harry.sokomart.navigation.ROUT_MAIN
import com.harry.sokomart.ui.screens.auth.LoginScreen


@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_REGISTER,

) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUT_MAIN) {
            MaterialTheme {(navController)}
        }
        composable(ROUT_ADD) {
            AddTaskScreen{ (navController)}
        }
        composable(ROUT_DETAIL) {
            TaskDetailScreen {(navController)}
        }
        composable(ROUT_LIST) {
           TaskListItem{(navController)}
        }



        }

        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(route = ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_MAIN) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }
    }
}



