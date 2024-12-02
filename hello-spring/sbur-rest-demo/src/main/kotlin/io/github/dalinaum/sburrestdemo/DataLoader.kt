package io.github.dalinaum.sburrestdemo

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class DataLoader(val coffeeRepository: CoffeeRepository) {

    @PostConstruct
    private fun loadData() {
        coffeeRepository.saveAll(
            listOf(
                Coffee("Café Cereza"),
                Coffee("Café Ganador"),
                Coffee("Café Lareno"),
                Coffee("Café Três Pontas"),
            )
        )
    }
}