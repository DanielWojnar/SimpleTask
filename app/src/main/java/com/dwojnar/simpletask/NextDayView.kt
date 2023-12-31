package com.dwojnar.simpletask

import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NextDayView(startNewDay: () -> Unit, goBack: () -> Unit){
    Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Do you want to start new day?", color = Color.White, fontSize = 30.sp)
            Row(modifier = Modifier
                .height(90.dp)
                .padding(top = 10.dp, bottom = 10.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly){
                Button(onClick = {
                    goBack()
                }, modifier = Modifier
                    .size(70.dp)
                    .border(3.dp, Color.White, RoundedCornerShape(100)),
                    colors = ButtonDefaults.buttonColors(Color.Transparent)) {
                    Icon(painter = painterResource(id = R.drawable.xmark_solid), contentDescription = "no", tint = Color.White)
                }
                Button(onClick = {
                    startNewDay()
                    goBack()
                }, modifier = Modifier
                    .size(70.dp)
                    .border(3.dp, Color.White, RoundedCornerShape(100)),
                    colors = ButtonDefaults.buttonColors(Color.Transparent)) {
                    Icon(painter = painterResource(id = R.drawable.check_solid), contentDescription = "yes", tint = Color.White)
                }
            }
        }
    }
}