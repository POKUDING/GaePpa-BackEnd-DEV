package com.sparta.gaeppa.members.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_members", uniqueConstraints = {
        @UniqueConstraint(columnNames = "provider_user_id")
})
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID memberId;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Column(nullable = false)
    private String username;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private MemberRole role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "login_type", nullable = false)
    private LoginType loginType;

    @Column(name = "provider_user_id", unique = true, nullable = false)
    @NotBlank
    private String providerUserId; // Provider + provider Id 형식

    @Column(name = "email_token")
    private String emailToken;

    @Column(name = "is_certified_email", nullable = false)
    private boolean isCertifyByMail = false;

    @Column(nullable = false)
    private boolean isActivated = false;

    // 계정 활성화 메서드
    public void activateAccount() {
        this.isActivated = true;
    }

    // 이메일 인증 메서드
    public void certifyEmail() {
        this.isCertifyByMail = true;
    }
}
