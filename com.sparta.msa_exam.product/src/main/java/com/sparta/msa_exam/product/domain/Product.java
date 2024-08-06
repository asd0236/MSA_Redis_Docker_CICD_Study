package com.sparta.msa_exam.product.domain;

import com.sparta.msa_exam.product.domain.dto.ProductRequestDto;
import com.sparta.msa_exam.product.domain.dto.ProductResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private Integer supplyPrice;

    public Product toEntity(ProductRequestDto productRequestDto) {
        return Product.builder()
                .name(productRequestDto.getName())
                .supplyPrice(productRequestDto.getSupplyPrice())
                .build();
    }

    public ProductResponseDto toProductResponseDto() {
        return ProductResponseDto.builder()
                .productId(this.productId)
                .name(this.name)
                .supplyPrice(this.supplyPrice)
                .build();
    }
}
