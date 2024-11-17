package com.sparta.gaeppa.review.repository;

import com.sparta.gaeppa.review.entity.Review;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
