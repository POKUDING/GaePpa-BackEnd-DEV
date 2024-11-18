package com.sparta.gaeppa.members.controller;

import static com.sparta.gaeppa.global.util.ApiResponseUtil.success;

import com.sparta.gaeppa.global.util.ApiResponseUtil.ApiResult;
import com.sparta.gaeppa.members.dto.join.JoinGeneralMemberRequestDto;
import com.sparta.gaeppa.members.dto.join.JoinMasterMemberRequestDto;
import com.sparta.gaeppa.members.dto.join.JoinResponseDto;
import com.sparta.gaeppa.members.entity.Member;
import com.sparta.gaeppa.members.service.MemberEmailService;
import com.sparta.gaeppa.members.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberEmailService memberEmailService;

    /**
     * 커스토머 회원에 대한 회원가입 진행. (default) MemberRole = CUSTOMER, SocialType = SocialType.GENERAL,
     * 이메일 인증 로직 추가.
     * @param request email, password, username(닉네임과 같은 역할)
     * @return email
     */
    @PostMapping("/join")
    public ResponseEntity<ApiResult<JoinResponseDto>> joinGeneralMember(@RequestBody @Valid JoinGeneralMemberRequestDto request)
            throws Exception {

        JoinResponseDto joinResponseDto = memberService.joinMemberByEmailAuthentication(request);

        return new ResponseEntity<>(success(joinResponseDto), HttpStatus.CREATED);
    }

    /**
     * MASTER 회원에 대한 회원가입 진행. (default) MemberRole = MASTER, SocialType = SocialType.GENERAL,
     * 이메일 인증 로직 생략.
     * @param request email, password, username(닉네임과 같은 역할)
     * @return email
     */
    @PostMapping("/master/join")
    public ResponseEntity<ApiResult<JoinResponseDto>> joinMasterMember(@RequestBody @Valid JoinMasterMemberRequestDto request)
            throws Exception {

        JoinResponseDto joinResponseDto = memberService.joinMemberNoEmailAuthentication(request);

        return new ResponseEntity<>(success(joinResponseDto), HttpStatus.CREATED);
    }

    @GetMapping("/auth/verify")
    public ResponseEntity<ApiResult<String>> verifyEmailWhenMemberJoin(@RequestParam("token") String token) {
        // queryParameter 로 전해진 token 값에 대한 유효성검사 및 인증과정 진행.
        Member member = memberEmailService.updateByVerifyToken(token);
        if (member == null){
            return new ResponseEntity<>(success("유효하지 않은 토큰입니다."), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(success("이메일 인증이 성공적으로 완료되었습니다."), HttpStatus.CREATED);
    }

}
