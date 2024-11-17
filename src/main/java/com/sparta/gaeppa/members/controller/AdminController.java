package com.sparta.gaeppa.members.controller;

import static com.sparta.gaeppa.global.util.ApiResponseUtil.success;

import com.sparta.gaeppa.global.util.ApiResponseUtil.ApiResult;
import com.sparta.gaeppa.members.dto.admin.MemberRoleRequestDto;
import com.sparta.gaeppa.members.dto.join.JoinResponseDto;
import com.sparta.gaeppa.members.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/admins")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final MemberService memberService;

    /**
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PutMapping("/role")
    public ResponseEntity<ApiResult<JoinResponseDto>> joinGeneralMember(@RequestBody @Valid MemberRoleRequestDto request)
            throws Exception {

//        JoinResponseDto joinResponseDto = memberService.joinMemberByEmailAuthentication(request);

//        return new ResponseEntity<>(success(joinResponseDto), HttpStatus.CREATED);
        return null;
    }
}
