package ch.keepcalm.hateoas.message

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(value = "MESSAGES")
data class Message(@Id val id: String?, val text: String)
