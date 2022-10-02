package com.example.demowebflux.config

import com.example.demowebflux.modules.Member
import com.example.demowebflux.modules.MemberRepository
import io.r2dbc.spi.ConnectionFactory
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@EnableR2dbcRepositories
@Configuration
class DBInit(
    private val repository: MemberRepository
) {

    val log = KotlinLogging.logger {}

    @Bean
    fun connectionFactoryInitializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer {
        return ConnectionFactoryInitializer().let {
            it.setConnectionFactory(connectionFactory)
            it.setDatabasePopulator(ResourceDatabasePopulator(ClassPathResource("schema.sql")))
            it
        }
    }

    @Bean
    fun demo() = CommandLineRunner {
        runBlocking {
            val members = repository.saveAll(
                listOf(
                    Member(firstName = "Jay", lastName = "Park"),
                    Member(firstName = "Haon", lastName = "Kim"),
                    Member(firstName = "Jessy", lastName = "Lim")
                )
            )
            log.info { ">>>> db initialized. members = $members" }
        }
    }
}