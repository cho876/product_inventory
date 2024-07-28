package com.toy.project.product.input.controller;

import com.toy.project.product.input.dto.ProductRequestDto;
import com.toy.project.product.inventory.service.ProductService;
import com.toy.project.product.output.dto.ProductListResponseDto;
import com.toy.project.product.output.dto.ProductResponseDto;
import com.toy.project.product.output.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name="product", description = "상품 API")
public class ProductController {

    private final ProductService productService;


    @Operation(
            operationId = "addProduct",
            summary = "상품 단건 등록 API",
            description = "상품 단건 등록 API",
            tags = {"product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "등록 성공여부 반환", content = {

                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
                    })
            }
    )
    @PostMapping("/v1/unit")
    public ResponseDto<String> addProduct(@RequestBody ProductRequestDto productRequestDto) {
        ResponseDto<String> responseDto = new ResponseDto<>();

        try {
            responseDto.initSuccess(productService.addProductInfo(productRequestDto));
        } catch (Exception ex) {
            responseDto.initFail("FAIL", ex);
        }

        return responseDto;
    }

    @Operation(
            operationId = "modifyProduct",
            summary = "상품 단건 수정 API",
            description = "상품 단건 등록 API",
            tags = {"product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "수정 성공여부 반환", content = {

                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
                    })
            }
    )
    @PutMapping("/v1/unit")
    public ResponseDto<String> modifyProduct(@RequestBody ProductRequestDto productRequestDto) {
        ResponseDto<String> responseDto = new ResponseDto<>();

        try {
            responseDto.initSuccess(productService.modifyProductInfo(productRequestDto));
        } catch (Exception ex) {
            responseDto.initFail("FAIL", ex);
        }

        return responseDto;
    }

    @Operation(
            operationId = "deleteProduct",
            summary = "상품 단건 삭제 API",
            description = "상품 단건 삭제 API",
            tags = {"product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "삭제 성공여부 반환", content = {

                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
                    })
            }
    )
    @DeleteMapping("/v1/unit")
    public ResponseDto<String> deleteProduct(@RequestParam("productId") String productId) {
        ResponseDto<String> responseDto = new ResponseDto<>();

        try {
            responseDto.initSuccess(productService.deleteProductInfo(productId));
        } catch (Exception ex) {
            responseDto.initFail("FAIL", ex);
        }

        return responseDto;
    }

    @Operation(
            operationId = "retrieveProductUnit",
            summary = "상품 단건 조회 API",
            description = "상품 단건 조회 API",
            tags = {"product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "상품정보 단건 반환", content = {

                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
                    })
            }
    )
    @GetMapping("/v1/unit")
    public ResponseDto<ProductResponseDto> retrieveProductUnit(@RequestParam("productId") String productId) {
        ResponseDto<ProductResponseDto> responseDto = new ResponseDto<>();

        try {
            responseDto.initSuccess(productService.retrieveProductUnit(productId));
        } catch (Exception ex) {
            responseDto.initFail(null, ex);
        }

        return responseDto;
    }

    @Operation(
            operationId = "addProductList",
            summary = "상품 복수건 등록 API",
            description = "상품 복수건 등록 API",
            tags = {"product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "등록 성공여부 반환", content = {

                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
                    })
            }
    )
    @PostMapping("/v1/units")
    public ResponseDto<String> addProductList(@RequestBody List<ProductRequestDto> productRequestDtoList) {
        ResponseDto<String> responseDto = new ResponseDto<>();

        try {
            responseDto.initSuccess(productService.addProductInfoList(productRequestDtoList));
        } catch (Exception ex) {
            responseDto.initFail("FAIL", ex);
        }

        return responseDto;
    }


    @Operation(
            operationId = "modifyProductList",
            summary = "상품 복수건 수정 API",
            description = "상품 복수건 수정 API",
            tags = {"product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "수정 성공여부 반환", content = {

                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
                    })
            }
    )
    @PutMapping("/v1/units")
    public ResponseDto<String> modifyProductList(@RequestBody List<ProductRequestDto> productRequestDtoList) {
        ResponseDto<String> responseDto = new ResponseDto<>();

        try {
            responseDto.initSuccess(productService.modifyProductInfoList(productRequestDtoList));
        } catch (Exception ex) {
            responseDto.initFail("FAIL", ex);
        }

        return responseDto;
    }


    @Operation(
            operationId = "retrieveInventoryList",
            summary = "상품 복수건 조회 API",
            description = "상품 복수건 조회 API",
            tags = {"product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "상품정보 리스트 반환", content = {

                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
                    })
            }
    )
    @GetMapping("/v1/units")
    public ResponseDto<ProductListResponseDto> retrieveProductList(@RequestParam("productIdList") List<String> productIdList) {
        ResponseDto<ProductListResponseDto> responseDto = new ResponseDto<>();

        try {
            responseDto.initSuccess(productService.retrieveProductList(productIdList));
        } catch (Exception ex) {
            responseDto.initFail(null, ex);
        }

        return responseDto;
    }

    @Operation(
            operationId = "modifyProductList",
            summary = "대분류 상품 리스트 조회 API",
            description = "대분류 내, 포함된 상품 리스트 조회 API",
            tags = {"product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "상품정보 리스트 반환", content = {

                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
                    })
            }
    )
    @GetMapping("/v1/category/{category}")
    public ResponseDto<ProductListResponseDto> retrieveCategoryList(@PathVariable("category") String category) {
        ResponseDto<ProductListResponseDto> listResponseDto = new ResponseDto<>();

        try {
            listResponseDto.initSuccess(productService.retrieveCategoryList(category));
        } catch (Exception ex) {
            listResponseDto.initFail(null, ex);
        }

        return listResponseDto;
    }


    @Operation(
            operationId = "modifyProductList",
            summary = "소분류 상품 리스트 조회 API",
            description = "소분류 내, 포함된 상품 리스트 조회 API",
            tags = {"product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "상품정보 리스트 반환", content = {

                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
                    })
            }
    )
    @GetMapping("/v1/category/{category}/sub-category/{subcategory}")
    public ResponseDto<ProductListResponseDto> retrieveSubCategoryList(@PathVariable("category") String category,
                                                                       @PathVariable("subcategory") String subcategory) {
        ResponseDto<ProductListResponseDto> listResponseDto = new ResponseDto<>();

        try {
            listResponseDto.initSuccess(productService.retrieveSubCategoryList(category, subcategory));
        } catch (Exception ex) {
            listResponseDto.initFail(null, ex);
        }

        return listResponseDto;
    }
}
