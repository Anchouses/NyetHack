package tests

class Weapon(val name: String)
class Playerr {
    var weapon: Weapon? = Weapon("Ebony Kris")
    fun printWeaponName() {
        weapon?.also {
            println(it.name)
        }
    }
}

fun main(args: Array<String>){
    Playerr().printWeaponName()
}