package ch.keepcalm.hateoas.customer

import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository) {

    fun getCustomer(id: Int): Customer? {
        return customerRepository.findById(id)
    }

    fun getCustomers() : List<Customer> {
        return customerRepository.getAll()
    }

}
