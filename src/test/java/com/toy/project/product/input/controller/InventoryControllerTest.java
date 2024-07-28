package com.toy.project.product.input.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.project.product.input.dto.DecreaseInventoryRequestDto;
import com.toy.project.product.input.dto.InventoryRequestDto;
import com.toy.project.product.inventory.service.InventoryService;
import com.toy.project.product.output.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class InventoryControllerTest {

    @InjectMocks
    private InventoryController inventoryController;
    @Mock
    private InventoryService inventoryService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private InventoryRequestDto requestDto;


    @BeforeEach
    public void setUp(){
        // 기본 세팅
        mockMvc = MockMvcBuilders.standaloneSetup(inventoryController).build();
        objectMapper = new ObjectMapper();

        // 요청 전문 세팅(단건)
        requestDto = new InventoryRequestDto(
                "12742", 800);
    }


    @DisplayName("상품재고 단건 등록 TC")
    @Test
    public void testAddInventory_success() throws Exception {
        // Service 응답전문
        String successMsg = "OK";

        // Controller 응답전문
        ResponseDto<String> successResponse = new ResponseDto<>();
        successResponse.initSuccess(successMsg);

        when(inventoryService.addInventoryInfo(any(InventoryRequestDto.class))).thenReturn(successMsg);

        mockMvc.perform(post("/product/inventory/v1/unit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(successResponse.getStatus()))
                .andExpect(jsonPath("$.message").value(successResponse.getMessage()))
                .andExpect(jsonPath("$.data").value(successResponse.getData()));

        verify(inventoryService, times(1)).addInventoryInfo(any(InventoryRequestDto.class));
    }

    @DisplayName("상품재고 단건 수정 TC")
    @Test
    public void testModifyInventory_success() throws Exception {
        // Service 응답전문
        String successMsg = "OK";

        // Controller 응답전문
        ResponseDto<String> successResponse = new ResponseDto<>();
        successResponse.initSuccess(successMsg);

        when(inventoryService.modifyInventoryInfo(any(InventoryRequestDto.class))).thenReturn(successMsg);

        mockMvc.perform(put("/product/inventory/v1/unit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(successResponse.getStatus()))
                .andExpect(jsonPath("$.message").value(successResponse.getMessage()))
                .andExpect(jsonPath("$.data").value(successResponse.getData()));

        verify(inventoryService, times(1)).modifyInventoryInfo(any(InventoryRequestDto.class));
    }


    @DisplayName("상품재고 단건 조회 TC")
    @Test
    public void testRetrieveInventory_success() throws Exception {
        // Service 응답전문
        InventoryResponseDto responseDto = new InventoryResponseDto();
        responseDto.setProductId("12742");

        // Controller 응답전문
        ResponseDto<InventoryResponseDto> successResponse = new ResponseDto<>();
        successResponse.initSuccess(responseDto);

        when(inventoryService.retrieveInventoryUnit(any(String.class))).thenReturn(responseDto);

        mockMvc.perform(get("/product/inventory/v1/unit")
                        .param("productId", "12742")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(successResponse.getStatus()))
                .andExpect(jsonPath("$.message").value(successResponse.getMessage()))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.productId").value(successResponse.getData().getProductId()));

        verify(inventoryService, times(1)).retrieveInventoryUnit(any(String.class));
    }

    @DisplayName("상품재고 복수건 조회 TC")
    @Test
    public void testRetrieveInventoryList_success() throws Exception {
        // Service 응답전문
        InventoryListResponseDto responseDto = new InventoryListResponseDto(
                Arrays.asList(new InventoryResponseDto(), new InventoryResponseDto()));

        // Controller 응답전문
        ResponseDto<InventoryListResponseDto> successResponse = new ResponseDto<>();
        successResponse.initSuccess(responseDto);

        when(inventoryService.retrieveInventoryList(anyList())).thenReturn(responseDto);

        mockMvc.perform(get("/product/inventory/v1/units")
                        .param("productIdList", "12742,12743")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(successResponse.getStatus()))
                .andExpect(jsonPath("$.message").value(successResponse.getMessage()))
                .andExpect(jsonPath("$.data").isNotEmpty());

        verify(inventoryService, times(1)).retrieveInventoryList(anyList());
    }

    @DisplayName("상품재고 차감 TC")
    @Test
    public void testDecreaseInventory_success() throws Exception {
        // Service 응답전문
        String successMsg = "OK";

        // Controller 응답전문
        ResponseDto<String> successResponse = new ResponseDto<>();
        successResponse.initSuccess(successMsg);

        when(inventoryService.decreaseInventory(any(DecreaseInventoryRequestDto.class))).thenReturn(successMsg);

        mockMvc.perform(put("/product/inventory/v1/decrease/unit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(successResponse.getStatus()))
                .andExpect(jsonPath("$.message").value(successResponse.getMessage()))
                .andExpect(jsonPath("$.data").value(successResponse.getData()));

        verify(inventoryService, times(1)).decreaseInventory(any(DecreaseInventoryRequestDto.class));
    }
}
