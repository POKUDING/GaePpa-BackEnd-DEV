package com.sparta.gaeppa.store.repository;

import com.sparta.gaeppa.store.entity.Store;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID> {
    Store findByMemberMemberId(UUID memberId);

    Store findByStoreName(String storeName);
}
