package com.sparta.gaeppa.members.service;

import com.sparta.gaeppa.global.exception.ExceptionStatus;
import com.sparta.gaeppa.global.exception.ServiceException;
import com.sparta.gaeppa.members.dto.join.JoinGeneralMemberRequestDto;
import com.sparta.gaeppa.members.dto.join.JoinMasterMemberRequestDto;
import com.sparta.gaeppa.members.dto.join.JoinResponseDto;
import com.sparta.gaeppa.members.entity.LoginType;
import com.sparta.gaeppa.members.entity.Member;
import com.sparta.gaeppa.members.repository.MemberRepository;
import com.sparta.gaeppa.profile.entity.Profile;
import com.sparta.gaeppa.profile.repository.ProfileRepository;
import com.sparta.gaeppa.security.jwts.repository.RefreshRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepositoryV1;
    private final PasswordEncoder passwordEncoder;
    private final RefreshRepository refreshRepository;
    private final ProfileRepository profileRepository;
    private final MemberEmailService memberEmailService;
    private final MemberRepository memberRepository;

    @Transactional
    public JoinResponseDto joinMemberByEmailAuthentication(JoinGeneralMemberRequestDto joinGeneralMemberRequestDto) throws Exception {
        // 닉네임 유효성 검사
        if (joinGeneralMemberRequestDto.getUsername() == null) {
            throw new IllegalArgumentException("유저네임을 꼭 입력해야합니다.");
        }

        // 동일한 이메일이 존재하는지 유효성 검사
        validateExistedMemberByEmail(joinGeneralMemberRequestDto.getEmail());

        // 표준화된 128-bit의 고유 식별자 생성
        String emailToken = UUID.randomUUID().toString();

        // 회원 생성 및 저장
        Member joinMember = joinGeneralMemberRequestDto.toEntity(passwordEncoder);

        Member newJoinMember = memberRepositoryV1.save(joinMember);

        // 프로필 생성 및 저장
        Profile profile = Profile.createMemberProfile(newJoinMember);
        profileRepository.save(profile);

        // 이메일 인증 메일 전송
        sendVerificationEmail(newJoinMember.getEmail(), emailToken);

        return JoinResponseDto.from(newJoinMember);
    }

    @Transactional
    public JoinResponseDto joinMemberNoEmailAuthentication(JoinMasterMemberRequestDto joinMasterMemberRequestDto) throws Exception {
        // 닉네임 유효성 검사
        if (joinMasterMemberRequestDto.getUsername() == null) {
            throw new IllegalArgumentException("유저네임을 꼭 입력해야합니다.");
        }

        // 동일한 이메일이 존재하는지 유효성 검사
        validateExistedMemberByEmail(joinMasterMemberRequestDto.getEmail());

        // email Token 을 toEntity 내에서 알아서 설정해주고 저장. 이메일 인증 기능 생략
        // 회원 생성 및 저장
        Member joinMember = joinMasterMemberRequestDto.toEntity(passwordEncoder);
        joinMember.certifyEmail();
        log.debug("MemberService : member 의 이메일 인증 상태를 확인 합니다 : {}", joinMember.isCertifyByMail());

        memberRepositoryV1.save(joinMember);

        // 프로필 생성 및 저장
        Profile profile = Profile.createMemberProfile(joinMember);
        profileRepository.save(profile);

        return JoinResponseDto.from(joinMember);
    }

    @Transactional
    public Member memberLogin(String email) {
        List<Member> generalMembersByEmail = findGeneralMembersByEmail(email);

        // 일반 회원 계정이 1명이 아닌 경우 예외 처리
        if (generalMembersByEmail.size() != 1) {
            throw new DuplicateMemberException();
        }

        // 1명일 때만 비밀번호 체크 진행
        Member member = generalMembersByEmail.getFirst();
        member.setLastLoginDate(LocalDateTime.now());
        memberRepositoryV1.save(member);
        return member;
    }

    @Transactional(readOnly = true)
    public Member authenticateMember(String email, String password) {
        Member generalMember = findSingleGeneralMemberByEmail(email);

        if (!generalMember.isCertifyByMail()) {
            throw new BadCredentialsException(ExceptionStatus.AUTHENTICATION_EMAIL_NOT_VERIFIED.getMessage());
        }

        if (!passwordEncoder.matches(password, generalMember.getPassword())) {
            throw new BadCredentialsException(ExceptionStatus.AUTHENTICATION_INVALID_PASSWORD.getMessage());
        }

        return generalMember;
    }


    @Transactional(readOnly = true)
    public boolean checkPassword(String email, String password) {
        List<Member> generalMembersByEmail = findGeneralMembersByEmail(email);

        // 일반 회원 계정이 1명이 아닌 경우 예외 처리
        if (generalMembersByEmail.size() != 1) {
            throw new DuplicateMemberException();
        }

        // 1명일 때만 비밀번호 체크 진행
        Member member = generalMembersByEmail.getFirst();
        return passwordEncoder.matches(password, member.getPassword());
    }

    @Transactional(readOnly = true)
    public void validateExistedMemberByEmail(String email) {
        if (memberRepositoryV1.existsByEmail(email)) {
            throw new ExistingMemberException();
        }
    }

    // 이메일 인증 보내는 서비스는 DB 을 수정하거나 생성하는 트랜젝션이 필요 없어서 readOnly 를 걸었습니다.
    @Transactional(readOnly = true)
    public void sendVerificationEmail(String email, String token) throws Exception {
        memberEmailService.sendEmailVerification(email, token);
    }

    @Transactional(readOnly = true)
    public List<Member> findMembersByEmail(String email) {

        return memberRepository.findAllByEmail(email);
    }


    @Transactional(readOnly = true)
    public List<Member> findGeneralMembersByEmail(String email) {
        // 이메일로 모든 회원을 조회한 후, LoginType이 GENERAL인 회원만 필터링하여 반환
        return memberRepository.findAllByEmail(email)
                .stream()
                .filter(member -> member.getLoginType() == LoginType.GENERAL)
                .collect(Collectors.toList());
    }

    public Member findSingleGeneralMemberByEmail(String email) {
        List<Member> generalMembers = findGeneralMembersByEmail(email);

        if (generalMembers.isEmpty()) {
            throw new EmailNotFoundException();
        } else if (generalMembers.size() > 1) {
            throw new DuplicateMemberException();
        }

        return generalMembers.getFirst();
    }

    // 사용자 정의 예외 클래스
    public static class ExistingMemberException extends ServiceException {
        public ExistingMemberException() {
            super(ExceptionStatus.EXISTING_MEMBER_EMAIL);
        }
    }
    public static class EmailNotFoundException extends ServiceException {
        public EmailNotFoundException() {
            super(ExceptionStatus.AUTHENTICATION_NOT_FOUND_EMAIL);
        }
    }
    public static class DuplicateMemberException extends ServiceException {
        public DuplicateMemberException() {
            super(ExceptionStatus.DUPLICATE_MEMBER);
        }
    }
}
