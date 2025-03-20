package org.fenux.palwordtcgp.data.remote.response

import kotlinx.serialization.Serializable
import org.fenux.palwordtcgp.domain.model.CardModel

@Serializable
data class CardResponse(
    val data: PokemonCardData
) {
    fun toDomain(): CardModel {
    return CardModel(
        id = data.id,
        image = data.images.small,
        name = data.name,
        imageLarge = data.images.large
    )
    }
}

@Serializable
data class PokemonCardData(
    val id: String,
    val name: String,
    val supertype: String,
    val subtypes: List<String>,
//    @SerialName("hp")
//    val hp: String,
//    @SerialName("types")
//    val types: List<String>,
    val retreatCost: List<String>? = null,
    val number: String,
    val artist: String? = "",
    val rarity: String? = "",
    val images: CardImages,
)



@Serializable
data class CardImages(
    val small: String,
    val large: String
)
