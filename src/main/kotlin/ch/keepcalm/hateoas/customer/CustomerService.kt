package ch.keepcalm.hateoas.customer

import org.springframework.stereotype.Service

@Service
class CustomerService(private val repository: CustomerRepository) {

    fun findCustomerById(id: String): Customer? {
        return repository.findById(id).orElse(null)
    }

    fun findCustomers() : List<Customer> {
        return repository.findCustomers()
    }

    fun post(customer: Customer) {
        repository.save(customer)
    }
}
