fun main(args:Array<String>){
    var swordsJuggling: Int? = null
    val isJugglingProficient = (1..3).random()
    if (isJugglingProficient == 3) {
        swordsJuggling = 2
    }

    try{
        proficiencyCheck(swordsJuggling)
        swordsJuggling = swordsJuggling!!.plus(2)
    } catch (e: Exception){
        println(e)
    }

    println("You juggle $swordsJuggling swords!")
}

fun proficiencyCheck(swordsJiggling: Int?){
    checkNotNull(swordsJiggling) { "Player cannot juggle swords" }
}

class UnskilledSwordJugglerException():
    IllegalStateException("Player cannot juggle swords, he is asshole")

