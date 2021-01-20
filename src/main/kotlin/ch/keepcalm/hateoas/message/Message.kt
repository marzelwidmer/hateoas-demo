package ch.keepcalm.hateoas.message

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.rest.core.annotation.Description

@Table(value = "MESSAGES")
data class Message(@Id val id: String?,  @Description(value = "Just a custom Message") val text: String)
