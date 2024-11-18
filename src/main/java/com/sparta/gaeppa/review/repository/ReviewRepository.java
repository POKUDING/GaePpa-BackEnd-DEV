package com.sparta.gaeppa.review.repository;

import com.sparta.gaeppa.review.entity.Review;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findAllByStoreId(UUID storeId);

    boolean existsReviewByOrderId(UUID orderId);
}
