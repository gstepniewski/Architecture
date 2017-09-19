package com.example.architecture.screens.main

import com.example.architecture.DialogRequest
import com.example.architecture.NavigationRequest

data class MainViewState(val name: String,
                         val dialog: DialogRequest? = null,
                         val navigation: NavigationRequest? = null) {
    fun name(name: String) = copy(name = name)

    fun dialog(request: DialogRequest) = copy(dialog = request)
    fun clearDialog() = copy(dialog = null)

    fun navigation(request: NavigationRequest) = copy(navigation = request)
    fun clearNavigation() = copy(navigation = null)
}
