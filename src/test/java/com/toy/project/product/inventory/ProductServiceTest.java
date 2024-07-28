package com.toy.project.product.inventory;


import com.toy.project.product.config.exception.DataRetrievalException;
import com.toy.project.product.input.dto.ProductRequestDto;
import com.toy.project.product.inventory.dto.entity.Products;
import com.toy.project.product.inventory.repository.ProductRepository;
import com.toy.project.product.inventory.service.ProductService;
import com.toy.project.product.output.dto.ProductResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;


    @DisplayName("[예외] 상품등록::상품 기존재 TC")
    @Test
    public void testAddProductInfo_ProductAlreadyExists() throws Exception {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setProductId("12345");

        Products existingProduct = new Products();
        existingProduct.setProductId("12345");

        when(productRepository.retrieveProductUnit(anyString())).thenReturn(existingProduct);
        assertThrows(DataRetrievalException.class, () -> {
            productService.addProductInfo(requestDto);
        });

        verify(productRepository, times(1)).retrieveProductUnit(anyString());
        verify(productRepository, times(0)).saveProductUnit(any(Products.class));
    }

    @DisplayName("[성공] 상품등록::상품 미존재 TC")
    @Test
    public void testAddProductInfo_ProductAddedSuccessfully() throws Exception {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setProductId("12345");

        when(productRepository.retrieveProductUnit(anyString())).thenReturn(null);

        String resMsg = productService.addProductInfo(requestDto);

        assertEquals("OK", resMsg);
        verify(productRepository, times(1)).retrieveProductUnit(anyString());
        verify(productRepository, times(1)).saveProductUnit(any(Products.class));
    }

    @DisplayName("[예외] 상품수정::상품 미존재 TC")
    @Test
    public void testModifyProductInfo_ProductIsNotExists() throws Exception {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setProductId("12345");

        Products existingProduct = new Products();
        existingProduct.setProductId("12345");

        when(productRepository.retrieveProductUnit(anyString())).thenReturn(null);
        assertThrows(DataRetrievalException.class, () -> {
            productService.modifyProductInfo(requestDto);
        });

        verify(productRepository, times(1)).retrieveProductUnit(anyString());
        verify(productRepository, times(0)).saveProductUnit(any(Products.class));
    }

    @DisplayName("[성공] 상품수정::상품 기존재 TC")
    @Test
    public void testModifyProductInfo_ProductModifiedSuccessfully() throws Exception {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setProductId("12345");

        Products existingProduct = new Products();
        existingProduct.setProductId("12345");

        when(productRepository.retrieveProductUnit(anyString())).thenReturn(existingProduct);

        String resMsg = productService.modifyProductInfo(requestDto);

        assertEquals("OK", resMsg);
        verify(productRepository, times(1)).retrieveProductUnit(anyString());
        verify(productRepository, times(1)).saveProductUnit(any(Products.class));
    }

    @DisplayName("[예외] 상품삭제::상품 미존재 TC")
    @Test
    public void testDeleteProductInfo_ProductIsNotExists() throws Exception {
        String productId = "12345";

        Products existingProduct = new Products();
        existingProduct.setProductId("12345");

        when(productRepository.retrieveProductUnit(productId)).thenReturn(null);
        assertThrows(DataRetrievalException.class, () -> {
            productService.deleteProductInfo(productId);
        });

        verify(productRepository, times(1)).retrieveProductUnit(anyString());
        verify(productRepository, times(0)).deleteProductUnit(anyString());
    }

    @DisplayName("[성공] 상품삭제::상품 기존재 TC")
    @Test
    public void testDeleteProductInfo_ProductDeletedSuccessfully() throws Exception {
        String productId = "12345";

        Products existingProduct = new Products();
        existingProduct.setProductId("12345");

        when(productRepository.retrieveProductUnit(anyString())).thenReturn(existingProduct);

        String resMsg = productService.deleteProductInfo(productId);

        assertEquals("OK", resMsg);
        verify(productRepository, times(1)).retrieveProductUnit(anyString());
        verify(productRepository, times(1)).deleteProductUnit(anyString());
    }

    @DisplayName("[예외] 상품조회::상품 미존재 TC")
    @Test
    public void testRetrieveProductInfo_ProductIsNotExists() throws Exception {
        String productId = "12345";

        Products existingProduct = new Products();
        existingProduct.setProductId("12345");

        when(productRepository.retrieveProductUnit(productId)).thenReturn(null);
        assertThrows(DataRetrievalException.class, () -> {
            productService.retrieveProductUnit(productId);
        });

        verify(productRepository, times(1)).retrieveProductUnit(anyString());
    }

    @DisplayName("[성공] 상품조회::상품 기존재 TC")
    @Test
    public void testRetrieveProductInfo_ProductRetrievedSuccessfully() throws Exception {
        String productId = "12345";

        Products existingProduct = new Products();
        existingProduct.setProductId("12345");

        when(productRepository.retrieveProductUnit(productId)).thenReturn(existingProduct);

        ProductResponseDto responseDto = productService.retrieveProductUnit(productId);

        assertNotNull(responseDto);
        assertEquals(productId, existingProduct.getProductId());
        verify(productRepository, times(1)).retrieveProductUnit(anyString());
    }
}
