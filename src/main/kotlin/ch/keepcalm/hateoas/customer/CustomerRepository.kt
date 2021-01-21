package ch.keepcalm.hateoas.customer

import ch.keepcalm.hateoas.message.Message
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<Customer, String> {

    @Query("select * from customers")
    fun findCustomers(): List<Customer>

}
