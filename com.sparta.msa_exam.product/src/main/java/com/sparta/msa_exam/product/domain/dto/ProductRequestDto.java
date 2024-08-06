package com.sparta.msa_exam.product.domain.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    private String name;
    private Integer supplyPrice;

}
