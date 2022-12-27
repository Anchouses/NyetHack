import kotlin.math.pow
const val NAME = "Marginal"

fun main(args: Array<String>) {
    var healthPoint = 89
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
    val auraColor = auraColor(isBlessed, healthPoint, isImmortal)

    // Состояние здоровья игрока
    val healsStatus = formatHealthStatus(healthPoint, isBlessed)
    printPlayerStatus(auraColor, isBlessed, NAME, healsStatus)

    castFireball()

//    val healthSummary = if (healthPoint != 100) "Need healing!" else "Looking good"

//    val karma = (Math.random().pow((110 - healthPoint) / 100.0) * 20).toInt()
//    println("Karma: $karma")
//    println("AuraColorful: $auraColorful")


//    val H = healsStatus
//    val HP = healthPoint
//    val A = auraColorful
//    val B = if (isBlessed) "YES" else "NO"
//    val statusFormatString = "HP: $HP, Aura: $A, Blessed: $B -> $NAME $H"
//    println(statusFormatString)


}

private fun printPlayerStatus(
    auraColor: String,
    isBlessed: Boolean,
    name: String,
    healsStatus: String
) {
    println("(Aura: $auraColor) " + "(Blessed: ${if (isBlessed) "YES" else "NO"})")
    println("$name $healsStatus")
}

private fun auraColor(isBlessed: Boolean, healthPoint: Int, isImmortal: Boolean) =
    if (isBlessed && (healthPoint > 50) || isImmortal) "GREEN" else "NONE"


private fun formatHealthStatus(healthPoint: Int, isBlessed: Boolean): String {
    val healsStatus = when (healthPoint) {
        100 -> "is in excellent condition!"
        in 90..99 -> "has a few scratches"
        in 75..89 -> if (isBlessed) {
            "has some minor wounds but is healing quite quickly!"
        } else "has some minor wounds."
        in 25..74 -> " looks pretty hurt."
        else -> "is in awful condition!"
    }
    return healsStatus
}

private fun castFireball(numFireballs: Int = 4): Int {
    val toxicEffect = when (numFireballs){
        1 -> (numFireballs * Math.random() * 10).toInt()
        2 -> (numFireballs * Math.random() * 20).toInt()
        3 -> (numFireballs * Math.random() * 30).toInt()
        4 -> (numFireballs * Math.random() * 40).toInt()
        else -> 0
    }
    val drunkenness= when (toxicEffect) {
        in 1..10 -> "Tipsy"     //навеселе
        in 11..20 -> "Sloshed"  //выпивший
        in 21..30 -> "Soused"   //пьяный
        in 31..40 -> "Stewed"   //сильно пьяный
        in 41..50 -> "..t0aSt3d"//в стельку
        else -> "Sober"               //трезвый
    }
    println(toxicEffect)
    println(drunkenness)
    println("A glass of Fireball springs into existence (x$numFireballs)")
    return toxicEffect
}
