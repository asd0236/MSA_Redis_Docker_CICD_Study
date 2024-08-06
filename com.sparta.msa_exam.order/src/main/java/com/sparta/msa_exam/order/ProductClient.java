package com.sparta.msa_exam.order;

import com.sparta.msa_exam.order.domain.dto.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 실제 Product 클라이언트 애플리케이션 호출할 수 있는  코드
@FeignClient(name="product-service")
public interface ProductClient {
    @GetMapping("/products/{id}")
    ProductResponseDto getProduct(@PathVariable("id") Long id);
}
