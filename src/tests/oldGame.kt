fun main(args: Array<String>) {
    val name = "Marginal"
    var healthPoint = 76
    val isBlessed = true
    val isImmortal = false

    // Расса
    val race = "gnome"
    val function = when (race) {
        "dwarf" -> "Keepers of the Mines"
        "gnome" -> "Keepers of the Mines"
        "orc" -> "Free People of the Rolling Hills"
        "human" -> "Free People of the Rolling Hills"
        else -> "Nothing"
    }
    println("Race: $race")
    println("Function: $function")

    // Аура
    val auraVisible = isBlessed && (healthPoint > 50)|| isImmortal
    val auraColor = if (auraVisible) "GREEN" else "NONE"

    // Состояние здоровья игрока
    val healsStatus = when (healthPoint) {
        100 -> "is in excellent condition!"
        in 90..99 -> "has a few scratches"
        in 75..89 -> if (isBlessed) {
            "has some minor wounds but is healing quite quickly!"
        } else "has some minor wounds."
        in 25..74 -> " looks pretty hurt."
        else -> "is in awful condition!"
    }
    println("Aura: $auraColor")
    println("Blessed: ${if (isBlessed) "YES" else "NO"}")
    println("$name $healsStatus")

    val healthSummary = if (healthPoint != 100) "Need healing!" else "Looking good"


}
