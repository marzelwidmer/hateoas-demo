package ch.keepcalm.hateoas.message

import org.springframework.web.bind.annotation.*

@RestController
class MessageResource (val service: MessageService) {

    @GetMapping
    fun index() : List<Message> = service.findMessages()  //listOf(Message(id= "1", text = "Hello"), Message(id = "2", text = "Hola"))

    @PostMapping
    fun post(@RequestBody message: Message){
        service.post(message)
    }
}
