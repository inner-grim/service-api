package com.team.innergrim.innergrimapi.web.dto

import com.team.innergrim.innergrimapi.entity.Member
import com.team.innergrim.innergrimapi.entity.Membership
import com.team.innergrim.innergrimapi.enums.Gender
import com.team.innergrim.innergrimapi.enums.SocialType
import com.team.innergrim.innergrimapi.enums.YnCode
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class MemberRequestDto {

    data class MemberCreate(
        @field:Schema(description = "소셜 type", required = true)
        @field:NotNull
        val socialType: SocialType,

        @field:Schema(description = "소셜 ID", required = true)
        @field:NotBlank
        val socialId: String,

        @field:Schema(description = "이름", required = false)
        val name: String? = null,

        @field:Schema(description = "이메일", required = false)
        val email: String? = null,

        @field:Schema(description = "생년 월일", required = false)
        val birthDate: String? = null,

        @field:Schema(description = "성별", required = false)
        val gender: Gender? = null,

        @field:Schema(description = "전화번호", required = false)
        val phoneNumber: String? = null,

        @field:Schema(description = "CI", required = false)
        val ci: String? = null,

        @field:Schema(description = "프로필 이미지", required = false)
        val profileImage: String? = null
    ) {
        fun toMemberEntity(membership: Membership):Member {
            return Member().apply {
                this.socialType = this@MemberCreate.socialType
                this.membership = membership
                this.socialId = this@MemberCreate.socialId
                this.name = this@MemberCreate.name
                this.email = this@MemberCreate.email
                this.birthDate = this@MemberCreate.birthDate
                this.gender = this@MemberCreate.gender
                this.phoneNumber = this@MemberCreate.phoneNumber
                this.ci = this@MemberCreate.ci
                this.profileImage = this@MemberCreate.profileImage
                this.blockYn = YnCode.N
            }
        }
    }
}