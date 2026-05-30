package com.example.bal2kleague

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BALTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BALDashboard()
                }
            }
        }
    }
}

@Composable
fun BALTheme(content: @Composable () -> Unit) {
    val colorScheme = darkColorScheme(
        primary = Color(0xFFEABB23), // BAL Yellow
        secondary = Color(0xFF009639), // BAL Green
        tertiary = Color(0xFFED1C24), // BAL Red
        background = Color(0xFF0A0A0A),
        surface = Color(0xFF151515),
        primaryContainer = Color(0xFF232323),
        onPrimaryContainer = Color(0xFFEABB23),
        onPrimary = Color.Black,
        onSecondary = Color.White,
        onTertiary = Color.White,
        onBackground = Color.White,
        onSurface = Color.White
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BALDashboard() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.SportsBasketball,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "BAL 2K LEAGUE", 
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            item {
                FeaturedHeader()
            }

            item {
                SectionHeader("LATEST RESULTS")
            }

            item {
                MatchResultCard(
                    teamA = "RSSB TIGERS", teamB = "FUS RABAT",
                    scoreA = 95, scoreB = 72,
                    isFinal = true
                )
            }

            item {
                MatchResultCard(
                    teamA = "AL AHLY", teamB = "VILLE DE DAKAR",
                    scoreA = 90, scoreB = 93,
                    isFinal = true
                )
            }

            item {
                SectionHeader("LEAGUE STANDINGS")
            }

            item {
                StandingsPreview()
            }

            item {
                SectionHeader("UPCOMING GAMES")
            }

            item {
                UpcomingMatchCard("CLUB AFRICAIN", "AL AHLY LY", "15:00 LOCAL")
            }

            item {
                UpcomingMatchCard("PETRO DE LUANDA", "DAR CITY", "18:00 LOCAL")
            }

            item {
                SectionHeader("BAL INSIDER")
            }

            item {
                NewsRow()
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(64.dp)
                        .testTag("get_tickets_button"),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("GET TICKETS NOW", fontWeight = FontWeight.Black, fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 12.dp),
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp,
        color = MaterialTheme.colorScheme.secondary
    )
}

@Composable
fun FeaturedHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        AsyncImage(
            model = R.drawable.bal_hero_new,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.9f)),
                        startY = 100f
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Surface(
                color = MaterialTheme.colorScheme.tertiary,
                shape = MaterialTheme.shapes.extraSmall
            ) {
                Text(
                    "LIVE",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Text(
                text = "RISE WITH THE GAME",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
            Text(
                text = "BAL FINALS 2026 - KIGALI ARENA",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun MatchResultCard(teamA: String, teamB: String, scoreA: Int, scoreB: Int, isFinal: Boolean) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TeamScore(teamA, scoreA, isWinner = scoreA > scoreB)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    if (isFinal) "FINAL" else "LIVE",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
                Text("VS", fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.outline)
            }
            TeamScore(teamB, scoreB, isWinner = scoreB > scoreA, isRight = true)
        }
    }
}

@Composable
fun TeamScore(name: String, score: Int, isWinner: Boolean, isRight: Boolean = false) {
    Column(horizontalAlignment = if (isRight) Alignment.End else Alignment.Start) {
        Text(
            name,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = if (isWinner) FontWeight.Bold else FontWeight.Normal,
            color = if (isWinner) Color.White else Color.White.copy(alpha = 0.6f)
        )
        Text(
            score.toString(),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black,
            color = if (isWinner) MaterialTheme.colorScheme.primary else Color.White
        )
    }
}

@Composable
fun StandingsPreview() {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            val teams = listOf(
                Pair("AL AHLY", "4-0"),
                Pair("FUS RABAT", "3-1"),
                Pair("RSSB TIGERS", "2-2")
            )
            teams.forEachIndexed { index, (name, record) ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "#${index + 1}",
                            modifier = Modifier.width(28.dp),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(name, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                    }
                    Text(record, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.secondary)
                }
                if (index < teams.size - 1) {
                    HorizontalDivider(color = Color.White.copy(alpha = 0.05f))
                }
            }
        }
    }
}

@Composable
fun UpcomingMatchCard(teamA: String, teamB: String, time: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(teamA, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
                Text(teamB, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
            }
            Surface(
                color = Color.Black,
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    time,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@Composable
fun NewsRow() {
    val news = listOf(
        "KIGALI READY FOR FINALS" to "The arena is set for the biggest African hoops event.",
        "MVP WATCH" to "Who is leading the race for the BAL 2K crown?",
        "YOUTH SUMMIT" to "Giants of Africa hosting youth development camps."
    )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(news) { (title, desc) ->
            Card(
                modifier = Modifier.width(260.dp).height(140.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp).fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                    Text(title, fontWeight = FontWeight.Black, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                    Text(desc, style = MaterialTheme.typography.bodySmall, maxLines = 2, color = Color.White.copy(alpha = 0.7f))
                }
            }
        }
    }
}
