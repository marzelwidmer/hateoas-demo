package ch.keepcalm.hateoas.person

import org.springframework.stereotype.Service

@Service
class PersonService(private val repository: PersonRepository) {

    fun post(person: Person) {
       repository.save(person)
    }

    fun findPersons(): List<Person>? {
        return repository.findPersons()
    }

}
