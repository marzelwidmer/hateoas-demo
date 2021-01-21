package ch.keepcalm.hateoas.customer

import ch.keepcalm.hateoas.message.MessageResource
import org.springframework.hateoas.*
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.hateoas.server.mvc.afford
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder

@RestController
class CustomerResource(private val service: CustomerService) {

    @GetMapping(value = ["/customers/{id}"])
    fun one(@PathVariable id: String): EntityModel<Customer> {
        val selfLink: Link = linkTo(
            methodOn(CustomerResource::class.java).one(id)
        ).withSelfRel()
        val create: Affordance = afford<CustomerResource> {
            methodOn(CustomerResource::class.java).add(customer = Customer(firstName = "", lastName = ""))
        }
        val aggregateRoot : Link = linkTo(methodOn(CustomerResource::class.java).all()).withRel("customers")
            .andAffordance(WebMvcLinkBuilder.afford(methodOn(CustomerResource::class.java).add(customer = Customer(firstName = "", lastName = ""))))

        return EntityModel.of(service.findCustomerById(id) as Customer, selfLink.andAffordance(create), aggregateRoot)
    }


    @GetMapping(value = ["/customers"])
    fun all(): CollectionModel<Customer> {
        val selfLink: Link = linkTo(
            methodOn(CustomerResource::class.java).all()
        ).withSelfRel()
        val messagesLink : Link = linkTo(methodOn(MessageResource::class.java).index()).withRel("messages")
        return CollectionModel.of(service.findCustomers(), selfLink, messagesLink)
    }


    @PostMapping(value = ["/customers"])
    fun add(@RequestBody customer: Customer): HttpEntity<*> {
        service.post(customer)
        return  ResponseEntity.EMPTY
    }
}
