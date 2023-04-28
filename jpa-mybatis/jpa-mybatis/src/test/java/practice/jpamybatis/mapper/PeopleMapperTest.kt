package practice.jpamybatis.mapper

import org.apache.ibatis.annotations.Mapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mybatis.spring.SqlSessionTemplate
import org.mybatis.spring.annotation.MapperScan
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension


@SpringBootTest
class PeopleMapperTest {

    @Autowired
    private lateinit var peopleMapper: PeopleMapper

    @Test
    fun `find` (){

        // given

        // when
        val res = peopleMapper.findAll()
        // then
        println("result = $res")
    }
}