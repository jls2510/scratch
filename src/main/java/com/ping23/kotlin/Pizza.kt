package com.ping23.kotlin

/**
 * Created by Naren on 6/15/17.
 */

class Pizza(val number: Int, val toppings: List<Toppings>) {

    private var pizzaMaker: PizzaMaker? = null

    init {
        pizzaMaker = PizzaMaker.Companion.getInstance(heat = 400, size = 4)
    }

    fun makePizza(): String? {
        return pizzaMaker?.makePizza(number = number, toppings = toppings)
    }

    override fun toString(): String {
        return "Pizza(Number=$number, toppings=$toppings)"
    }

}


/**
 * inner class PizzaMaker
 */
private class PizzaMaker private constructor(val heat: Int, val size: Int?) {

    companion object {
        fun getInstance(heat: Int, size: Int?): PizzaMaker {
            return PizzaMaker(heat, size)
        }

    }

    fun makePizza(number: Int, toppings: List<Toppings>): String {
        return "Pizza (Number = $number, Toppings = $toppings)"
    }

    override fun toString(): String {
        return "PizzaMaker(heat=$heat, size=$size)"
    }

}

enum class Toppings {
    Pineapple, Pepperoni, Chicken, Olives, Mushroom, Tomato, BellPeppers, Onions, Cheese
}
