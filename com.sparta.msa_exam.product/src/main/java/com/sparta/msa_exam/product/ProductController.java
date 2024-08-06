package com.sparta.msa_exam.product;

import com.sparta.msa_exam.product.domain.dto.ProductRequestDto;
import com.sparta.msa_exam.product.domain.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto productResponseDto = productService.saveProduct(productRequestDto);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Port", serverPort);

        return new ResponseEntity<>(productResponseDto, headers, HttpStatus.OK);
    }

//    @GetMapping
//    public List<ProductResponseDto> getAllProduct() {
//        return productService.findAll();
//    }

    @GetMapping
    public ResponseEntity<?> getAllProduct() {
        List<ProductResponseDto> productResponseDtos = productService.findAll();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Port", serverPort);

        return new ResponseEntity<>(productResponseDtos, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        ProductResponseDto productResponseDto = productService.findById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Port", serverPort);

        return new ResponseEntity<>(productResponseDto, headers, HttpStatus.OK);
    }
}
