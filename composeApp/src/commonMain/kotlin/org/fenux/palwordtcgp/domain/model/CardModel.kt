package org.fenux.palwordtcgp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CardModel(
    val id: String,
    val name: String,
    val image:String,
    val imageLarge:String
)
