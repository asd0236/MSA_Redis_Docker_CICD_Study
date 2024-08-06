package com.sparta.msa_exam.order;

import com.sparta.msa_exam.order.domain.dto.NewOrderRequestDto;
import com.sparta.msa_exam.order.domain.dto.OrderRequestDto;
import com.sparta.msa_exam.order.domain.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }

    @PutMapping("/{orderId}")
    public OrderResponseDto addOrder(@PathVariable Long orderId, @RequestBody NewOrderRequestDto newOrderRequestDto) {
        return orderService.addOrder(orderId, newOrderRequestDto);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDto getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping
    public String getOrder() {
        return "Order detail";
    }
}
