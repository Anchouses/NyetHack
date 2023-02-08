import kotlin.math.roundToInt
import java.io.File

const val TAVERN_NAME = "Lorry's Folly"

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Ferndeorth", "Baggins")
var uniquePatrons = mutableSetOf<String>()
val menuList = File("dataNH/tavern-menu-items.txt")
    .readText()
    .split("\n")
var patronGold = mutableMapOf<String, Double>()
var leavePatron = mutableSetOf<String>()
var set = mutableMapOf<String, Double>()


fun main(args: Array<String>) {

    var isTavernOpen = true
    val isClosingTime = false
    while (isTavernOpen == true) {
        if (isClosingTime) {
            isTavernOpen = false
        }
        println("Having a grand old time!")
        break
    }


    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }

    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }

    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(
            uniquePatrons.shuffled().first(),
            menuList.shuffled().first()
        )
        orderCount++
    }

    displayPatronBalances()
//    println(uniquePatrons)
//    println(patronGold)
//    println(leavePatron)
//    println(set)
    patronGold -= leavePatron
    println("Stay in tavern: $patronGold")

}
    // Совершаем покупку
fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}

    // Считаем баланс, прогоняем неплатежеспособных клиентов
fun displayPatronBalances(){
    patronGold.forEach { (patron, balance) ->
       println("$patron, balance: ${"%.2f".format(balance)}")
        if (balance <= 0.0){
            println("Bouncer say: $patron, you have no money, pal! Get out!")
            uniquePatrons.remove(patron)
            leavePatron += patron
            } else {
            println("$patron, would you like something else?")
            set += patron to balance
        }
    }
}
    // Оформляем заказ
 fun placeOrder(patronName: String, menuData: String){
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about his order")
    val (type, drinkName, price) = menuData.split(',')

    val message = "$patronName buys a $drinkName ($type) of $price"
    println(message)

    performPurchase(price.toDouble(), patronName)

    var phrase = if (drinkName == "Dragon's Breath"){
        "$patronName exclaims: ${toDragonSpeak("Ah, delicious $drinkName!")}"
        } else {
            "$patronName says: Thanks for the $drinkName!"
        }
    println(phrase)

}

    //Драконий язык
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
