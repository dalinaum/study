package io.github.dalinaum.sburrestdemo

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class SburRestDemoApplication

fun main(args: Array<String>) {
    runApplication<SburRestDemoApplication>(*args)
}

class Coffee @JsonCreator constructor(
    val id: String = UUID.randomUUID().toString(),
    var name: String
)