package org.fenux.palwordtcgp.ui.home.tabs.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import org.fenux.palwordtcgp.domain.model.CardModel
import org.fenux.palwordtcgp.ui.core.components.PagingLoadingState
import org.fenux.palwordtcgp.ui.core.components.PagingType
import org.fenux.palwordtcgp.ui.core.components.PagingWrapper
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import palwordtcgp.composeapp.generated.resources.Res
import palwordtcgp.composeapp.generated.resources.ic_card_backgound

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CardsScreen(navigationToDetail:(CardModel) -> Unit ) {
    val cardsViewModel = koinViewModel<CardsViewModel>()
    val state by cardsViewModel.state.collectAsState()
    val cards = state.cards.collectAsLazyPagingItems()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        //CardOfTheDay(state.cardOfTheDay)
        PagingWrapper(
            pagingType = PagingType.VERTICAL_GRID,
            pagingItems = cards,
            initialView = {PagingLoadingState()},
            itemView = { CardItemList(
                it,
                onItemSelected = { cardModel ->
                    navigationToDetail(cardModel)
                }
            ) }
        )
        //CardsGridList(cards)
    }
}

@Composable
fun CardsGridList(cards: LazyPagingItems<CardModel>) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize().padding(horizontal = 6.dp),
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        item(span = { GridItemSpan(3) }) {
                Column {
                    Text("Mis Cartas", color = Color.Black, fontSize = 24.sp)
                }
        }

        when {
            cards.loadState.refresh is LoadState.Loading && cards.itemCount == 0 -> {
                //Carga inicial
                item(span = { GridItemSpan(3) }) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(Modifier.size(64.dp), color = Color.Red)
                    }
                }
            }

            cards.loadState.refresh is LoadState.NotLoading && cards.itemCount == 0 -> {
                item { // API vacia
                    Text("no hay pokémones")
                }
            }

            else -> {
                //Recorremos los items
                items(cards.itemCount) { pos ->
                    cards[pos]?.let { cardsModel ->
                        CardItemList(cardsModel){
                            //Navegar
                            //navigationToDetail()
                        }
                    }
                }

                if (cards.loadState.append is LoadState.Loading) {
                    item(span = { GridItemSpan(3) }) {
                        Box(
                            modifier = Modifier.fillMaxHeight().height(100.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(Modifier.size(64.dp), color = Color.Red)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardItemList(cardsModel: CardModel, onItemSelected:(CardModel) -> Unit) {
    val borderBrush = listOf(
        Color.LightGray,
        Color.White,
        Color.LightGray,
        Color.LightGray,
        Color.White,
        Color.LightGray,
        Color.LightGray,
        Color.White,
        Color.LightGray,
        Color.LightGray,
        Color.White,
        Color.LightGray,
    )
    val borderBrush2 = listOf(
        Color.LightGray,
        Color.Yellow,
        Color.LightGray,
        Color.LightGray,
        Color.Magenta,
        Color.LightGray,
        Color.LightGray,
        Color.Transparent,
        Color.LightGray,
        Color.LightGray,
        Color.Cyan,
        Color.LightGray,
    )
    Card(
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
//            .fillMaxWidth().height(151.dp)
            .size(170.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = borderBrush, // Ajusta los colores del gradiente
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                ),
                shape = RoundedCornerShape(6.dp)
            )
            .padding(3.dp) // Crea un "espacio" para el borde
            .background(
                Color.Black,
                shape = RoundedCornerShape(6.dp)
            ) // Fondo interior de la tarjeta
            .clickable { onItemSelected(cardsModel) }
    ) {
        AsyncImage(
            placeholder = painterResource(Res.drawable.ic_card_backgound),
            model = cardsModel.image,
            contentDescription = null,
            contentScale = ContentScale.FillBounds, // O usa `FillBounds` si prefieres llenar el contenedor
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(6.dp)) // Asegura que la imagen siga la forma redondeada
        )
    }
}


@Composable
fun CardOfTheDay(cardsModel: CardModel? = null) {

    if (cardsModel == null) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(color = Green)
        }
    } else {
        //CardFaceDisplay(cardsModel)
    }

}



