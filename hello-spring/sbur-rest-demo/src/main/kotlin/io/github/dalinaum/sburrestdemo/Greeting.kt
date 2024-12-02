package io.github.dalinaum.sburrestdemo

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "greeting")
class Greeting(val name: String, val coffee: String)