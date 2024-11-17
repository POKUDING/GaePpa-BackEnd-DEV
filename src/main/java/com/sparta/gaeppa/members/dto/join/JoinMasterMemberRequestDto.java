package com.sparta.gaeppa.members.dto.join;

import com.sparta.gaeppa.members.entity.LoginType;
import com.sparta.gaeppa.members.entity.Member;
import com.sparta.gaeppa.members.entity.MemberRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class JoinMasterMemberRequestDto {

    @Email
    private String email;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$", message = "Password must contain at least one digit and one special character")
    private String password;
    // 표준화된 128-bit의 고유 식별자 생성
    private String emailToken = UUID.randomUUID().toString();

    private boolean isCertifyByMail = true;

    // Generate a UUID for provider_user_id
    String uuid = UUID.randomUUID().toString().replace("-", "");
    String providerUserId = "GENERAL-" + uuid.substring(0, 12);

    // 엔티티로 변환 메서드 (PasswordEncoder 주입)
    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .username(username)
                .password(passwordEncoder.encode(password)) // 암호화 처리
                .emailToken(emailToken)
                .isCertifyByMail(true)
                .providerUserId(providerUserId)
                .loginType(LoginType.GENERAL)
                .role(MemberRole.MASTER)
                .build();
    }
}
