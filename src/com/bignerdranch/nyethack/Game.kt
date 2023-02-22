package com.bignerdranch.nyethack

fun main(args: Array<String>) {
    Game.play()
}


fun printIsSourceOfBlessings(any: Any){
    val isSourceOfBlessings = if (any is Player){
        any.isBlessed
    } else {
       (any as Room).name == "Found of blessings"
    }
    println("$any is a source of blessings: $isSourceOfBlessings")
}


object Game{
    private val player = Player("marginal")
    private var currentRoom = TownSquare() as Room

    private var worldMap = listOf(            // y
        listOf(currentRoom, Room("Tavern"), Room("Black Room")),
        listOf(Room("Long Corridor"),Room("Generic Room")))

    init {
        println("Welcome, adventurer!")
        player.castFireball()    //Огненное зелье
    }

    fun play(){     //игровой процесс NyetHack
        while (true){

            println(currentRoom.description())
            println(currentRoom.load())

            printPlayerStatus(player)    // Состояние здоровья игрока

            print("> Enter your command: ")
            println(readLine()?.let { GameInput(it).processCommand() })      //ОБРАТИТЬ ВНИМАНИЕ НА СИНТАКСИС!!!
        }
    }

    private fun printPlayerStatus(player: Player) {
        println("(Aura: ${player.auraColor()}) " + "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
        println("${player.name} ${player.formatHealthStatus()}")
    }

    val abandonedTownSquare = object : TownSquare(){
       override fun load() = "You anticipate, but no one is here..."}

    private class GameInput(arg:String){
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, {""})

        fun processCommand() = when (command.lowercase()){
            "move" -> move(argument)
            else -> commandNotFound()
        }
        private fun commandNotFound() = "I'm not quite sure what you are trying to do!"
    }

private fun move(directionInput: String) =
    try {
        val direction = Direction.valueOf(directionInput.uppercase())
        val newPosition = direction.updateCoordinate(player.currentPosition)
        if (!newPosition.isInBounds){
            throw java.lang.IllegalStateException("$direction is out of bounds")
            }
        val newRoom = worldMap[newPosition.y][newPosition.x]
        player.currentPosition = newPosition
        currentRoom = newRoom
        "Ok, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
    } catch (e: Exception){
        "Invalid direction: $directionInput"
    }


}