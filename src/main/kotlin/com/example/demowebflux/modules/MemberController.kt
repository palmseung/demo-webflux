package com.example.demowebflux.modules

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val repository: MemberRepository
) {

    @GetMapping("/members")
    fun getAll(): Flow<MemberDto.Response> {
        return repository.findAll()
            .transform<Member, MemberDto.Response> {
                emit(MemberDto.Response(it.id, "${it.firstName} ${it.firstName}"))
            }

    }

}
