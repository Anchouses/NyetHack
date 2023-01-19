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
var leavePatron = mutableMapOf<String, Double>()
var set = mutableMapOf<String, Double>()

fun main(args: Array<String>){

    var isTavernOpen = true
    val isClosingTime = false
    while (isTavernOpen == true){
        if (isClosingTime) {
            isTavernOpen = false
        }
        println("Having a grand old time!")
        break
    }


    (0..9).forEach{
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }

    uniquePatrons.forEach{
        patronGold[it] = 6.0
    }


    var orderCount = 0
    while (orderCount <= 9){
        placeOrder(uniquePatrons.shuffled().first(),
            menuList.shuffled().first())
        orderCount ++
    }

    displayPatronBalances()

    println(uniquePatrons)
    println(patronGold)
    println(leavePatron)
    println(set)
    patronGold = set
    println(patronGold)
}
// Совершаем покупку
fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}

fun displayPatronBalances(){

    patronGold.forEach { (patron, balance) ->
       println("$patron, balance: ${"%.2f".format(balance)}")
        if (balance <= 0.0){
            println("Bouncer say: $patron, you have no money, pal! Get out!")

            uniquePatrons.remove(patron)

            leavePatron += patron to balance
        } else {
            set += patron to balance
        }

    }
}
// Оформляем заказ
 fun placeOrder(patronName: String, menuData: String){
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about his order")
    val (type, name, price) = menuData.split(',')

    val message = "$patronName buys a $name ($type) of $price"
    println(message)

    performPurchase(price.toDouble(), patronName)

    var phrase = if (name == "Dragon's Breath"){
        "$patronName exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
        } else {
            "$patronName says: Thanks for the $name!"
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
