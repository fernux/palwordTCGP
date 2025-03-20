package org.fenux.palwordtcgp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform