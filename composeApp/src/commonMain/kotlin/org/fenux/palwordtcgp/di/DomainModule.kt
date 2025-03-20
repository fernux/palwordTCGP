package org.fenux.palwordtcgp.di

import org.fenux.palwordtcgp.domain.GetRandomCard
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {

    factoryOf(::GetRandomCard)
}