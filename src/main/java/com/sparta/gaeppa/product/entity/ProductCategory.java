package com.sparta.gaeppa.product.entity;

import com.sparta.gaeppa.global.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_product_categories")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class ProductCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_category_id")
    private UUID id;

    @Column(name = "product_category_name")
    private String name;

    //TODO: 스토어와 합칠때는 삭제후 주석 코드 사용
    @Column(name = "store_id")
    private UUID storeId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "store_id", nullable = false)
//    private Store store;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products;

    @Builder
    protected ProductCategory(String name, UUID storeId) {
        this.name = name;
        this.storeId = storeId;
    }

    public void update(String name) {
        this.name = name;
    }
}