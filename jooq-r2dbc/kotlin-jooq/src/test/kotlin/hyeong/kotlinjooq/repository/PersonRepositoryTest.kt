package hyeong.kotlinjooq.repository

import hyeong.kotlinjooq.domain.Person
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.impl.DSL.field
import org.jooq.impl.DSL.table
import org.jooq.impl.SQLDataType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.sql.DriverManager

@SpringBootTest
//@Transactional
internal class PersonRepositoryTest() {

    @Autowired
    lateinit var personRepository: PersonRepository

    @Autowired
    lateinit var dslContext : DSLContext

    @BeforeEach
    fun setUp() {
        // DSLContext 인스턴스 생성
//        val connection = DriverManager.getConnection("jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1")
        val connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test;MODE=MySQL;DB_CLOSE_DELAY=-1", "sa" , "")

        dslContext = DSL.using(connection, SQLDialect.H2)
        // PersonRepository에 DSLContext 주입
        personRepository = PersonRepository(dslContext)

        // 데이터베이스 초기화
//        dslContext.execute("drop table if exists person")
//        dslContext.execute("CREATE TABLE person (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50) NOT NULL, age INT)")
    }

    @Test
    fun `insert person`() {
        // given
        val name = "Alice"
        val age = 30
        val person = Person(id=null , name = name, age = age)

        // when
        val result = personRepository.insert(person)
        println(" result return value  = $result")

        // then
        assertThat(result).isEqualTo(1)

        dslContext.transaction { configuration ->
            val ctx = DSL.using(configuration)
            ctx.execute("commit")
        }


        val actual = dslContext.selectFrom(table("person"))
            .where(field("name").eq(name))
            .fetchOne()



        println("actual result = $actual")
    }

    @Test
    fun `find Person name Alice`(){
        //given
        //when
        //then



    }
}