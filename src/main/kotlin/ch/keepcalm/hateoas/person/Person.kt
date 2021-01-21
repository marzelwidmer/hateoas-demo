package ch.keepcalm.hateoas.person

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(value = "PERSONS")
data class Person (@Id val id: String?, val firstName: String, val lastName: String)


