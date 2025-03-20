package org.fenux.palwordtcgp.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.fenux.palwordtcgp.data.RepositoryImpl
import org.fenux.palwordtcgp.data.remote.ApiService
import org.fenux.palwordtcgp.domain.Repository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.fenux.palwordtcgp.data.remote.paging.CardsPagingSource
import org.fenux.palwordtcgp.data.remote.paging.CardsPagingSourceP

val dataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
                install(DefaultRequest){
                    url {
                        protocol = URLProtocol.HTTPS
                        host = "api.pokemontcg.io"
//                        parameters.append("key","")
                    }
                }
        }
    }
    factoryOf(::ApiService)
    factory<Repository> { RepositoryImpl(get(),get(),get())}
    factoryOf(::CardsPagingSource)
    factoryOf(::CardsPagingSourceP)
}