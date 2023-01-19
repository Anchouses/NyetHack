import java.io.File

fun main() {
    var x = (1..3).toList()
    println(x)
    val y = 1 !in 3 downTo 1
    println(y)
    val numberLetter = "Mississippi".count { it == 's' }
    println(numberLetter)

    val addLambda = { a: Int, b: Int ->
        a + b
    }

    val multy = { a: Int, b: Int ->
        a * b
    }
    val unicod = '\u0059'
    println(unicod)

    "Dragon's Breath".forEach {
        println("$it\n")
    }
    Integer.toBinaryString(42)
    operateOnNumbers(2, 4, addLambda)
    operateOnNumbers(3, 5, multy)

    // apply
    val menuFile = File("menu-file.txt").apply {
        setReadable(true)
        setWritable(true)
        setExecutable(false)
    }
    println(menuFile)
//    let
    val firstItemSquared = listOf(1, 2, 3, 4, 5, 6).first().let {
        it * it
    }
    println(firstItemSquared)

    println(formatGreeting("Ann"))

    val menuFily = File("menu-file.txt")
 //   val servesDragonsBreath = menuFily.run {
 //       readText().contains("Dragon's Breath")
 //   }



    val get = "fkjslglkdfjkldsjsajfdlkasjfdlsajdlks"
        .run(::nameIsLong)
        .run(::playerMessage)
        .run(::println)


val fileContents = File("my-file.txt")
    .takeIf { it.canRead() && it.canWrite() }?.readText()
    println(fileContents)

    val fileContent = File("file.txt")
        .takeUnless{it.isHidden}?.readText()
}


fun operateOnNumbers(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

fun formatGreeting(vipGuest: String?): String {
    return vipGuest?.let {
        "Welcome, $it! Please, go straight back - your table is ready"
    } ?: "Welcome to $TAVERN_NAME. You'll be seated soon"

}

fun nameIsLong(name: String) = name.length >= 20

fun playerMessage(nameTooLong: Boolean): String{
    return if (nameTooLong) {
        "Name is too long"
    } else {
        "Welcome"
    }
}
