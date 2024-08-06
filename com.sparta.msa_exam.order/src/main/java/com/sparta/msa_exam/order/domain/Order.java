package com.sparta.msa_exam.order.domain;

import com.sparta.msa_exam.order.domain.dto.OrderRequestDto;
import com.sparta.msa_exam.order.domain.dto.OrderResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String name;

    @OneToMany
    private List<OrderProduct> productIds = new ArrayList<>();

    public Order createOrder(OrderRequestDto orderRequestDto) {
        return Order.builder()
                .name(orderRequestDto.getName())
                .build();
    }

    public OrderResponseDto toResponseDto() {

        List<Long> productIds = new ArrayList<>();
        for(OrderProduct product: this.getProductIds()) {
            productIds.add(product.getId());
        }

        return OrderResponseDto.builder()
                .orderId(this.orderId)
                .productIds(productIds)
                .build();
    }

    public void addOrderProduct(OrderProduct orderProduct) {
        this.productIds.add(orderProduct);
    }

}
