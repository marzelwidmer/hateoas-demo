package ch.keepcalm.hateoas.message

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import ch.keepcalm.hateoas.person.Person




interface MessageRepository : CrudRepository<Message, String> {

    @Query("select * from messages")
    fun findMessages(): List<Message>
}
internal interface PersonRepository : CrudRepository<Person?, Long?>
