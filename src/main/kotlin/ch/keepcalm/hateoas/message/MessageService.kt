package ch.keepcalm.hateoas.message

import org.springframework.stereotype.Service

@Service
class MessageService(val repository: MessageRepository) {

    fun findMessages() :List<Message> = repository.findMessages()

    fun post(message: Message) = repository.save(message)

}
