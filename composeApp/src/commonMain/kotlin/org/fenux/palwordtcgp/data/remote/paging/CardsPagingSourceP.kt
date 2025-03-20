package org.fenux.palwordtcgp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.fenux.palwordtcgp.domain.model.CardModel


class CardsPagingSourceP : PagingSource<Int, CardModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CardModel> {
        val currentPage = params.key ?: 1 // Página actual, inicia en 1
        val pageSize = params.loadSize // Tamaño del lote a cargar
        val totalItems = 286 // Número total de imágenes disponibles

        // Generar URLs para la página actual
        val cards = (currentPage..(currentPage + pageSize - 1))
            .takeWhile { it <= totalItems } // Limitar hasta 284
            .map { number ->
                val formattedNumber = number.toString().padStart(3, '0')
                val imageUrl = "https://limitlesstcg.nyc3.cdn.digitaloceanspaces.com/pocket/A1a/A1a_${formattedNumber}_EN.webp"
                CardModel(
                    id = "1",
                    image = imageUrl,
                    imageLarge = imageUrl,
                    name = "pokemonPocket"
                )
            }

        // Configurar la próxima página
        val nextPage = if (cards.isNotEmpty() && cards.last().image.contains("_286_")) null else currentPage + pageSize

        return LoadResult.Page(
            data = cards,
            prevKey = if (currentPage == 1) null else currentPage - pageSize,
            nextKey = nextPage
        )
    }

    override fun getRefreshKey(state: PagingState<Int, CardModel>): Int? {
        return state.anchorPosition
    }
}
