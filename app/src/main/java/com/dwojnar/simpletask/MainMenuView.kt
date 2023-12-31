package com.dwojnar.simpletask

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dwojnar.simpletask.models.TodoTask

@Composable
fun MainMenuView(todoTasks: List<TodoTask>,
                 clickAdd: () -> Unit,
                 clickRemove: (uid: String) -> Unit,
                 clickEdit: (todoTask: TodoTask) -> Unit,
                 clickNextDay: () -> Unit,
                 clickSetTaskDone: (todoTask: TodoTask) -> Unit) {
    Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .background(Color.Black, RoundedCornerShape(0)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Text(
                    text = "SimpleTask",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontSize = 25.sp
                )
            }
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1F)) {
                items(todoTasks.size) { index ->
                    Row(modifier = Modifier
                        .height(90.dp)
                        .padding(top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth()
                        .clickable { todoTasks[index].done = !todoTasks[index].done
                            clickSetTaskDone(todoTasks[index]) }
                        .background(if (todoTasks[index].done) Color(0xFF0D6EFD) else Color.Gray , RoundedCornerShape(20)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween){
                        Text(
                            text = " ${todoTasks[index].name}",
                            textAlign = TextAlign.Left,
                            color = Color.White,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            fontSize = 25.sp
                        )
                        Row(){
                            Button(onClick = { clickEdit(todoTasks[index]) }, modifier = Modifier
                                .size(70.dp),
                                colors = ButtonDefaults.buttonColors(Color.Transparent)) {
                                Icon(painter = painterResource(id = R.drawable.pen_to_square_solid), tint = Color.White, contentDescription = "edit")
                            }
                            Button(onClick = { clickRemove(todoTasks[index].uid) }, modifier = Modifier
                                .size(70.dp),
                                colors = ButtonDefaults.buttonColors(Color.Transparent)) {
                                Icon(painter = painterResource(id = R.drawable.xmark_solid), tint = Color.White, contentDescription = "remove")
                            }
                        }
                    }
                }
            }
            Button(onClick = { clickAdd() }, modifier = Modifier
                .size(70.dp)
                .border(3.dp, Color.White, RoundedCornerShape(100)),
                colors = ButtonDefaults.buttonColors(Color.Transparent)) {
                Icon(painter = painterResource(id = R.drawable.plus_solid), tint = Color.White, contentDescription = "add")
            }
            Button(onClick = { clickNextDay() }, modifier = Modifier
                .size(70.dp)
                .border(3.dp, Color.White, RoundedCornerShape(100)),
                colors = ButtonDefaults.buttonColors(Color.Transparent)) {
                Icon(painter = painterResource(id = R.drawable.chevron_right_solid), tint = Color.White, contentDescription = "nextDay")
            }
        }
    }
}