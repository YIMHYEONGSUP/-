package hyeong.kotlinjooq

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.junit.jupiter.api.Test

class JooqTest {

    @Test
    fun `test`(){
        val dataSource = org.h2.jdbcx.JdbcDataSource()
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")

        val dsl: DSLContext = DSL.using(dataSource, SQLDialect.H2)
        dsl.execute("CREATE TABLE person (id INTEGER PRIMARY KEY, name VARCHAR(50))")
        dsl.execute("INSERT INTO person (id, name) VALUES (1, 'John Doe')")

        val result = dsl.select().from("person").fetch()
        require(result.size == 1)
        require(result[0].get("PUBLIC.PERSON.id") == 1)
        require(result[0].get("PUBLIC.PERSON.name") == "John Doe")

    }
}