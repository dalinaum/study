package io.github.dalinaum.sburrestdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@ConfigurationPropertiesScan
@SpringBootApplication
class SburRestDemoApplication {

    @Bean
    @ConfigurationProperties(prefix = "droid")
    fun createDroid(): Droid {
        return Droid()
    }
}

fun main(args: Array<String>) {
    runApplication<SburRestDemoApplication>(*args)
}
