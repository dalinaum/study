package io.github.dalinaum.sburrestdemo

import org.springframework.data.repository.CrudRepository

interface CoffeeRepository : CrudRepository<Coffee, String>