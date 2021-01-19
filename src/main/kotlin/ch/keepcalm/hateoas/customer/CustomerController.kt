package ch.keepcalm.hateoas.customer

import org.springframework.hateoas.Affordance
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.afford
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController(private val customerService: CustomerService) {

    @GetMapping(value = ["/api/customers/{id}"])
    fun one(@PathVariable id: Int): EntityModel<Customer> {
        val selfLink: Link = linkTo(
            methodOn(CustomerController::class.java).one(id)
        ).withSelfRel()

        val update : Affordance = afford<Any> {
            methodOn(CustomerController::class.java).update(customer = Customer(firstName = "", lastName = ""), id)
        }
//        val aggregateRoot : Link = linkTo(methodOn(CustomerController::class.java))
        return EntityModel.of(customerService.getCustomer(id) as Customer, selfLink)
    }

    @PutMapping(value = ["/api/customers/{id}"])
    fun update(@RequestBody customer: Customer, @PathVariable id: Int) {
        println("Update")
    }

    @GetMapping(value = ["/api/customers"])
    fun all(): CollectionModel<Customer> {
        val selfLink: Link = linkTo(
            methodOn(CustomerController::class.java).all()
        ).withSelfRel()
        return  CollectionModel.of(  customerService.getCustomers(), selfLink )
    }
}
