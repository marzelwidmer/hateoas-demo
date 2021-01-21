package ch.keepcalm.hateoas.person

@Entity
class Person {
    @Id
    @GeneratedValue
    private val id: Long? = null
    private val firstName: String? = null
    private val lastName: String? = null

    @OneToOne
    private val address: Address? = null
}
