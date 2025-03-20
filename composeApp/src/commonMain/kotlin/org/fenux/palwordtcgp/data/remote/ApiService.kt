package org.fenux.palwordtcgp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.fenux.palwordtcgp.data.RepositoryImpl.Companion.MAX_ITEMS
import org.fenux.palwordtcgp.data.remote.response.CardResponse
import org.fenux.palwordtcgp.data.remote.response.CardsWrapperResponse

class ApiService(private val client: HttpClient) {

    suspend fun getSingleCard(id: String): CardResponse {
        return client.get("/v2/cards/$id").body()
    }

    suspend fun getAllCards(page: Int): CardsWrapperResponse {
        return client.get("/v2/cards/") {
            parameter("q", "set.id:sv3pt5 AND nationalPokedexNumbers:[1 TO 151]")
            parameter("page", page)
            parameter("pageSize", MAX_ITEMS)
            parameter("orderBy", "nationalPokedexNumbers")
        }.body()
    }
}
