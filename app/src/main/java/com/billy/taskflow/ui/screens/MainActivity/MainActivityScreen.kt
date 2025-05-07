package com.billy.taskflow.ui.screens.MainActivity

import android.R.attr.type
import com.billy.taskflow.ui.screens.tasklist.TaskListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.billy.taskflow.ui.screens.AddTaskScreen
import com.billy.taskflow.ui.screens.TaskDetailScreen
import com.billy.taskflow.ui.screens.TaskListScreen

data class Task(
    val id: Int,
    val title: String,
    val description: String = ""
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var tasks by remember { mutableStateOf(listOf<Task>()) }
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "list") {
                composable("list") {
                    TaskListScreen(
                        tasks = tasks,
                        onTaskClick = { taskId ->
                            navController.navigate("detail/$taskId")
                        },
                        onAddClick = {
                            navController.navigate("add")
                        }
                    )
                }
                composable("add") {
                    AddTaskScreen(
                        onAddTask = { newTask ->
                            tasks = tasks + newTask
                            navController.popBackStack()
                        },
                        onCancel = {
                            navController.popBackStack()
                        }
                    )
                }
                composable(
                    "detail/{taskId}",
                    arguments = listOf(navArgument("taskId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val taskId = backStackEntry.arguments?.getInt("taskId") ?: return@composable
                    val task = tasks.find { it.id == taskId }
                    if (task != null) {
                        TaskDetailScreen(
                            task = task,
                            onDelete = {
                                tasks = tasks.filter { it.id != task.id }
                                navController.popBackStack()
                            },
                            onUpdate = { updatedTask ->
                                tasks = tasks.map { if (it.id == updatedTask.id) updatedTask else it }
                                navController.popBackStack()
                            },
                            onCancel = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}