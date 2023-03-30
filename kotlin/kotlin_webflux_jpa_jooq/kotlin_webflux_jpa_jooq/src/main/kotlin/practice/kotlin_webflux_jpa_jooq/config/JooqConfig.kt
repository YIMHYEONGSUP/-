package practice.kotlin_webflux_jpa_jooq.config

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.conf.Settings
import org.jooq.impl.DSL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class JooqConfig (private val dataSource: DataSource){

    @Bean
    fun dslContext(): DSLContext {
        val settings = Settings()
        settings.withExecuteWithOptimisticLocking(true)

        return DSL.using(dataSource, SQLDialect.MARIADB, settings)
    }

}