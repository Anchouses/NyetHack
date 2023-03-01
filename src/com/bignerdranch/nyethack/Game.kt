package com.bignerdranch.nyethack

fun main() {
    Game.play()
}


object Game {
    private val player = Player("marginal")
    private var currentRoom = TownSquare() as Room
    private var exit = true
    private var worldMap = listOf(            // y
        listOf(currentRoom, Room("Tavern"), Room("Black Room")),
        listOf(Room("Long Corridor"), Room("Generic Room"))
    )

    init {
        println("Welcome, adventurer!")
        player.castFireball()    //Огненное зелье
    }

    fun play() {     //игровой процесс NyetHack

        while (exit) {
            println(currentRoom.description())
            println(currentRoom.load())

            printPlayerStatus(player)    // Состояние здоровья игрока

            print("> Enter your command: ")
            println(readLine()?.let { GameInput(it).processCommand() })      //ОБРАТИТЬ ВНИМАНИЕ НА СИНТАКСИС!!!

        }
        println("Bye! Have a nice day!")
    }

    private fun printPlayerStatus(player: Player) {
        println("(Aura: ${player.auraColor()}) " + "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
        println("${player.name} ${player.formatHealthStatus()}")
    }

     private class GameInput(arg: String) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })

        fun processCommand() = when (command.lowercase()) {
            "move" -> move(argument)
            "map" -> map()
            "ring" -> ringBell(argument.toInt())
            "quit" -> exit = false
            else -> commandNotFound()
        }

        private fun commandNotFound() = "I'm not quite sure what you are trying to do!"
    }

    private fun move(directionInput: String) =
        try {
            val direction =
                Direction.valueOf(directionInput.uppercase())   // вызывает значение класса перечислений Direction, совпадающее с введенным значением.uppercase()
            val newPosition =
                direction.updateCoordinate(player.currentPosition)  // вызывает функцию updateCoordinate из класса Direction, которая складывает координаты раправления с текущими координатами игрока currentPosition
            if (!newPosition.isInBounds) {  //
                throw java.lang.IllegalStateException("$direction is out of bounds")
            }
            val newRoom =
                worldMap[newPosition.y][newPosition.x] // новая комната ищется по координате у из двух списков, потом по координате х из комнат в найденном списке
            player.currentPosition = newPosition  //  переприсваиваем текущее положение игрока
            currentRoom = newRoom  //  переприсваиваем текущую комнату
            "Ok, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"  //   вывод если все ок, направление нашлось, за границы не вышли
        } catch (e: Exception) {  // если направление не нашлось
            "Invalid direction: $directionInput"  // вывод "неверное направление"
        }

    private fun map() {
        for (list in worldMap) {
            list.forEachIndexed() { index, room ->
                if (room != currentRoom) {
                    if (index == 2) print("  O  \n") else print(" O ")
                } else
                    if (index == 2) print("  X  \n") else print(" X ")
            }
        }
    }

    public fun ringBell(quantity: Int) {
        if (currentRoom.name == "Town Square") {
            println("${player.name} ring bells for $quantity times")
        } else {
            println("There is no one bell ${player.name} can ring")
        }
    }
}


