package com.sparta.gaeppa.store.entity;

import com.sparta.gaeppa.global.base.BaseEntity;
import com.sparta.gaeppa.members.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "store_id")
    private UUID storeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_category_id")
    private StoreCategory category;

    @Column
    @NotBlank
    @NotNull
    private String storeName;

    @Column(nullable = false)
    @NotBlank
    private String storeAddress;

    @Column(nullable = false)
    @Min(10L)
    @Max(11L)
    private String storeTelephone;

    @Column(nullable = true)
    private String storeIntroduce;

    @Column(nullable = false)
    private boolean icActive = true;

    @Positive
    private BigDecimal reviewAvg = BigDecimal.valueOf(0.0);

    @Positive
    private int reviewCount = 0;

    private String businessTime;

    // 소수점 1자리까지 반올림하는 메서드
    public void setReviewAvg(double reviewAvg) {
        this.reviewAvg = BigDecimal.valueOf(reviewAvg).setScale(1, RoundingMode.HALF_UP);
    }
}
