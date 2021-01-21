package ch.keepcalm.hateoas.message

import ch.keepcalm.hateoas.customer.CustomerResource
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageResource (private val service: MessageService) {

    @PostMapping(value = ["/messages"])
    fun post(@RequestBody message: Message){
        service.post(message)
    }

    @GetMapping(value = ["/messages"])
    fun index (): CollectionModel<Message> {
        val selfLink : Link = linkTo(methodOn(MessageResource::class.java).index()).withSelfRel()
//        val customers : Link = linkTo(methodOn(CustomerResource::class.java).all()).withRel("customers")
        return CollectionModel.of(service.findMessages() , selfLink)
    }
}
