package com.example.ledproject

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuizCard(questions: List<Question>, key: String) {
    var current by remember(key) { mutableIntStateOf(0) }
    var score by remember(key) { mutableIntStateOf(0) }
    var selected by remember(key) { mutableStateOf<Int?>(null) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(Panel)
            .border(1.dp, Line, RoundedCornerShape(6.dp))
            .padding(20.dp)
    ) {
        if (current >= questions.size) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$score / ${questions.size} doğru",
                    color = TextMain,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(14.dp))
                OutlinedButton(
                    onClick = { current = 0; score = 0; selected = null },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Copper)
                ) {
                    Text("Tekrar Dene")
                }
            }
        } else {
            val q = questions[current]
            Column {
                Text(
                    text = "Soru ${current + 1} / ${questions.size}",
                    color = TextDim,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace
                )
                Spacer(Modifier.height(10.dp))
                Text(text = q.text, color = TextMain, fontSize = 15.sp, lineHeight = 20.sp)
                Spacer(Modifier.height(14.dp))
                q.options.forEachIndexed { i, opt ->
                    val isAnswered = selected != null
                    val borderColor = when {
                        !isAnswered -> Line
                        i == q.correct -> Good
                        i == selected -> Bad
                        else -> Line
                    }
                    OutlinedButton(
                        onClick = {
                            if (selected == null) {
                                selected = i
                                if (i == q.correct) score++
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        border = BorderStroke(1.dp, borderColor),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = TextMain)
                    ) {
                        Text(text = opt, fontSize = 13.sp, modifier = Modifier.fillMaxWidth())
                    }
                }
                if (selected != null) {
                    Text(
                        text = q.note,
                        color = TextDim,
                        fontSize = 12.sp,
                        lineHeight = 17.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Spacer(Modifier.height(12.dp))
                    Button(
                        onClick = { current++; selected = null },
                        colors = ButtonDefaults.buttonColors(containerColor = Copper)
                    ) {
                        Text(if (current == questions.size - 1) "Bitir" else "Sonraki Soru")
                    }
                }
            }
        }
    }
}
