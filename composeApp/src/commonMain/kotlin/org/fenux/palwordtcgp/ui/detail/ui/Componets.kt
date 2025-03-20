package org.fenux.palwordtcgp.ui.detail.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.fenux.palwordtcgp.domain.model.CardModel
import org.jetbrains.compose.resources.painterResource
import palwordtcgp.composeapp.generated.resources.Res
import palwordtcgp.composeapp.generated.resources.ic_card

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHeader(cardModel: CardModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent,
                    navigationIconContentColor = Color.Transparent,
                    titleContentColor = Color.Transparent,
                    actionIconContentColor = Color.Transparent,
                ),
                actions = {
                    BottomsBarHedear()
                }

            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier.fillMaxWidth().height(650.dp).background(Color.LightGray)
                    .padding(paddingValues)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.TopCenter
                ) {
                    CardFaceDisplay(cardModel)
                }
                CardHeader(cardModel = cardModel)
            }

        }
    )

}

@Composable
fun BottomsBarHedear() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .padding(start = 16.dp)
    ) {
        Button(
            onClick = { /* Acción del botón */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White, // Fondo blanco
                contentColor = Color.Black // Texto negro
            ),
            contentPadding = PaddingValues(6.dp),
            modifier = Modifier

                //.width(120.dp)
                //.wrapContentWidth(Alignment.Start)
                .weight(1f) // Espacio proporcional
                .clip(RoundedCornerShape(50)) // Redondea completamente el botón
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(Res.drawable.ic_card), "")
                Text(text = "0")
                Icon(imageVector = Icons.Default.Lock, "")
            }

        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = { /* Acción del botón */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White, // Fondo blanco
                contentColor = Color.Black // Texto negro
            ),
            contentPadding = PaddingValues(6.dp),
            modifier = Modifier

                //.width(120.dp)
                .weight(1f)
                .clip(RoundedCornerShape(50)) // Redondea completamente el botón
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.MailOutline, "")
                Text(textAlign = TextAlign.Center, text = "1")
                Icon(imageVector = Icons.Default.Lock, "")
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {},
            modifier = Modifier.width(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent, // Fondo blanco
                contentColor = Color.Black // Texto negro
            )
        ) {
            Text(text = "...", textAlign = TextAlign.Right)
        }
        Button(
            onClick = {},
            modifier = Modifier.width(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent, // Fondo blanco
                contentColor = Color.Black // Texto negro
            )
        ) {
            Icon(imageVector = Icons.Default.Favorite, "")
        }
    }
}

@Composable
fun CardHeader(cardModel: CardModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            Modifier.fillMaxWidth().height(100.dp).clip(
                RoundedCornerShape(
                    topStartPercent = 30,
                    topEndPercent = 30
                )
            )

                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
            //verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.size(5.dp))
            CircularRectangleWithShadow()
            CircularRectangleWithBorder()
//            Text(
//                cardModel.name,
//                color = Color.Black,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold
//            )

        }
    }
}

@Composable
fun CircularRectangleWithShadow() {
    Box(
        modifier = Modifier
            .size(55.dp, 5.dp) // Tamaño del rectángulo
            .clip(RoundedCornerShape(26.dp)) // Esquinas redondeadas
            .background(Color.LightGray) // Color de fondo

        //.shadow(8.dp, RoundedCornerShape(16.dp)) // Sombra con esquinas redondeadas
    )
}

@Composable
fun CircularRectangleWithBorder() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp)
            .wrapContentWidth(Alignment.Start)
            .size(300.dp, 50.dp) // Dimensiones del rectángulo
            .clip(RoundedCornerShape(26.dp)) // Forma redondeada
            .border(
                width = 4.dp, brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.LightGray,
                        Color.LightGray,
                        Color.White
                    ) // Gradiente de color gris a azul
                ), shape = RoundedCornerShape(26.dp)
            ), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(Res.drawable.ic_card), "", modifier = Modifier.weight(.5f))
        Text(textAlign = TextAlign.Center, text = "1", modifier = Modifier.weight(.7f))
        Button(
            onClick = { /* Acción del botón */ },
            modifier = Modifier.weight(1.7f)
                .weight(1f) // Espacio proporcional
                .clip(RoundedCornerShape(50)) // Redondea completamente el botón
        ) {
            Text(text = "Botón")
        }
    }
}

@Composable
fun CardFaceDisplay(cardsModel: CardModel) {
    val alphaAnimation = remember {
        Animatable(0f)
    }

    val contrast = 1f // 0f..10f (1 should be default)
    var brightness by remember {
        mutableStateOf(0f)
    }
    var colorMatrix = floatArrayOf(
        contrast, 0f, 0f, 0f, brightness,
        0f, contrast, 0f, 0f, brightness,
        0f, 0f, contrast, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )
    LaunchedEffect(Unit) {
        alphaAnimation.animateTo(1f)
    }
    var isRotated by rememberSaveable { mutableStateOf(false) }
    var pointerOffset by remember {
        mutableStateOf(Offset(0f, 0f))
    }
    var pointerOffsetLigth by remember {
        mutableStateOf(Offset(0f, 0f))
    }

    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = .9f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Restart),
        label = "scale"
    )

    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
            //.wrapContentSize() // El card se ajusta al tamaño de su contenido
            .padding(16.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, offset ->

                        pointerOffsetLigth -= offset / 2f
                        pointerOffset -= offset / 90f
                        isRotated = change.pressed

                        brightness = 30f
                        //Log.wtf("brillo" , ""+offset.x+"/"+offset.y)
                    },
                    onDragStart = {
                        brightness = 30f
                        pointerOffset = Offset(.2f, .2f)
                        isRotated = false
                    },
                    onDragEnd = {
                        isRotated = false
                        brightness = 0f
                    },
                    onDragCancel = {
                        isRotated = false
                        brightness = 0f
                    }
                )
            }

            .onSizeChanged {
                pointerOffset = Offset(it.width / 1f, it.height / 1f)
            }
            .graphicsLayer {
                alpha = alphaAnimation.value
                //scaleX = scale
                //scaleY = scale
                rotationX = if (isRotated) pointerOffset.y else 0f
                rotationY = if (isRotated) -pointerOffset.x else 0f
                transformOrigin = TransformOrigin.Center

            }

        //Modifier.fillMaxWidth().height(550.dp).padding(20.dp)
    ) {
        AsyncImage(
            modifier = Modifier.wrapContentSize(),
            colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
            //filterQuality = FilterQuality.Low,
            model = cardsModel.imageLarge,
            contentDescription = null,
            contentScale = ContentScale.Crop,

            )

    }
}