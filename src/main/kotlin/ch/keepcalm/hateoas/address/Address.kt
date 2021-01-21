package ch.keepcalm.hateoas.address

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(value = "ADDRESS")
class Address (@Id val id: String?, val streetName: String)


