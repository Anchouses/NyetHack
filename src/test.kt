fun main(){
 var x =  (1..3).toList()
 println(x)
    val y = 1 !in 3 downTo 1
    println(y)
val numberLetter = "Mississippi".count{it == 's'}
    println(numberLetter)

val addLambda = { a: Int, b: Int ->
        a + b
    }

val multy = {a: Int, b: Int ->
    a * b
}
    val unicod = '\u0159'
    println(unicod)

    "Dragon's Breath".forEach{
        println("$it\n")
    }

operateOnNumbers(2,4, addLambda)
operateOnNumbers(3, 5, multy)
}

fun operateOnNumbers(a: Int, b: Int, operation: (Int, Int) -> Int): Int{
    val result = operation(a, b)
    return result
}

