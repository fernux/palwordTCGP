package org.fenux.palwordtcgp.data

import androidx.paging.PagingConfig
import app.cash.paging.Pager
import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.fenux.palwordtcgp.data.remote.ApiService
import org.fenux.palwordtcgp.data.remote.paging.CardsPagingSource
import org.fenux.palwordtcgp.data.remote.paging.CardsPagingSourceP
import org.fenux.palwordtcgp.domain.Repository
import org.fenux.palwordtcgp.domain.model.CardModel

class RepositoryImpl(
    private val api: ApiService,
    private val cardsPagingSource: CardsPagingSource,
    private val cardPagingSource: CardsPagingSourceP
) : Repository {

    companion object {
        const val MAX_ITEMS = 20
        const val PREFETCH = 5
    }

    override suspend fun getSingleCard(id: String): CardModel {
        return api.getSingleCard(id).toDomain()
    }

    override fun getAllCards(): Flow<PagingData<CardModel>> {
        return Pager(config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH),
            pagingSourceFactory = { cardsPagingSource }).flow
    }

    override fun getAllCard(): Flow<PagingData<CardModel>>{
        return Pager(config =  PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH),
            pagingSourceFactory = { cardPagingSource}).flow
    }
}