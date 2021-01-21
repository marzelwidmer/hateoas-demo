package ch.keepcalm.hateoas.address

import org.springframework.data.repository.CrudRepository

interface AddressRepository : CrudRepository<Address?, Long?>
