package com.sparta.gaeppa.store.service;

import com.sparta.gaeppa.global.base.AuditorAwareImpl;
import com.sparta.gaeppa.global.exception.ExceptionStatus;
import com.sparta.gaeppa.global.exception.ServiceException;
import com.sparta.gaeppa.members.entity.Member;
import com.sparta.gaeppa.members.repository.MemberRepository;
import com.sparta.gaeppa.store.dto.StoreCreateRequestDto;
import com.sparta.gaeppa.store.dto.StoreUpdateRequestDto;
import com.sparta.gaeppa.store.entity.Store;
import com.sparta.gaeppa.store.entity.StoreCategory;
import com.sparta.gaeppa.store.repository.StoreRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {
    MemberRepository memberRepository;
    StoreRepository storeRepository;
    StoreCategoryService storeCategoryService;
    AuditorAwareImpl auditorAware;

    @Transactional
    public Store createStore(StoreCreateRequestDto storeCreateRequestDto) {
        // AuditorAware를 통해 userId 조회
        UUID userId = auditorAware.getCurrentUserId()
                .orElseThrow(() -> new ServiceException(ExceptionStatus.AUDITOR_NOT_FOUND));

        // 회원 조회
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ExceptionStatus.MEMBER_NOT_FOUND));

        // 카테고리 생성 또는 예외 발생
        StoreCategory storeCategory = storeCategoryService.createIfNotExists(
                storeCreateRequestDto.getStoreCategoryName()
        );

        // Store 엔티티 생성 및 저장
        Store store = storeCreateRequestDto.toStoreEntity(storeCategory, member);
        return storeRepository.save(store);
    }

    @Transactional
    public Store updateStore(StoreUpdateRequestDto storeUpdateRequestDto, UUID storeId) {
        // 기존 Store 엔티티 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ServiceException(ExceptionStatus.STORE_NOT_FOUND));

        // 스토어 카테고리 조회 또는 생성
        StoreCategory storeCategory = storeCategoryService.findStoreCategoryByName(storeUpdateRequestDto.getStoreCategoryName());


        // Store 엔티티 수정
        store.updateStore(
                storeUpdateRequestDto.getStoreName(),
                storeUpdateRequestDto.getStoreAddress(),
                storeUpdateRequestDto.getStoreTelephone(),
                storeUpdateRequestDto.getStoreIntroduce(),
                storeUpdateRequestDto.getBusinessTime(),
                storeCategory
        );

        // 저장 및 반환
        return storeRepository.save(store);
    }

    @Transactional(readOnly = true)
    public Store getMyStore(UUID storeId) {

        return storeRepository.findById(storeId)
                .orElseThrow(() -> new ServiceException(ExceptionStatus.STORE_NOT_FOUND));
    }

}
