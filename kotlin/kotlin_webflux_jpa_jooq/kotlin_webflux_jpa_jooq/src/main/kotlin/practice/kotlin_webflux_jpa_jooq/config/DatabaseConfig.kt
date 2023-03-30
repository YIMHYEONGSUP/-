package practice.kotlin_webflux_jpa_jooq.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@Configuration
class DatabaseConfig (val dataSource: DataSource) {

    @Bean
    fun jdbcTemplate(): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }
}