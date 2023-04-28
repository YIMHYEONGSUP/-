package hyeong.kotlinjooq.config

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.sql.DriverManager

@Configuration
class JooqConfig {

    @Bean
    fun dslContext() : DSLContext {
        val connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test;MODE=MySQL;DB_CLOSE_DELAY=-1", "sa", "")
        return DSL.using(connection, SQLDialect.H2)
    }

}