package com.sparta.msa_exam.product.domain.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto implements Serializable {

    private Long productId;
    private String name;
    private Integer supplyPrice;

}
