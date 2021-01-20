package ch.keepcalm.hateoas

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HateoasDemoApplication

fun main(args: Array<String>) {
	runApplication<HateoasDemoApplication>(*args)
}
