fun main() {
    val counter1 = countingLambda()
    val counter2 = countingLambda()

    println(counter1()) // > 1
    println(counter2()) // > 1
    println(counter1()) // > 2
    println(counter1()) // > 3
    println(counter2()) // > 2

    val names = arrayOf("ZZZZZ", "CCC", "mmmmmmmm")
    val namesByLength = names.sortedWith(compareBy{
        it.length})
    println(namesByLength)
    for (items in namesByLength){
        println(items)
    }



    val values = listOf(1,2,3,4,5,6,7)
    values.forEach{
        println("$it: ${it*3}\n")
    }

    val price = listOf(0.33, 8.0, 4.99)
    val largePrice = price.filter{
        it > 5.0
    }
    println(largePrice)

    val salePrice = price.map{
       "%.2f".format(it * 0.9)
    }
    println("Sale price is $salePrice")

    val someText = listOf("42", "how", "000", "9999")
    val someInt = someText.mapNotNull {
        it.toIntOrNull()
    }
    println(someInt)

    val sum = price.fold(0.0){a, b ->
        a + b
    }
println(sum)


}

fun countingLambda(): () -> Int {
    var counter = 5
    val incrementCounter: () -> Int = {
        counter += 7
        counter
    }
    return incrementCounter
}