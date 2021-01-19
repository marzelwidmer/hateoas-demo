package ch.keepcalm.hateoas.customer

import org.springframework.stereotype.Repository

@Repository
class CustomerRepository {

    private val customers = mapOf(1 to Customer(firstName = "John", lastName= "Doe"), 2 to Customer(firstName = "Jane", lastName= "Doe"), 3 to Customer(firstName = " Jack", lastName= "Doe"))

    fun findById(id: Int): Customer? {
      return customers[id]
    }

    fun getAll(): List<Customer> {
        return customers.values.toList()
    }
}
