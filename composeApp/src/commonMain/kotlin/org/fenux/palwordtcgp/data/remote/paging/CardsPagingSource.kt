package org.fenux.palwordtcgp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.fenux.palwordtcgp.data.remote.ApiService
import org.fenux.palwordtcgp.data.remote.response.CardsResponse
import org.fenux.palwordtcgp.data.remote.response.generateCardsResponse
import org.fenux.palwordtcgp.domain.model.CardModel

class CardsPagingSource(private val api: ApiService) : PagingSource<Int, CardModel>() {

    override fun getRefreshKey(state: PagingState<Int, CardModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CardModel> {

        return try {
            val page = params.key ?: 1
            val response = api.getAllCards(page)
            val prev = if (page > 1) -1 else null
            val next = if (response.count == 0) null else page + 1

            LoadResult.Page(
                data = addTcgPocket(response.data!!,page).map { cardsResponse -> cardsResponse.toDomain() },
                prevKey = prev,
                nextKey = next
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
     fun addTcgPocket(data: List<CardsResponse>, page: Int):List<CardsResponse>{

         val finalCards: List<CardsResponse>
         if (page==1) {
             finalCards = generateCardsResponse() + data
         }else{
             finalCards =   data
         }
         return finalCards
     }
}