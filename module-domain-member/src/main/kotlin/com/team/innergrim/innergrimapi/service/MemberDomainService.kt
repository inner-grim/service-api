package com.team.innergrim.innergrimapi.service

import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.repository.MemberRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class MemberDomainService (private val memberRepository: MemberRepository) {

    fun getMembers (): List<Member> {
        return memberRepository.findAll();
    }

    fun getMemberDetail (id: Long): Optional<Member> {
        return memberRepository.findById(id);
    }

}