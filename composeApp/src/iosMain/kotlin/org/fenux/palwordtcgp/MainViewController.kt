package org.fenux.palwordtcgp

import androidx.compose.ui.window.ComposeUIViewController
import org.fenux.palwordtcgp.di.iniKoin

fun MainViewController() = ComposeUIViewController(configure = {iniKoin()}) { App() }