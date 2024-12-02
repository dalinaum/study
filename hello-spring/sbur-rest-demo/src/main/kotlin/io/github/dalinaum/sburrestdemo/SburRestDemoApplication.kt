package io.github.dalinaum.sburrestdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class SburRestDemoApplication

fun main(args: Array<String>) {
    runApplication<SburRestDemoApplication>(*args)
}
