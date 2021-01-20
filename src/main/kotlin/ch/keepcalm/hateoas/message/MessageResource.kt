package ch.keepcalm.hateoas.message

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

    @GetMapping
    fun index() : List<Message> = service.findMessages()  //listOf(Message(id= "1", text = "Hello"), Message(id = "2", text = "Hola"))

    @PostMapping
    fun post(@RequestBody message: Message){
        service.post(message)
    }

    @GetMapping(value = ["/hal/messages"])
    fun whatAboutHATEOAS (): CollectionModel<Message> {
        val selfLink : Link = linkTo(methodOn(MessageResource::class.java).whatAboutHATEOAS()).withSelfRel()
        return CollectionModel.of(service.findMessages() , selfLink)
    }
}
