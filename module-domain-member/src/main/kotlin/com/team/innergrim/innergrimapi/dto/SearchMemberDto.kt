package com.team.innergrim.innergrimapi.dto

import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.enums.MemberType
import com.team.innergrim.innergrimapi.enums.SocialType
import com.team.innergrim.innergrimapi.specification.MemberSpecification
import org.springframework.data.jpa.domain.Specification

data class SearchMemberDto(
    val id: Long? = null,
    val loginId: String? = null,
    val socialType: SocialType? = null,
    val memberType: MemberType,
    val name: String? = null,
    val roleId: Long? = null,
    val nickname: String? = null,
) {
    val specification: Specification<Member> = MemberSpecification.setSpecification(this)
}
