package com.toy.project.product.inventory;


import com.toy.project.product.config.exception.DataRetrievalException;
import com.toy.project.product.input.dto.InventoryRequestDto;
import com.toy.project.product.inventory.dto.CustomProductInventory;
import com.toy.project.product.inventory.dto.entity.ProductInventory;
import com.toy.project.product.inventory.dto.entity.Products;
import com.toy.project.product.inventory.repository.InventoryRepository;
import com.toy.project.product.inventory.repository.ProductRepository;
import com.toy.project.product.inventory.service.InventoryService;
import com.toy.project.product.inventory.service.ProductService;
import com.toy.project.product.output.dto.InventoryResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    @InjectMocks
    private InventoryService inventoryService;

    @Mock
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private InventoryRepository inventoryRepository;


    @DisplayName("[예외] 재고등록::상품 미존재 TC")
    @Test
    public void testAddInventoryInfo_ProductIsNotExists() throws Exception {
        String productId = "12345";

        InventoryRequestDto requestDto = new InventoryRequestDto();
        requestDto.setProductId("12345");

        when(productRepository.retrieveProductUnit(productId)).thenReturn(null);
        doThrow(new DataRetrievalException("product not found")).when(productService).ifEmptyUnitThrowException(null);
        assertThrows(DataRetrievalException.class, () -> {
            inventoryService.addInventoryInfo(requestDto);
        });

        verify(productRepository, times(1)).retrieveProductUnit(productId);
        verify(inventoryRepository, times(0)).saveInventoryUnit(any(ProductInventory.class));
    }


    @DisplayName("[성공] 재고등록::상품 기존재 TC")
    @Test
    public void testAddInventoryInfo_InventoryAddedSuccessfully() throws Exception {
        String productId = "12345";

        InventoryRequestDto requestDto = new InventoryRequestDto();
        requestDto.setProductId("12345");

        when(productRepository.retrieveProductUnit(productId)).thenReturn(any(Products.class));

        String resMsg = inventoryService.addInventoryInfo(requestDto);

        assertEquals("OK", resMsg);
        verify(productRepository, times(1)).retrieveProductUnit(productId);
        verify(inventoryRepository, times(1)).saveInventoryUnit(any(ProductInventory.class));
    }

    @DisplayName("[예외] 재고수정::상품 미존재 TC")
    @Test
    public void testModifyInventoryInfo_InventoryIsNotExists() throws Exception {
        String productId = "12345";

        InventoryRequestDto requestDto = new InventoryRequestDto();
        requestDto.setProductId("12345");

        when(inventoryRepository.retrieveInventoryUnit(productId)).thenReturn(null);
        assertThrows(DataRetrievalException.class, () -> {
            inventoryService.modifyInventoryInfo(requestDto);
        });

        verify(inventoryRepository, times(1)).retrieveInventoryUnit(productId);
        verify(inventoryRepository, times(0)).saveInventoryUnit(any(ProductInventory.class));
    }


    @DisplayName("[성공] 재고수정::상품 기존재 TC")
    @Test
    public void testModifyInventoryInfo_InventoryModifiedSuccessfully() throws Exception {
        String productId = "12345";

        InventoryRequestDto requestDto = new InventoryRequestDto();
        requestDto.setProductId("12345");

        CustomProductInventory existedProductInventory = new CustomProductInventory(
                "12345","테스트",100);

        when(inventoryRepository.retrieveInventoryUnit(productId)).thenReturn(existedProductInventory);

        String resMsg = inventoryService.modifyInventoryInfo(requestDto);

        assertEquals("OK", resMsg);
        verify(inventoryRepository, times(1)).retrieveInventoryUnit(productId);
        verify(inventoryRepository, times(1)).saveInventoryUnit(any(ProductInventory.class));
    }

    @DisplayName("[예외] 재고삭제::상품 미존재 TC")
    @Test
    public void testDeleteInventoryInfo_InventoryIsNotExists() throws Exception {
        String productId = "12345";

        when(inventoryRepository.retrieveInventoryUnit(productId)).thenReturn(null);
        assertThrows(DataRetrievalException.class, () -> {
            inventoryService.deleteInventoryInfo(productId);
        });

        verify(inventoryRepository, times(1)).retrieveInventoryUnit(productId);
        verify(inventoryRepository, times(0)).deleteInventoryUnit(productId);
    }


    @DisplayName("[성공] 재고삭제::상품 기존재 TC")
    @Test
    public void testDeleteInventoryInfo_InventoryDeletedSuccessfully() throws Exception {
        String productId = "12345";

        CustomProductInventory existedProductInventory = new CustomProductInventory(
                "12345","테스트",100);

        when(inventoryRepository.retrieveInventoryUnit(productId)).thenReturn(existedProductInventory);

        String resMsg = inventoryService.deleteInventoryInfo(productId);

        assertEquals("OK", resMsg);
        verify(inventoryRepository, times(1)).retrieveInventoryUnit(productId);
        verify(inventoryRepository, times(1)).deleteInventoryUnit(productId);
    }

    @DisplayName("[예외] 재고조회::상품 미존재 TC")
    @Test
    public void testRetrieveInventoryInfo_InventoryIsNotExists() throws Exception {
        String productId = "12345";

        when(inventoryRepository.retrieveInventoryUnit(productId)).thenReturn(null);
        assertThrows(DataRetrievalException.class, () -> {
            inventoryService.retrieveInventoryUnit(productId);
        });

        verify(inventoryRepository, times(1)).retrieveInventoryUnit(productId);
    }


    @DisplayName("[성공] 재고삭제::상품 기존재 TC")
    @Test
    public void testRetrieveInventoryInfo_InventoryRetrievedSuccessfully() throws Exception {
        String productId = "12345";

        CustomProductInventory existedProductInventory = new CustomProductInventory(
                "12345","테스트",100);

        when(inventoryRepository.retrieveInventoryUnit(productId)).thenReturn(existedProductInventory);

        InventoryResponseDto resDto = inventoryService.retrieveInventoryUnit(productId);

        assertNotNull(resDto);
        assertEquals(productId, existedProductInventory.getProductId());
        verify(inventoryRepository, times(1)).retrieveInventoryUnit(productId);
    }
}
