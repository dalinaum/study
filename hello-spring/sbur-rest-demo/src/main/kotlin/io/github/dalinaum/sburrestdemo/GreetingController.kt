package io.github.dalinaum.sburrestdemo

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/greeting")
class GreetingController {
    @Value("\${greeting-name: Mirage}")
    private lateinit var name: String

    @Value("\${greeting-coffee: \${greeting-name} is dringking Café Genador}")
    private lateinit var coffee: String

    @GetMapping
    fun greeting(): String {
        return name
    }

    @GetMapping("/coffee")
    fun getNameAndCoffee(): String {
        return coffee;
    }
}