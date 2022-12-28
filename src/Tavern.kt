import kotlin.math.roundToInt

const val TAVERN_NAME = "Lorry's Folly"
var playerGold = 10
var playerSilver = 10
var playerDracoin = (1.43 * (playerGold + playerSilver / 100)).roundToInt()
fun main(args: Array<String>){

    placeOrder("shandy,Dragon's Breath,5.11")

}

fun performPurchase(price: Double, tavernMaster: String): Boolean {        //производить закупки
    displayBalance()

    val totalPurse = playerGold + (playerSilver/100.0)  //всего в кошельке

//    println("Purchasing item for $price")     //покупка товара за цену

    return if (totalPurse < price) {
        println("$tavernMaster: you haven't enough money, pal")
        false
    } else {
        val remainingBalance = totalPurse - price    //изменение баланса
//    println("Remaining Balance: ${"%.2f".format(remainingBalance)}")
        val remainingGold = remainingBalance.toInt()
        val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
        val remainingDracoin = (remainingBalance * 1.43).roundToInt()
        playerGold = remainingGold
        playerSilver = remainingSilver
        playerDracoin = remainingDracoin
        true
    }
}

private fun displayBalance(){
    println("Player's purse balance: Gold $playerGold, Silver $playerSilver, or in Dracoin $playerDracoin")
}


 fun placeOrder(menuData: String){
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$NAME speaks with $tavernMaster about his order")
    val (type, name, price) = menuData.split(',')

    val message = "$NAME buys a $name ($type) of $price"
    println(message)

    if (performPurchase(price.toDouble(), tavernMaster)){
        var phrase = if (name == "Dragon's Breath"){
            "$NAME exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
        } else {
            "$NAME says: Thanks for the $name!"
        }
        println(phrase)
    }

     displayBalance()

    var dragonsBreathKeg = 5.0
    var order = 12
    if (name == "Dragon's Breath") order = order.plus(1)
    val orderVolume = 0.125
    val residueDragonsBreath = dragonsBreathKeg - (orderVolume * order)
    dragonsBreathKeg = residueDragonsBreath
    println("Only ${"%.2f".format(dragonsBreathKeg)} gallons of $type $name left")
}



private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aAeEiIoOuU]")) {
        when (it.value) {
            "a", "A" -> "4"
            "e", "E" -> "3"
            "i", "I" -> "1"
            "o", "O" -> "0"
            "u", "U" -> "|_|"
            else -> it.value
        }
    }
