package org.fenux.palwordtcgp.data.remote.response



fun generateCardsResponse(): List<CardsResponse> {
    val subTypes = listOf<String>() // Define aquí tus subtipos si son necesarios
    val cards = mutableListOf<CardsResponse>()

    for (i in 1..286) {
        val cardNumber = i.toString().padStart(3, '0') // Formatea el número como "001", "002", etc.
        cards.add(
            CardsResponse(
                id = i.toString(),
                name = "pokemonPocket",
                number = i.toString(),
                subtypes = subTypes,
                evolvesTo = null,
                rules = null,
                artist = "",
                images = CardImages(
                    "https://limitlesstcg.nyc3.digitaloceanspaces.com/pocket/A1/A1_${cardNumber}_EN.webp",
                    "https://limitlesstcg.nyc3.digitaloceanspaces.com/pocket/A1/A1_${cardNumber}_EN.webp"
                ),
                rarity = "",
                supertype = "",
                retreatCost = null
            )
        )
    }

    return cards
}
