package com.sparta.gaeppa.review.entity;

import com.sparta.gaeppa.global.base.BaseEntity;
import com.sparta.gaeppa.order.entity.Images;
import com.sparta.gaeppa.order.entity.Orders;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_reviews")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reviews extends BaseEntity {

    @Id
    @Column(name = "review_id")
    @GeneratedValue
    private UUID reviewId;

    @OneToOne
    @JoinColumn(nullable = false, name = "order_id")
    private Orders order;

    @Embedded
    private Images image;

    @Column(nullable = false, name = "review_content")
    private String reviewContent;

    @Column(nullable = false, name = "review_score")
    private int reviewScore;

}
