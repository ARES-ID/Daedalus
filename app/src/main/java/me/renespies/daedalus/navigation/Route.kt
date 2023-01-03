package me.renespies.daedalus.navigation

sealed class Route(val actual: String) {
    sealed class GreenEngineeringMenu(subRoute: String) : Route("GreenEngineeringMenu#$subRoute") {
        object Entry : GreenEngineeringMenu("Entry")
        object Typography : GreenEngineeringMenu("Typography")
    }

    object Home : Route("Home")
}
