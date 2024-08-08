package com.sparta.msa_exam.order.domain.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto implements Serializable {

    private Long orderId;
    private List<Long> productIds;

}
