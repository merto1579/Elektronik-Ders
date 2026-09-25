package com.example.ledproject

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProjectListScreen(onSelect: (Project) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        AppBackground()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 28.dp)
        ) {
            Text("ELEKTRONİK DERS", color = Copper, fontSize = 13.sp, fontFamily = FontFamily.Monospace)
            Spacer(Modifier.height(6.dp))
            Text(
                "Elektrik-Elektronik Müfredatı",
                color = TextMain, fontSize = 26.sp, fontWeight = FontWeight.Bold, lineHeight = 32.sp
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "${projects.size} uygulamalı ders, 3 kategori. Bir proje seç ve başla.",
                color = TextDim, fontSize = 14.sp, lineHeight = 20.sp
            )
            Spacer(Modifier.height(26.dp))

            Category.values().forEach { cat ->
                val items = projects.filter { it.category == cat }
                Text(cat.label, color = Copper, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items.forEach { p -> ProjectRow(p) { onSelect(p) } }
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ProjectRow(project: Project, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(Panel)
            .border(1.dp, Line, RoundedCornerShape(4.dp))
            .clickable(onClick = onClick)
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "${String.format("%02d", project.number)}",
                    color = TextDim, fontSize = 11.sp, fontFamily = FontFamily.Monospace
                )
                Text(project.title, color = TextMain, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text(project.subtitle, color = TextDim, fontSize = 12.sp, lineHeight = 16.sp)
            }
            Spacer(Modifier.width(10.dp))
            Text("›", color = Copper, fontSize = 20.sp)
        }
    }
}
