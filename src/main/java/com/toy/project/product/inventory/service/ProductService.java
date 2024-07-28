package com.toy.project.product.inventory.service;

import com.toy.project.product.config.exception.DataRetrievalException;
import com.toy.project.product.input.dto.ProductRequestDto;
import com.toy.project.product.inventory.dto.entity.Products;
import com.toy.project.product.inventory.repository.ProductRepository;
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
public class ProductService {

    private final ProductRepository productRepository;


    /**
     * 상품 단건 추가
     */
    public String addProductInfo(ProductRequestDto requestDto) {
        String resMsg = "OK";

        try {
            Products products = productRepository.retrieveProductUnit(requestDto.getProductId());
            ifExistdUnitThrowException(products);

            Products convertToEntity = Products.initByDto(requestDto);
            productRepository.saveProductUnit(convertToEntity);

        } catch (Exception e) {
            log.info(e.toString());
            throw e;
        }

        return resMsg;
    }

    /**
     * 상품 단건 수정
     */
    public String modifyProductInfo(ProductRequestDto requestDto) {
        String resMsg = "OK";

        try {
            Products products = productRepository.retrieveProductUnit(requestDto.getProductId());
            ifEmptyUnitThrowException(products);

            Products convertToEntity = Products.initByDto(requestDto);
            productRepository.saveProductUnit(convertToEntity);
        } catch (Exception e) {
            log.info(e.toString());
            throw e;
        }

        return resMsg;
    }

    /**
     * 상품 단건 삭제
     */
    public String deleteProductInfo(String productId) {
        String resMsg = "OK";

        try {
            Products products = productRepository.retrieveProductUnit(productId);
            ifEmptyUnitThrowException(products);

            productRepository.deleteProductUnit(productId);
        } catch (Exception e) {
            log.info(e.toString());
            throw e;
        }

        return resMsg;
    }

    /**
     * 상품 복수건 추가
     */
    public String addProductInfoList(List<ProductRequestDto> requestDtoList) {
        String resMsg = "OK";

        try {
            List<String> existProductIds = hasProductsInDatabase(requestDtoList);
            ifExistListThrowException(existProductIds);

            List<Products> convertToEntityList = Products.initByDtoList(requestDtoList);

            productRepository.addProductInfoList(convertToEntityList);
        } catch (Exception e) {
            log.info(e.toString());
            throw e;
        }

        return resMsg;
    }

    /**
     * 상품 복수건 수정
     */
    public String modifyProductInfoList(List<ProductRequestDto> requestDtoList) {
        String resMsg = "OK";

        try {
            List<String> existProductIds = hasProductsInDatabase(requestDtoList);
            ifComparedListSizeNotSameThrowException(requestDtoList, existProductIds);

            List<Products> convertToEntityList = Products.initByDtoList(requestDtoList);

            productRepository.addProductInfoList(convertToEntityList);
        } catch (Exception e) {
            log.info(e.toString());
            throw e;

        }

        return resMsg;
    }

    /**
     * 대분류 기준, 상품 리스트 조회
     */
    public ProductListResponseDto retrieveCategoryList(String catagory) {
        ProductListResponseDto listResponseDto = new ProductListResponseDto();

        try {
            List<Products> retrievedList = productRepository.retrieveProductListByCategory(catagory);
            ifEmptyListThrowException(retrievedList);

            listResponseDto.setResponseFromProductsList(retrievedList);
        } catch (Exception e) {
            log.info(e.toString());
            throw e;
        }

        return listResponseDto;
    }

    /**
     * 소분류 기준, 상품 리스트 조회
     */
    public ProductListResponseDto retrieveSubCategoryList(String category, String subCategory) {
        ProductListResponseDto listResponseDto = new ProductListResponseDto();

        try {
            List<Products> retrievedList = productRepository.retrieveProductListBySubCategory(category, subCategory);
            ifEmptyListThrowException(retrievedList);

            listResponseDto.setResponseFromProductsList(retrievedList);
        } catch (Exception e) {
            log.info(e.toString());
            throw e;
        }

        return listResponseDto;
    }

    /**
     * 상품번호 기준, 단건 조회
     */
    public ProductResponseDto retrieveProductUnit(String productId) {
        ProductResponseDto responseDto;

        try {
            Products retrievedDto = productRepository.retrieveProductUnit(productId);
            ifEmptyUnitThrowException(retrievedDto);

            responseDto = ProductResponseDto.toDto(retrievedDto);
        } catch (Exception e) {
            log.info(e.toString());
            throw e;
        }

        return responseDto;
    }

    /**
     * 상품번호 기준, 리스트 조회
     */
    public ProductListResponseDto retrieveProductList(List<String> productIdList) {
        ProductListResponseDto listResponseDto = new ProductListResponseDto();

        try {
            List<Products> retrievedList = productRepository.retrieveProductList(productIdList);
            ifEmptyListThrowException(retrievedList);

            listResponseDto.setResponseFromProductsList(retrievedList);
        } catch (Exception e) {
            log.info(e.toString());
            throw e;
        }

        return listResponseDto;
    }

    /**
     * DB에 기존재하는 상품번호 리스트 반환
     */
    private List<String> hasProductsInDatabase(List<ProductRequestDto> requestDtoList) {
        List<String> baseProductIdList = this.getProductIdList(requestDtoList);

        return productRepository.retrieveProductList(baseProductIdList).stream()
                .map(Products::getProductId)
                .collect(Collectors.toList());
    }

    private List<String> getProductIdList(List<ProductRequestDto> requestDtoList) {
        return requestDtoList.stream()
                .map(ProductRequestDto::getProductId)
                .collect(Collectors.toList());
    }

    public void ifEmptyListThrowException(List<Products> productsList) {
        if (ObjectUtils.isEmpty(productsList)) {
            throw new DataRetrievalException("[FAIL] isEmpty List");
        }
    }

    public void ifEmptyUnitThrowException(Products products) {
        if(ObjectUtils.isEmpty(products)){
            throw new DataRetrievalException("[FAIL] isEmpty Unit");
        }
    }

    public void ifExistdUnitThrowException(Products products) {
        if(!ObjectUtils.isEmpty(products)){
            throw new DataRetrievalException("[FAIL] inValid Unit :: " + products.getProductId());
        }
    }

    public void ifExistListThrowException(List<String> productIdList) {
        if(!ObjectUtils.isEmpty(productIdList)){
            throw new RuntimeException("[FAIL] inValid List :: " + String.join(",", productIdList));
        }
    }

    public void ifComparedListSizeNotSameThrowException(List<ProductRequestDto> baseList, List<String> comparedList) {
        if (baseList.size() != comparedList.size()) {
            throw new RuntimeException("[FAIL] inValid List :: " + String.join(",", baseList.stream()
                    .map(ProductRequestDto::getProductId)
                    .filter(productId -> !comparedList.contains(productId))
                    .toList()));
        }
    }

}