package com.sparta.gaeppa.profile.controller;

import com.sparta.gaeppa.global.util.ApiResponseUtil;
import com.sparta.gaeppa.global.util.ApiResponseUtil.ApiResult;
import com.sparta.gaeppa.profile.entity.Profile;
import com.sparta.gaeppa.profile.repository.ProfileRepository;
import com.sparta.gaeppa.profile.service.ProfileService;
import com.sparta.gaeppa.store.entity.Favorite;
import com.sparta.gaeppa.store.repository.StoreRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;
    private final ProfileRepository profileRepository;
    private final StoreRepository storeRepository;

    /**
     * 멤버 ID로 프로필 조회
     * @param memberId 멤버 ID
     * @return 프로필 정보
     */
    @GetMapping("/member/{memberId}")
    public ResponseEntity<ApiResult<Profile>> getProfileByMemberId(@PathVariable UUID memberId) {
        Profile profile = profileService.getProfileByMemberId(memberId);
        return ResponseEntity.ok(ApiResponseUtil.success(profile));
    }

    /**
     * 프로필 소개 수정
     * @param profileId 프로필 ID
     * @param introduce 새로운 소개글
     * @return 수정된 프로필 정보
     */
    @PutMapping("/{profileId}/introduce")
    public ResponseEntity<ApiResult<Profile>> updateIntroduce(
            @PathVariable UUID profileId,
            @RequestBody String introduce) {
        Profile updatedProfile = profileService.updateProfileIntroduce(profileId, introduce);
        return ResponseEntity.ok(ApiResponseUtil.success(updatedProfile));
    }

    /**
     * 프로필 이미지 수정
     * @param profileId 프로필 ID
     * @param imgName 이미지 파일 이름
     * @param imgPath 이미지 파일 경로
     * @return 수정된 프로필 정보
     */
    @PutMapping("/{profileId}/image")
    public ResponseEntity<ApiResult<Profile>> updateProfileImage(
            @PathVariable UUID profileId,
            @RequestParam String imgName,
            @RequestParam String imgPath) {
        Profile updatedProfile = profileService.updateProfileImage(profileId, imgName, imgPath);
        return ResponseEntity.ok(ApiResponseUtil.success(updatedProfile));
    }

    /**
     * 프로필의 모든 즐겨찾기 조회
     * @param profileId 프로필 ID
     * @return 즐겨찾기 목록
     */
    @GetMapping("/{profileId}/favorites")
    public ResponseEntity<ApiResult<List<Favorite>>> getFavorites(@PathVariable UUID profileId) {
        List<Favorite> favorites = profileService.getFavoritesByProfile(profileId);
        return ResponseEntity.ok(ApiResponseUtil.success(favorites));
    }

    /**
     * 즐겨찾기 토글 API
     * @param profileId 프로필 ID
     * @param storeId 스토어 ID
     * @return 즐겨찾기 상태와 메시지
     */
    @PostMapping("/favorites/{storeId}")
    public ResponseEntity<ApiResult<String>> toggleFavorite(
            @RequestParam UUID profileId,
            @PathVariable UUID storeId) {
        // 서비스 계층에 토글 로직 위임
        String message = profileService.toggleFavorite(profileId, storeId);

        // 성공 응답 반환
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseUtil.success(message));
    }

}
