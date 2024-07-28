package com.toy.project.product.inventory.service;

import com.toy.project.product.config.exception.DataRetrievalException;
import com.toy.project.product.input.dto.DecreaseInventoryRequestDto;
import com.toy.project.product.input.dto.InventoryRequestDto;
import com.toy.project.product.input.dto.ProductRequestDto;
import com.toy.project.product.inventory.dto.CustomProductInventory;
import com.toy.project.product.inventory.dto.entity.ProductInventory;
import com.toy.project.product.inventory.dto.entity.Products;
import com.toy.project.product.inventory.repository.InventoryRepository;
import com.toy.project.product.inventory.repository.ProductRepository;
import com.toy.project.product.output.dto.InventoryListResponseDto;
import com.toy.project.product.output.dto.InventoryResponseDto;
import com.toy.project.product.output.dto.ProductListResponseDto;
import com.toy.project.product.output.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;



    /** 상품 재고 추가 */
    public String addInventoryInfo(InventoryRequestDto inventoryRequestDto) {
        String resMsg = "OK";

        try {
            Products products = productRepository.retrieveProductUnit(inventoryRequestDto.getProductId());
            productService.ifEmptyUnitThrowException(products);

            ProductInventory convertToEntity = ProductInventory.initByDto(inventoryRequestDto);
            inventoryRepository.saveInventoryUnit(convertToEntity);
        } catch (Exception e) {
            log.info(e.toString());
            throw e;
        }

        return resMsg;
    }

    /**  상품 재고 수정 */
    public String modifyInventoryInfo(InventoryRequestDto requestDto) {
        String resMsg = "OK";

        try {
            CustomProductInventory retrievedDto = inventoryRepository.retrieveInventoryUnit(requestDto.getProductId());
            ifEmptyUnitThrowException(retrievedDto);

            ProductInventory convertToEntity = ProductInventory.initByDto(requestDto);
            inventoryRepository.saveInventoryUnit(convertToEntity);
        } catch (Exception e) {
            log.info(e.toString());
            throw e;
        }

        return resMsg;
    }

    /** 상품 재고 수정 */
    public String deleteInventoryInfo(String productId) {
        String resMsg = "OK";

        try {
            CustomProductInventory retrievedDto = inventoryRepository.retrieveInventoryUnit(productId);
            ifEmptyUnitThrowException(retrievedDto);

            inventoryRepository.deleteInventoryUnit(productId);
        } catch (Exception e) {
            log.info(e.toString());
            throw e;
        }

        return resMsg;
    }

    /** 상품 재고 차감 */
    public String decreaseInventory(DecreaseInventoryRequestDto decreaseInventoryRequestDto) {
        String resMsg = "OK";

        try {
            CustomProductInventory retrievedDto = inventoryRepository.retrieveInventoryUnit(decreaseInventoryRequestDto.getProductId());
            ifEmptyUnitThrowException(retrievedDto);
            ifUnableQuantityThrowException(retrievedDto, decreaseInventoryRequestDto);

            ProductInventory convertToEntity = ProductInventory.initDecreaseByDto(retrievedDto, decreaseInventoryRequestDto);
            inventoryRepository.saveInventoryUnit(convertToEntity);
        } catch (Exception e) {
            log.info(e.toString());
            throw e;
        }

        return resMsg;
    }

    /** 상품번호 기준, 단건 조회 */
    public InventoryResponseDto retrieveInventoryUnit(String productId) {
        InventoryResponseDto responseDto;

        try {
            CustomProductInventory retrievedDto = inventoryRepository.retrieveInventoryUnit(productId);
            ifEmptyUnitThrowException(retrievedDto);

            responseDto = InventoryResponseDto.toDto(retrievedDto);
        } catch (Exception e) {
            log.info(e.toString());
            throw e;
        }

        return responseDto;
    }

    /** 상품번호 리스트 기준, 복수건 조회 */
    public InventoryListResponseDto retrieveInventoryList(List<String> productIdList) {
        InventoryListResponseDto listResponseDto = new InventoryListResponseDto();

        try {
            List<CustomProductInventory> retrievedList = inventoryRepository.retrieveInventoryList(productIdList);
            ifEmptyListThrowException(retrievedList);

            listResponseDto.setResponseFromInventoryList(retrievedList);
        } catch (Exception e) {
            log.info(e.toString());
            throw e;
        }

        return listResponseDto;
    }

    private void ifEmptyListThrowException(List<CustomProductInventory> customProductInventoryList) {
        if(ObjectUtils.isEmpty(customProductInventoryList)){
            throw new DataRetrievalException("[FAIL] isEmpty List");
        }
    }

    private void ifEmptyUnitThrowException(CustomProductInventory customProductInventory) {
        if(ObjectUtils.isEmpty(customProductInventory)){
            throw new DataRetrievalException("[FAIL] isEmpty Unit");
        }
    }

    private void ifUnableQuantityThrowException(CustomProductInventory customProductInventory, DecreaseInventoryRequestDto decreaseInventoryRequestDto) {
        if(!ObjectUtils.isEmpty(customProductInventory)
                && customProductInventory.getQuantity() < decreaseInventoryRequestDto.getQuantity()){
            throw new DataRetrievalException("[FAIL] Unable Quantity");
        }
    }
}