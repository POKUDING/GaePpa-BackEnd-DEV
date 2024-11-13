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

    @Column(name = "profile_img_name", nullable = false)
    private String profileImgName;
    @Column(name = "profile_img_path", nullable = false)
    private String profileImgPath;

    @Lob
    private String introduce;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_gender", nullable = false)
    private MemberGender memberGender;

    public void setProfileImage(byte[] profileImage) {

    }
}
