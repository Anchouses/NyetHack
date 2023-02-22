package com.bignerdranch.nyethack
import java.io.File

class Player(_name: String,
                      var healthPoints: Int = 100,
                      val isBlessed: Boolean,
                      private val isImmortal: Boolean) {

    var name = _name
    set(value) {
             field = value.trim()
    }
    get() = "${field.replaceFirstChar {
            it.uppercase()
    }} of $homeTown"

    val homeTown by lazy { selectHomeTown() }

    var currentPosition = Coordinate(0, 0)

    init {
        require(healthPoints > 0, {"healthPoints must ve greater than zero"})
        require(name.isNotBlank(), {"Player must have a name"})
    }

    constructor(name: String): this(name,
        isBlessed = true,
        isImmortal = false){
        if (name.lowercase() == "kar") healthPoints = 40
    }

    //Аура
    fun auraColor() =
        if (isBlessed && (healthPoints > 50) || isImmortal) "GREEN" else "NONE"

    // Состояние здоровья игрока
    fun formatHealthStatus() = when (healthPoints) {
            100 -> "is in excellent condition!"
            in 90..99 -> "has a few scratches"
            in 75..89 -> if (isBlessed) {
                "has some minor wounds but is healing quite quickly!"
            } else "has some minor wounds."
            in 25..74 -> "looks pretty hurt."
            else -> "is in awful condition!"
        }

    // Огненное зелье
    fun castFireball(numFireballs: Int = 2) =
    println("A glass of Fireball springs into existence. (x$numFireballs)")

    //HomeTown select
    private fun selectHomeTown() = File("dataNH/towns.txt")
        .readText()
        .split("\n")
        .shuffled()
        .first()

}


