package me.renespies.daedalus.navigation

sealed class Route(val actual: String) {
    sealed class GreenEngineeringMenu(subRoute: String) : Route("GreenEngineeringMenu#$subRoute") {
        object Entry : GreenEngineeringMenu("Entry")
        object Typography : GreenEngineeringMenu("Typography")
    }

    sealed class Home(subRoute: String) : Route("Home#$subRoute") {
        object Entry : Home("Entry")
        object AddWeight : Home("AddWeight")
    }
}
