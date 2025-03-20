package org.fenux.palwordtcgp.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun iniKoin(config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)
        modules(
            uiModule,
            domainModule,
            dataModule
        )
    }
}