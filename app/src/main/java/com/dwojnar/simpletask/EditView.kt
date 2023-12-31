package com.dwojnar.simpletask

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dwojnar.simpletask.models.TodoTask
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditView(todoTask: TodoTask, editTodoTask: (editedTodoTask: TodoTask) -> Unit, goBack: () -> Unit){
    val todoTaskName = remember { mutableStateOf(todoTask.name) }

    Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Enter task name", color = Color.White, fontSize = 30.sp)
            TextField(
                value = todoTaskName.value,
                onValueChange = { todoTaskName.value = it },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .border(3.dp, Color.White),
                colors = TextFieldDefaults.textFieldColors(Color.White),
                singleLine = true,
            )
            Button(onClick = {
                val newTodoTask = TodoTask(
                    uid = todoTask.uid,
                    name = todoTaskName.value,
                    done = todoTask.done,
                )
                editTodoTask(newTodoTask)
                goBack()
            }, enabled = todoTaskName.value.isNotEmpty(),modifier = Modifier
                .size(70.dp)
                .border(3.dp, Color.White, RoundedCornerShape(100)),
                colors = ButtonDefaults.buttonColors(Color.Transparent)) {
                Icon(painter = painterResource(id = R.drawable.check_solid), contentDescription = "add", tint = Color.White)
            }
        }
    }
}