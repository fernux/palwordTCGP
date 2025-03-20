package org.fenux.palwordtcgp.data.remote.response

import kotlinx.serialization.Serializable
import org.fenux.palwordtcgp.domain.model.CardModel

@Serializable
data class CardsResponse(
    val id: String,
    val name: String,
    val supertype: String,
    val subtypes: List<String>,
//    @SerialName("hp")
//    val hp: String,
//    @SerialName("types")
//    val types: List<String>,
    val evolvesTo: List<String>? = null,
    val rules: List<String>? = null,
    val retreatCost: List<String>? = null,

    val number: String,
    val artist: String? = "",
    val rarity: String? = "",
//    @SerialName("nationalPokedexNumbers")
//    val nPokeNumbers: List<Int>,
    val images: CardImages,
){
    fun toDomain(): CardModel {
        return CardModel(
            id = id,
            image = images.small,
            name = name,
            imageLarge = images.large
        )
    }
}
