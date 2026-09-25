package com.example.ledproject

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProjectDetailScreen(project: Project, onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        AppBackground()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 28.dp)
        ) {
            TextButton(onClick = onBack) {
                Text("← Projelere dön", color = Copper, fontSize = 13.sp, fontFamily = FontFamily.Monospace)
            }
            Spacer(Modifier.height(10.dp))
            Text(
                text = "${project.category.kicker} ${String.format("%02d", project.number)}",
                color = Copper, fontSize = 13.sp, fontFamily = FontFamily.Monospace
            )
            Spacer(Modifier.height(6.dp))
            Text(project.title, color = TextMain, fontSize = 26.sp, fontWeight = FontWeight.Bold, lineHeight = 32.sp)
            Spacer(Modifier.height(6.dp))
            Text(project.subtitle, color = TextDim, fontSize = 14.sp, lineHeight = 20.sp)

            Spacer(Modifier.height(20.dp))
            SchematicCard(project.category)

            Spacer(Modifier.height(24.dp))
            SectionTitle("Neden bu proje?")
            Text(project.why, color = TextMain, fontSize = 14.sp, lineHeight = 20.sp)

            Spacer(Modifier.height(24.dp))
            SectionTitle("Gerekli malzemeler")
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                for (row in project.parts.chunked(2)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        row.forEach { p ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .border(1.dp, Line, RoundedCornerShape(3.dp))
                                    .padding(10.dp)
                            ) {
                                Column {
                                    Text(p.name, color = TextMain, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                    Text(p.desc, color = TextDim, fontSize = 12.sp)
                                }
                            }
                        }
                        if (row.size == 1) Spacer(Modifier.weight(1f))
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
            SectionTitle("Uygulama adımları")
            Column {
                project.steps.forEachIndexed { i, s ->
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
                        Text(
                            text = "${i + 1}", color = Copper, fontWeight = FontWeight.Bold,
                            fontSize = 14.sp, modifier = Modifier.width(22.dp)
                        )
                        Text(text = s, color = TextMain, fontSize = 14.sp, lineHeight = 19.sp)
                    }
                    if (i < project.steps.size - 1) {
                        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Line))
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
            SectionTitle("Anladın mı? — Kısa quiz")
            QuizCard(questions = project.quiz, key = project.id)
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(text = text, color = Copper, fontSize = 15.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(10.dp))
}

@Composable
fun SchematicCard(category: Category) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(Panel)
            .border(1.dp, Line, RoundedCornerShape(6.dp))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxWidth().height(150.dp)) {
            val w = size.width
            val h = size.height
            val strokeW = 4f
            when (category) {
                Category.TEMEL -> {
                    val top = h * 0.2f; val bottom = h * 0.55f
                    val left = w * 0.08f; val right = w * 0.9f
                    drawLine(Copper, Offset(left, bottom), Offset(w * 0.28f, bottom), strokeW, cap = StrokeCap.Round)
                    drawRect(
                        color = Copper,
                        topLeft = Offset(w * 0.28f, bottom - h * 0.1f),
                        size = androidx.compose.ui.geometry.Size(w * 0.14f, h * 0.2f),
                        style = Stroke(width = strokeW)
                    )
                    drawLine(Copper, Offset(w * 0.42f, bottom), Offset(w * 0.55f, bottom), strokeW, cap = StrokeCap.Round)
                    val ledCenter = Offset(w * 0.65f, bottom)
                    drawCircle(LedColor, radius = h * 0.16f, center = ledCenter, style = Stroke(width = strokeW))
                    drawLine(Copper, Offset(w * 0.81f, bottom), Offset(right, bottom), strokeW, cap = StrokeCap.Round)
                    drawLine(Copper, Offset(right, bottom), Offset(right, top), strokeW, cap = StrokeCap.Round)
                    drawLine(Copper, Offset(right, top), Offset(left, top), strokeW, cap = StrokeCap.Round)
                    drawLine(Copper, Offset(left, top), Offset(left, bottom), strokeW, cap = StrokeCap.Round)
                }
                Category.SAYISAL -> {
                    // IC chip with pins
                    val chipLeft = w * 0.32f; val chipRight = w * 0.68f
                    val chipTop = h * 0.28f; val chipBottom = h * 0.72f
                    drawRect(
                        color = LedColor,
                        topLeft = Offset(chipLeft, chipTop),
                        size = androidx.compose.ui.geometry.Size(chipRight - chipLeft, chipBottom - chipTop),
                        style = Stroke(width = strokeW)
                    )
                    val pinCount = 4
                    for (i in 0 until pinCount) {
                        val y = chipTop + (chipBottom - chipTop) * (i + 0.5f) / pinCount
                        drawLine(Copper, Offset(w * 0.08f, y), Offset(chipLeft, y), strokeW, cap = StrokeCap.Round)
                        drawLine(Copper, Offset(chipRight, y), Offset(w * 0.92f, y), strokeW, cap = StrokeCap.Round)
                    }
                }
                Category.MCU -> {
                    // Development board silhouette with header pins
                    val boardLeft = w * 0.15f; val boardRight = w * 0.85f
                    val boardTop = h * 0.22f; val boardBottom = h * 0.78f
                    drawRect(
                        color = Copper,
                        topLeft = Offset(boardLeft, boardTop),
                        size = androidx.compose.ui.geometry.Size(boardRight - boardLeft, boardBottom - boardTop),
                        style = Stroke(width = strokeW)
                    )
                    drawRect(
                        color = LedColor,
                        topLeft = Offset(w * 0.38f, h * 0.38f),
                        size = androidx.compose.ui.geometry.Size(w * 0.24f, h * 0.24f),
                        style = Stroke(width = strokeW * 0.6f)
                    )
                    val pins = 6
                    for (i in 0 until pins) {
                        val x = boardLeft + (boardRight - boardLeft) * (i + 0.5f) / pins
                        drawCircle(Color(0xFF6B7A73), radius = 3f, center = Offset(x, boardTop - 6f))
                        drawCircle(Color(0xFF6B7A73), radius = 3f, center = Offset(x, boardBottom + 6f))
                    }
                }
            }
        }
    }
}
