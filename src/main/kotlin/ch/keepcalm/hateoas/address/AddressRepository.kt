package ch.keepcalm.hateoas.person

import org.springframework.data.repository.CrudRepository

internal interface AddressRepository : CrudRepository<Address?, Long?>
