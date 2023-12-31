package com.dwojnar.simpletask

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationActivity() {
    val navController = rememberNavController()
    val mainViewModel = viewModel<MainViewModel>()
    mainViewModel.loadTodoTasks()
    NavHost(
        navController = navController,
        startDestination = "mainMenu"
    ) {
        composable("mainMenu") {
            MainMenuView(
                todoTasks = mainViewModel.todoTasks,
                clickAdd = { navController.navigate("addMenu") },
                clickRemove = { uid -> mainViewModel.removeTodoTask(uid) },
                clickEdit = { todoTask -> mainViewModel.currentTodoTask.value = todoTask
                    navController.navigate("editMenu") },
                clickNextDay = { navController.navigate("nextDayMenu") },
                clickSetTaskDone = { todoTask -> mainViewModel.updateTodoTask(todoTask) }
            )
        }
        composable("addMenu") {
            AddView(
                addTodoTask = { todoTask -> mainViewModel.addTodoTask(todoTask) },
                goBack = { navController.popBackStack() }
            )
        }
        composable("editMenu") {
            EditView(
                todoTask = mainViewModel.currentTodoTask.value,
                editTodoTask = { todoTask -> mainViewModel.updateTodoTask(todoTask) },
                goBack = { navController.popBackStack() }
            )
        }
        composable("nextDayMenu") {
            NextDayView(
                startNewDay = { mainViewModel.setAllTodoTasksUndone() },
                goBack = { navController.popBackStack() }
            )
        }
    }
}