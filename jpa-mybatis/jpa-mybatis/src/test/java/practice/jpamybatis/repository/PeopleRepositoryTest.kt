package practice.jpamybatis.repository

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import practice.jpamybatis.entity.People
import javax.sql.DataSource

//@DataJpaTest
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@Rollback(false)
class PeopleRepositoryTest (@Autowired private val dataSource: DataSource){
    @Autowired
    private lateinit var peopleRepository: PeopleRepository

    @BeforeAll
    fun `create table`(){
        val jdbc = JdbcTemplate(dataSource)
        jdbc.execute("""
        DROP TABLE IF EXISTS people;
        CREATE TABLE people (
            id BIGINT AUTO_INCREMENT PRIMARY KEY,
            name VARCHAR(255),
            age INT
        );
    """.trimIndent())
    }

    @Test
    fun `insert` (){
        // given
        val people = People(name = "Alice", age = 20)
        // when
        val res = peopleRepository.save(people)
        // then
        println("res = $res")

    }

    @Test
    fun `find` (){
        // given

        // when
        val res = peopleRepository.findAll()
        // then
        println("res = $res")

    }
}