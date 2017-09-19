package com.example.architecture

data class NavigationRequest(val target: Class<*>)

data class DialogRequest(val title: String, val message: String)