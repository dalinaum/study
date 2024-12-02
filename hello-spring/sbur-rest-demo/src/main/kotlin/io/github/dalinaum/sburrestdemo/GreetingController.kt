package io.github.dalinaum.sburrestdemo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/greeting")
class GreetingController(val greeting: Greeting) {
    @GetMapping
    fun getGreeting(): String {
        return greeting.name
    }

    @GetMapping("/coffee")
    fun getNameAndCoffee(): String {
        return greeting.coffee;
    }
}