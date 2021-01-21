package ch.keepcalm.hateoas.person

import ch.keepcalm.hateoas.message.MessageResource
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController (private val service : PersonService) {

    @PostMapping(value = ["/persons"])
    fun post(@RequestBody person: Person){
        service.post(person)
    }

    @GetMapping(value = ["/persons"])
    fun index (): CollectionModel<Person> {
        val selfLink : Link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController::class.java).index()).withSelfRel()
        return CollectionModel.of(service.findPersons() , selfLink)
    }
}
