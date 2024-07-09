package com.example.notifcation4.navcation




sealed class Screen(val route: String) {
    object Main : Screen(route = "main")
    object Details : Screen(route = "details/{$MY_ARG}") {
        fun passArgument(message: String) = "details/$message"
    }
}