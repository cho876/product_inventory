package com.toy.project.product.input.controller;

import com.toy.project.product.input.dto.DecreaseInventoryRequestDto;
import com.toy.project.product.input.dto.InventoryRequestDto;
import com.toy.project.product.inventory.service.InventoryService;
import com.toy.project.product.output.dto.InventoryListResponseDto;
import com.toy.project.product.output.dto.InventoryResponseDto;
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
@RequestMapping("/product/inventory")
@RequiredArgsConstructor
@Tag(name="inventory", description = "상품 재고 API")
public class InventoryController {

    private final InventoryService inventoryService;


    @Operation(
            operationId = "addInventory",
            summary = "상품 재고 단건 등록 API",
            description = "상품 재고 단건 등록 API",
            tags = {"inventory"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "등록 성공여부 반환", content = {

                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
                    })
            }
    )
    @PostMapping("/v1/unit")
    public ResponseDto<String> addInventory(@RequestBody InventoryRequestDto inventoryRequestDto) {
        ResponseDto<String> responseDto = new ResponseDto<>();

        try {
            responseDto.initSuccess(inventoryService.addInventoryInfo(inventoryRequestDto));
        } catch (Exception ex) {
            responseDto.initFail("FAIL", ex);
        }

        return responseDto;
    }

    @Operation(
            operationId = "modifyInventory",
            summary = "상품 재고 단건 수정 API",
            description = "상품 재고 단건 수정 API",
            tags = {"inventory"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "수정 성공여부 반환", content = {

                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
                    })
            }
    )
    @PutMapping("/v1/unit")
    public ResponseDto<String> modifyInventory(@RequestBody InventoryRequestDto inventoryRequestDto) {
        ResponseDto<String> responseDto = new ResponseDto<>();

        try {
            responseDto.initSuccess(inventoryService.modifyInventoryInfo(inventoryRequestDto));
        } catch (Exception ex) {
            responseDto.initFail("FAIL", ex);
        }

        return responseDto;
    }

    @Operation(
            operationId = "modifyInventory",
            summary = "상품 재고 단건 삭제 API",
            description = "상품 재고 단건 삭제 API",
            tags = {"inventory"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "수정 성공여부 반환", content = {

                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
                    })
            }
    )
    @DeleteMapping("/v1/unit")
    public ResponseDto<String> deleteInventory(@RequestParam("productId") String productId) {
        ResponseDto<String> responseDto = new ResponseDto<>();

        try {
            responseDto.initSuccess(inventoryService.deleteInventoryInfo(productId));
        } catch (Exception ex) {
            responseDto.initFail("FAIL", ex);
        }

        return responseDto;
    }

    @Operation(
            operationId = "decreaseInventory",
            summary = "상품 재고 차감 API",
            description = "상품 재고 차감 API\n" + "입력한 수량만큼 차감 진행",
            tags = {"inventory"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "재고차감 성공여부 반환", content = {

                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
                    })
            }
    )
    @PutMapping("/v1/decrease/unit")
    public ResponseDto<String> decreaseInventory(@RequestBody DecreaseInventoryRequestDto decreaseInventoryRequestDto) {
        ResponseDto<String> responseDto = new ResponseDto<>();

        try {
            responseDto.initSuccess(inventoryService.decreaseInventory(decreaseInventoryRequestDto));
        } catch (Exception ex) {
            responseDto.initFail("FAIL", ex);
        }

        return responseDto;
    }

    @Operation(
            operationId = "retrieveInventoryUnit",
            summary = "상품재고 단건 조회 API",
            description = "상품재고 단건 조회 API",
            tags = {"inventory"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "상품정보 단건 반환", content = {

                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
                    })
            }
    )
    @GetMapping("/v1/unit")
    public ResponseDto<InventoryResponseDto> retrieveInventoryUnit(@RequestParam("productId") String productId) {
        ResponseDto<InventoryResponseDto> responseDto = new ResponseDto<>();

        try {
            responseDto.initSuccess(inventoryService.retrieveInventoryUnit(productId));
        } catch (Exception ex) {
            responseDto.initFail(null, ex);
        }

        return responseDto;
    }

    @Operation(
            operationId = "retrieveInventoryList",
            summary = "상품재고 복수건 조회 API",
            description = "상품재고 복수건 조회 API",
            tags = {"inventory"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "상품정보 리스트 반환", content = {

                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
                    })
            }
    )
    @GetMapping("/v1/units")
    public ResponseDto<InventoryListResponseDto> retrieveInventoryList(@RequestParam("productIdList") List<String> productIdList) {
        ResponseDto<InventoryListResponseDto> responseDto = new ResponseDto<>();

        try {
            responseDto.initSuccess(inventoryService.retrieveInventoryList(productIdList));
        } catch (Exception ex) {
            responseDto.initFail(null, ex);
        }

        return responseDto;
    }
}
