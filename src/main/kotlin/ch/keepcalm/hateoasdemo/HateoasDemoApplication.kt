package ch.keepcalm.hateoasdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.Link
import org.springframework.hateoas.MediaTypes
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.hateoas.server.mvc.add
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class HateoasDemoApplication

fun main(args: Array<String>) {
	runApplication<HateoasDemoApplication>(*args)
}




@RestController
@RequestMapping("/", produces = [MediaTypes.HAL_JSON_VALUE])
class IndexController {

//	@GetMapping
//	fun api() {
//		val method = PersonController::class.java.getMethod("show", Long::class.java)
//		val link = linkTo(method, 2L).withSelfRel()
////		val link = linkTo(PersonController::class.java).slash(person.getId()).withSelfRel()
//
//	}



	@GetMapping
	fun api(): RepresentationModel<Index> {

		val model = RepresentationModel<Index>()
		model.add(linkTo(methodOn(PersonController::class.java).show()).withRel("person"))

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

open class Index : RepresentationModel<Index>()


@RestController
@RequestMapping("/api/persons")
class PersonController{

	@GetMapping
	fun show (): PersonModel {
		val person = PersonModel(id= 42, firstname = "John", lastname = "Doe")
//		person.add(Link("https://myhost/people/42"))
		val self = linkTo(PersonController::class.java).slash(person.id).withSelfRel()
		person.add(self)
		return person
	}
}



open class PersonModel(val id: Int, val firstname: String, val lastname : String) : RepresentationModel<PersonModel>()