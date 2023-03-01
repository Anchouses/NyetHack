package com.bignerdranch.nyethack

enum class Direction(private val coordinate: Coordinate) {      //координаты направлений - в зависимости от направления присваивает значения координатам
    NORTH(Coordinate(0, -1)),
    EAST(Coordinate(1, 0)),
    SOUTH(Coordinate(0, 1)),
    WEST(Coordinate(-1, 0));

    fun updateCoordinate(playerCoordinate: Coordinate) =         // складывает координаты направлений и текущие координаты игрока
        coordinate + playerCoordinate
}

data class Coordinate(val x: Int, val y: Int){     // класс "координаты", определяет условия
    val isInBounds = x >= 0 && y >= 0

    operator fun plus(other: Coordinate) = Coordinate(x + other.x, y + other.y)  // перегружает (переопределяет) орератор "+" для класса Координаты

}
