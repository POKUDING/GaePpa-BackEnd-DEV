package com.sparta.gaeppa.profile.service;

import com.sparta.gaeppa.global.exception.ExceptionStatus;
import com.sparta.gaeppa.global.exception.ServiceException;
import com.sparta.gaeppa.profile.entity.Profile;
import com.sparta.gaeppa.profile.repository.ProfileRepository;
import com.sparta.gaeppa.store.entity.Favorite;
import com.sparta.gaeppa.store.entity.Store;
import com.sparta.gaeppa.store.repository.FavoriteRepository;
import com.sparta.gaeppa.store.repository.StoreRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final FavoriteRepository favoriteRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Profile updateProfileIntroduce(UUID profileId, String introduce) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ServiceException((ExceptionStatus.PROFILE_NOT_FOUND)));
        profile.updateIntroduce(introduce);
        return profileRepository.save(profile);
    }

    @Transactional
    public Profile updateProfileImage(UUID profileId, String imgName, String imgPath) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ServiceException((ExceptionStatus.PROFILE_NOT_FOUND)));
        profile.setProfileImage(imgName, imgPath);
        return profileRepository.save(profile);
    }

    @Transactional(readOnly = true)
    public Profile getProfileByMemberId(UUID memberId) {
        return profileRepository.findByMemberMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found for memberId: " + memberId));
    }

    @Transactional
    public String toggleFavorite(UUID profileId, UUID storeId) {
        // 프로필 조회
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ServiceException(ExceptionStatus.PROFILE_NOT_FOUND));
        // 스토어 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ServiceException(ExceptionStatus.STORE_NOT_FOUND));

        // 프로필,스토어 ->  즐겨찾기 조회
        Favorite favorite = favoriteRepository.findByProfileAndStore(profile, store)
                .orElseThrow(() -> new ServiceException(ExceptionStatus.FAVORITE_NOT_FOUND));

        if (favorite != null) {
            favoriteRepository.delete(favorite);
            return "Store removed from favorites.";
        } else {
            Favorite newFavorite = Favorite.builder()
                    .profile(profile)
                    .store(store)
                    .build();
            favoriteRepository.save(newFavorite);
            return "Store added to favorites.";
        }
    }

    @Transactional(readOnly = true)
    public List<Favorite> getFavoritesByProfile(UUID profileId) {
        return favoriteRepository.findAllByProfileProfileId(profileId);
    }

}
