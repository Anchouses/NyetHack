
const val TAVERN_NAME = "Lorry's Folly"

fun main(args: Array<String>){
    //placeOrder("shandy,DrAgon's Breath, 5.91")
placeOrder("elixir,DrAgon's BreAth,4.12")

}

fun placeOrder(menuData: String){
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$NAME speaks with $tavernMaster about his order")
    val (type, name, price) = menuData.split(',')
    val message = "$NAME buys a $name ($type) of $price"
    println(message)

    var phrase = if (name == "DrAgon's BreAth"){
        "$NAME exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "$NAME says: Thanks for the $name!"
    }
    println(phrase)

}

fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aAeEiIoOuU]")) {
        when (it.value) {
            "a", "A" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }
