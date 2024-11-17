package com.sparta.gaeppa.profile.entity;

import com.sparta.gaeppa.global.base.BaseEntity;
import com.sparta.gaeppa.members.entity.Member;
import com.sparta.gaeppa.members.entity.MemberGender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_profile")
public class Profile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profile_id")
    private UUID profileId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "profile_img_name")
    private String profileImgName;
    @Column(name = "profile_img_path")
    private String profileImgPath;

    @Lob
    private String introduce;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_gender")
    private MemberGender memberGender;

    public void setProfileImage(byte[] profileImage) {

    }

    public Profile(Member member, String profileImgName, String profileImgPath, String introduce,
                   MemberGender memberGender) {
        this.member = member;
        this.profileImgName = profileImgName;
        this.profileImgPath = profileImgPath;
        this.introduce = introduce;
        this.memberGender = memberGender;
    }

    // 최초 회원가입 시, 기본적으로 만들어주는 프로필. Profile 과 Member 는 강한 종속성을 띱니다.
    public static Profile createMemberProfile(Member member) {
        return new Profile(member, "자기 소개를 수정해주세요. ", null, null, null);
    }
}
