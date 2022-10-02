package com.example.demowebflux.modules

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "members")
data class Member(
    @Id
    @Column("id")
    val id: Long = 0,

    @Column("first_name")
    val firstName: String,

    @Column("last_name")
    val lastName: String

) : Persistable<Long> {

    override fun getId(): Long = id

    override fun isNew(): Boolean = id == 0L

}
