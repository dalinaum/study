package io.github.dalinaum.sburrestdemo

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/coffees")
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

    @GetMapping
    fun getCoffees(): List<Coffee> {
        return coffees
    }

    @GetMapping("/{id}")
    fun getCoffeeById(@PathVariable id: String): Coffee? {
        return coffees.find { it.id == id }
    }

    @PostMapping
    fun postCoffee(@RequestBody coffee: Coffee): Coffee {
        coffees.add(coffee)
        return coffee
    }

    @PutMapping("/{id}")
    fun putCoffee(@PathVariable id: String, @RequestBody coffee: Coffee): Coffee {
        val index = coffees.indexOfFirst { it.id == id }
        if (index == -1) {
            return postCoffee(coffee)
        }
        coffees[index] = coffee
        return coffee
    }

    @DeleteMapping("/{id}")
    fun deleteCoffee(@PathVariable id: String) {
        coffees.removeIf { it.id == id }
    }
}