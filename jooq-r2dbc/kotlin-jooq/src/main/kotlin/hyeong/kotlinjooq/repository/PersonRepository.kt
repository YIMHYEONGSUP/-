package hyeong.kotlinjooq.repository

import hyeong.kotlinjooq.domain.Person
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.Result
import org.jooq.impl.DSL.field
import org.jooq.impl.DSL.table
import org.springframework.stereotype.Repository

@Repository
class PersonRepository(private val dslContext: DSLContext) {

    fun insert(person: Person): Int {
        val res = dslContext.insertInto(table("person"))
            .columns(field("name"), field("age"))
            .values(person.name, person.age)
            .execute()
        return res
    }

    fun findAll(): Result<Record> {
        val result = dslContext.selectFrom("person").fetch()
        println("result = $result")
        return result
    }
}