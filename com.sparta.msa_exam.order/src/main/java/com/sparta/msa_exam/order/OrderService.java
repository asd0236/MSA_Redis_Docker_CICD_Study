package com.sparta.msa_exam.order;

import com.sparta.msa_exam.order.domain.Order;
import com.sparta.msa_exam.order.domain.OrderProduct;
import com.sparta.msa_exam.order.domain.dto.NewOrderRequestDto;
import com.sparta.msa_exam.order.domain.dto.OrderRequestDto;
import com.sparta.msa_exam.order.domain.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductClient productClient;
    private final OrderRepository orderRepository;

//    public String getProductInfo(String productId) {
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

        Long productId = newOrderRequestDto.getProductId();

        OrderProduct orderProduct = OrderProduct.builder()
                .id(orderId)
                .order(order)
                .productId(productId)
                .build();

        order.addOrderProduct(orderProduct);

//        Product product = new Product();
//        product.toEntity();
//        productClient.getProduct(newOrderRequestDto.getProductId());

        return orderRepository.save(order).toResponseDto();
    }

    public OrderResponseDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        return order.toResponseDto();
    }
}
