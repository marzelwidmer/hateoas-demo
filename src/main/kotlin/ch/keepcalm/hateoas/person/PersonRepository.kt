package ch.keepcalm.hateoas.person

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface PersonRepository : CrudRepository<Person?, Long?> {

    @Query(value = "select * from persons")
    fun findPersons(): List<Person>?
}
