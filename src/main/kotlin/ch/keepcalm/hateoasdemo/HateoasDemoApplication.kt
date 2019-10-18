package ch.keepcalm.hateoasdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.hateoas.*
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class HateoasDemoApplication

fun main(args: Array<String>) {
	runApplication<HateoasDemoApplication>(*args)
}




@RestController
@RequestMapping("/", produces = [MediaTypes.HAL_JSON_VALUE])
class IndexController : RepresentationModel<IndexController>() {

	@GetMapping
	fun api(): RepresentationModel<IndexController> {

		val model = RepresentationModel<IndexController>()
		model.add(linkTo(methodOn(PersonController::class.java).show()).withRel("person"))
		model.add(linkTo(methodOn(PersonController::class.java).hello()).withRel("hello"))

		val method = PersonController::class.java.getMethod("show")
		model.add(linkTo(method, 2L).withSelfRel())

		model.add(Link("/some-resource", IanaLinkRelations.NEXT))
		model.add(Link("/some-resource", IanaLinkRelations.PREVIOUS))

		model.add(Link("/some-resource", IanaLinkRelations.PREFETCH))
		model.add(Link("/some-resource", IanaLinkRelations.AUTHOR))
		model.add(Link("/some-resource", IanaLinkRelations.EDIT))
		model.add(Link("/some-resource", IanaLinkRelations.LAST))

		return model

	}
}



@RestController
@RequestMapping("/api/persons")
class PersonController{

	@GetMapping
	fun show (): PersonModel {
		val person = PersonModel(id= 42, firstname = "John", lastname = "Doe")
		//person.add(Link("https://myhost/people/42"))
		val self = linkTo(PersonController::class.java).slash(person.id).withSelfRel()
		person.add(self)
		return person
	}

	@GetMapping(path = ["/hello"])
	fun hello () = EntityModel("hello")


}



open class PersonModel(val id: Int, val firstname: String, val lastname : String) : RepresentationModel<PersonModel>()