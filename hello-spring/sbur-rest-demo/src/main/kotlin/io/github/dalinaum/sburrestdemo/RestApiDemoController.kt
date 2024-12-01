package io.github.dalinaum.sburrestdemo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class RestApiDemoController {
    private val coffees = mutableListOf<Coffee>()

    init {
        coffees.addAll(
            listOf(
                Coffee(name = "Café Cereza"),
                Coffee(name = "Café Ganador"),
                Coffee(name = "Café Lareno"),
                Coffee(name = "Café Três Pontas"),
            )
        )
    }

    @GetMapping("/coffees")
    fun getCoffees(): List<Coffee> {
        return coffees
    }

    @GetMapping("/coffees/{id}")
    fun getCoffeeById(@PathVariable id: String): Coffee? {
        return coffees.find { it.id == id }
    }

    @PostMapping("/coffees")
    fun postCoffee(@RequestBody coffee: Coffee): Coffee {
        coffees.add(coffee)
        return coffee
    }
}