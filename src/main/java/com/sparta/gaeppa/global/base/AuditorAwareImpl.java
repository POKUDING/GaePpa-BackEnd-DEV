package com.sparta.gaeppa.global.base;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            //TODO: User 객체의 변경에 따라 수정 필요
            User user = (User) authentication.getPrincipal();
            return Optional.of(user.getUsername());  // username을 반환
        }
        return Optional.empty();  // 사용자 정보를 가져올 수 없을 경우 기본값
    }
}