package com.sparta.msa_exam.order;

import com.sparta.msa_exam.order.domain.dto.NewOrderRequestDto;
import com.sparta.msa_exam.order.domain.dto.OrderRequestDto;
import com.sparta.msa_exam.order.domain.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Value("${server.port}")
    private String serverPort;


    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto orderResponseDto = orderService.createOrder(orderRequestDto);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Port", serverPort);

        return new ResponseEntity<>(orderResponseDto, headers, HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity addOrder(@PathVariable Long orderId, @RequestBody NewOrderRequestDto newOrderRequestDto) {
        OrderResponseDto orderResponseDto = orderService.addOrder(orderId, newOrderRequestDto);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Port", serverPort);

        return new ResponseEntity<>(orderResponseDto, headers, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity getOrder(@PathVariable Long orderId) {
        OrderResponseDto orderResponseDto = orderService.getOrder(orderId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Port", serverPort);

        return new ResponseEntity<>(orderResponseDto, headers, HttpStatus.OK);
    }

//    @GetMapping
//    public String getOrder() {
//        return "Order detail";
//    }
}
