package ch.keepcalm.hateoas.customer

import org.springframework.data.relational.core.mapping.Table

@Table(value = "CUSTOMERS")
data class Customer(val firstName: String, val lastName: String)
