package com.example.demowebflux.modules

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.concurrent.Flow

interface MemberRepository : CoroutineCrudRepository<Member, Long> {

    suspend fun saveAll(items: Iterable<Member>): List<Member>
}