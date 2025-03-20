package org.fenux.palwordtcgp.data.remote.response


import kotlinx.serialization.Serializable

@Serializable
data class CardsWrapperResponse(
    val data: List<CardsResponse>? = null,
    val page: Int ,
    val pageSize: Int,
    val count: Int?= null,
    val totalCount : Int ,
)
