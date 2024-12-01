package io.github.dalinaum.sburrestdemo

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class Coffee(val name: String = "Cup O' Joe", @Id val id: String = UUID.randomUUID().toString())