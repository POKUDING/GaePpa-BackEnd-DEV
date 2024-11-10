package com.sparta.gaeppa.product.entity;

import com.sparta.gaeppa.global.base.BaseEntity;
import com.sparta.gaeppa.product.dto.ProductOptionCategoryRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "p_product_option_categories")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class ProductOptionCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_option_category_id")
    private UUID id;

    @Column(name = "product_option_category_name", nullable = false)
    private String name;

    @Column(name = "product_option_max_limits", nullable = false)
    private int maxLimits;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "productOptionCategory", fetch = FetchType.LAZY)
    private List<ProductOption> productOptions;

    @Builder
    protected ProductOptionCategory(String name, int maxLimits) {
        this.name = name;
        this.maxLimits = maxLimits;
    }

    public void update(ProductOptionCategoryRequestDto requestDto) {
        this.name = requestDto.getProductOptionCategoryName();
        this.maxLimits = requestDto.getMaxLimits();
    }
}