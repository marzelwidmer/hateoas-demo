package ch.keepcalm.hateoas.customer

import org.springframework.hateoas.*
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.hateoas.server.mvc.afford
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder

@RestController
class CustomerController(private val customerService: CustomerService) {

    @GetMapping(value = ["/api/customers/{id}"])
    fun one(@PathVariable id: Int): EntityModel<Customer> {
        val selfLink: Link = linkTo(
            methodOn(CustomerController::class.java).one(id)
        ).withSelfRel()
        val create: Affordance = afford<CustomerController> {
            methodOn(CustomerController::class.java).add(customer = Customer(firstName = "", lastName = ""))
        }
        val aggregateRoot : Link = linkTo(methodOn(CustomerController::class.java).all()).withRel("customers")
            .andAffordance(WebMvcLinkBuilder.afford(methodOn(CustomerController::class.java).add(customer = Customer(firstName = "", lastName = ""))))

        return EntityModel.of(customerService.getCustomer(id) as Customer, selfLink.andAffordance(create), aggregateRoot)
    }


    @GetMapping(value = ["/api/customers"])
    fun all(): CollectionModel<Customer> {
        val selfLink: Link = linkTo(
            methodOn(CustomerController::class.java).all()
        ).withSelfRel()
        return CollectionModel.of(customerService.getCustomers(), selfLink)
    }


    @PostMapping(value = ["/api/customers"])
    fun add(@RequestBody customer: Customer): HttpEntity<*> {
        println("Add")
        println(customer)
        return  ResponseEntity.EMPTY
    }

//    @GetMapping("/employees/{id}")
//    fun findOne(@PathVariable id: Int): EntityModel<Customer> {
//        val selfLink: Link = linkTo(methodOn(CustomerController::class.java).one(id)).withSelfRel()
//        val affordance: Link = Affordances.of(linkTo(methodOn(CustomerController::class.java).add(Customer(firstName = "", lastName = "") )).withRel(IanaLinkRelations.CREATE_FORM_VALUE))
//            .afford(HttpMethod.POST)
//            .withInput(Customer::class.java)
//            .withName(IanaLinkRelations.CREATE_FORM_VALUE)
//            .toLink()
//        return EntityModel.of(customerService.getCustomer(id) as Customer, selfLink, affordance)
//    }
}
