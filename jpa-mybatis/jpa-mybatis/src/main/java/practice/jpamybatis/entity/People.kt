package practice.jpamybatis.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class People(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long?,
    val name: String,
    val age: Int
) {
    constructor(name: String, age: Int) : this(null, name, age)
}

