package com.sparta.msa_exam.order;

import com.sparta.msa_exam.order.domain.Order;
import com.sparta.msa_exam.order.domain.OrderProduct;
import com.sparta.msa_exam.order.domain.dto.NewOrderRequestDto;
import com.sparta.msa_exam.order.domain.dto.OrderRequestDto;
import com.sparta.msa_exam.order.domain.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

//    public String getProductInfo(Long productId) {
//        return productClient.getProduct(productId);
//    }
//
//    public String getOrder(String orderId) {
//        if(orderId.equals("1")){
//            String productId = "2";
//            String productInfo = getProductInfo(productId);
//            return "Your order is " + orderId + " and " + productInfo;
//        }
//        return "Not exist order...";
//    }

    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.createOrder(orderRequestDto);

        return orderRepository.save(order).toResponseDto();
    }

    public OrderResponseDto addOrder(Long orderId, NewOrderRequestDto newOrderRequestDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        // FeignClient 이용해 상품 존재 여부 확인
        if(productClient.getProduct(newOrderRequestDto.getProductId()) == null){
            log.info("상품이 존재하지 않습니다.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
        }


        Long productId = newOrderRequestDto.getProductId();

        OrderProduct orderProduct = OrderProduct.builder()
                .order(order)
                .productId(productId)
                .build();
        orderProductRepository.save(orderProduct);

        order.addOrderProduct(orderProduct);

        return orderRepository.save(order).toResponseDto();
    }

    public OrderResponseDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        return order.toResponseDto();
    }
}
