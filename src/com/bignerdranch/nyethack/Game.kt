import com.bignerdranch.nyethack.Player

fun main(args: Array<String>) {

    val player = Player("marginal")
    println(player.name)
        //Огненное зелье
    player.castFireball()
        // Аура
    player.auraColor()
        // Состояние здоровья игрока
    printPlayerStatus(player)

}



private fun printPlayerStatus(player: Player) {
    println("(Aura: ${player.auraColor()}) " + "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
    println("${player.name} ${player.formatHealthStatus()}")
}




