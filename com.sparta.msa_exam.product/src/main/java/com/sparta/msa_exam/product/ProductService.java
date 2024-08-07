package com.sparta.msa_exam.product;

import com.sparta.msa_exam.product.domain.Product;
import com.sparta.msa_exam.product.domain.dto.ProductRequestDto;
import com.sparta.msa_exam.product.domain.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponseDto> findAll() {
        List<Product> Products =  productRepository.findAll();

        List<ProductResponseDto> ProductResponseDtos = new ArrayList<>();
        for (Product product : Products) {
            ProductResponseDtos.add(product.toProductResponseDto());
        }

        return ProductResponseDtos;
    }

    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
        Product product = new Product().toEntity(productRequestDto);

        return productRepository.save(product).toProductResponseDto();
    }

    public ProductResponseDto findById(Long id) {
        return productRepository.findById(id)
                .map(Product::toProductResponseDto)
                .orElse(null); // 존재하지 않는 상품 조회 시 null 반환
    }
}
