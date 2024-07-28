package com.toy.project.product.input.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.project.product.input.dto.ProductRequestDto;
import com.toy.project.product.inventory.service.ProductService;
import com.toy.project.product.output.dto.ProductListResponseDto;
import com.toy.project.product.output.dto.ProductResponseDto;
import com.toy.project.product.output.dto.ResponseDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductService productService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private ProductRequestDto requestDto;
    private List<ProductRequestDto> requestDtoList;


    @BeforeEach
    public void setUp(){
        // 기본 세팅
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        objectMapper = new ObjectMapper();


        // 요청 전문 세팅(단건)
        requestDto = new ProductRequestDto(
                "12742", "비비고", "10000","1001","-",7800);

        // 요청 전문 세팅(복수건)
        requestDtoList = Arrays.asList(
                new ProductRequestDto("12742", "비비고1", "10000", "1001", "-", 7800),
                new ProductRequestDto("12743", "비비고2", "10000", "1001", "-", 7801)
        );
    }

    @DisplayName("상품 단건 등록 TC")
    @Test
    public void testAddProduct_success() throws Exception {
        // Service 응답전문
        String successMsg = "OK";

        // Controller 응답전문
        ResponseDto<String> successResponse = new ResponseDto<>();
        successResponse.initSuccess(successMsg);

        when(productService.addProductInfo(any(ProductRequestDto.class))).thenReturn(successMsg);

        mockMvc.perform(post("/product/v1/unit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(successResponse.getStatus()))
                .andExpect(jsonPath("$.message").value(successResponse.getMessage()))
                .andExpect(jsonPath("$.data").value(successResponse.getData()));

        verify(productService, times(1)).addProductInfo(any(ProductRequestDto.class));
    }

    @DisplayName("상품 단건 수정 TC")
    @Test
    public void testModifyProduct_success() throws Exception {
        // Service 응답전문
        String successMsg = "OK";

        // Controller 응답전문
        ResponseDto<String> successResponse = new ResponseDto<>();
        successResponse.initSuccess(successMsg);

        when(productService.modifyProductInfo(any(ProductRequestDto.class))).thenReturn(successMsg);

        mockMvc.perform(put("/product/v1/unit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(successResponse.getStatus()))
                .andExpect(jsonPath("$.message").value(successResponse.getMessage()))
                .andExpect(jsonPath("$.data").value(successResponse.getData()));

        verify(productService, times(1)).modifyProductInfo(any(ProductRequestDto.class));
    }

    @DisplayName("상품 단건 조회 TC")
    @Test
    public void testRetrieveProduct_success() throws Exception {
        // Service 응답 전문
        ProductResponseDto resDto = new ProductResponseDto();
        resDto.setProductId("12742");

        // Controller 응답 전문
        ResponseDto<ProductResponseDto> successResponse = new ResponseDto<>();
        successResponse.initSuccess(resDto);

        when(productService.retrieveProductUnit(any(String.class))).thenReturn(resDto);

        mockMvc.perform(get("/product/v1/unit")
                        .param("productId","12742")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(successResponse.getStatus()))
                .andExpect(jsonPath("$.message").value(successResponse.getMessage()))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.productId").value(successResponse.getData().getProductId()));

        verify(productService, times(1)).retrieveProductUnit(any(String.class));
    }

    @DisplayName("상품 복수건 등록 TC")
    @Test
    public void testAddProductList_success() throws Exception {
        // Service 응답전문
        String successMsg = "OK";

        // Controller 응답전문
        ResponseDto<String> successResponse = new ResponseDto<>();
        successResponse.initSuccess(successMsg);

        when(productService.addProductInfoList(anyList())).thenReturn(successMsg);

        mockMvc.perform(post("/product/v1/units")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(successResponse.getStatus()))
                .andExpect(jsonPath("$.message").value(successResponse.getMessage()))
                .andExpect(jsonPath("$.data").value(successResponse.getData()));

        verify(productService, times(1)).addProductInfoList(anyList());
    }

    @DisplayName("상품 복수건 수정 TC")
    @Test
    public void testModifyProductList_success() throws Exception {
        // Service 응답전문
        String successMsg = "OK";

        // Controller 응답전문
        ResponseDto<String> successResponse = new ResponseDto<>();
        successResponse.initSuccess(successMsg);

        when(productService.modifyProductInfoList(anyList())).thenReturn(successMsg);

        mockMvc.perform(put("/product/v1/units")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(successResponse.getStatus()))
                .andExpect(jsonPath("$.message").value(successResponse.getMessage()))
                .andExpect(jsonPath("$.data").value(successResponse.getData()));

        verify(productService, times(1)).modifyProductInfoList(anyList());
    }

    @DisplayName("상품 복수건 조회 TC")
    @Test
    public void testRetrieveProductList_success() throws Exception {
        // Service 응답전문
        ProductListResponseDto resDto = new ProductListResponseDto(
                Arrays.asList(new ProductResponseDto(), new ProductResponseDto()));

        // Controller 응답전문
        ResponseDto<ProductListResponseDto> successResponse = new ResponseDto<>();
        successResponse.initSuccess(resDto);

        when(productService.retrieveProductList(anyList())).thenReturn(resDto);

        mockMvc.perform(get("/product/v1/units")
                .param("productIdList", "12342,12343")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(successResponse.getStatus()))
                .andExpect(jsonPath("$.message").value(successResponse.getMessage()))
                .andExpect(jsonPath("$.data").isNotEmpty());


        verify(productService, times(1)).retrieveProductList(anyList());
    }

    @DisplayName("대분류 기준, 상품 리스트 조회 TC")
    @Test
    public void testRetrieveCategoryList_success() throws Exception {
        // Service 응답전문
        ProductListResponseDto resDto = new ProductListResponseDto(
                Arrays.asList(new ProductResponseDto(), new ProductResponseDto()));

        // Controller 응답전문
        ResponseDto<ProductListResponseDto> successResponse = new ResponseDto<>();
        successResponse.initSuccess(resDto);

        when(productService.retrieveCategoryList(any(String.class))).thenReturn(resDto);

        mockMvc.perform(get("/product/v1/category/{category}", "10000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(successResponse.getStatus()))
                .andExpect(jsonPath("$.message").value(successResponse.getMessage()))
                .andExpect(jsonPath("$.data").isNotEmpty());


        verify(productService, times(1)).retrieveCategoryList(any(String.class));
    }

    @DisplayName("소분류 기준, 상품 리스트 조회 TC")
    @Test
    public void testRetrieveSubCategoryList_success() throws Exception {
        // Service 응답전문
        ProductListResponseDto resDto = new ProductListResponseDto(
                Arrays.asList(new ProductResponseDto(), new ProductResponseDto()));

        // Controller 응답전문
        ResponseDto<ProductListResponseDto> successResponse = new ResponseDto<>();
        successResponse.initSuccess(resDto);

        when(productService.retrieveSubCategoryList(any(String.class),any(String.class))).thenReturn(resDto);

        mockMvc.perform(get("/product/v1/category/{category}/sub-category/{subcategory}", "10000","1001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(successResponse.getStatus()))
                .andExpect(jsonPath("$.message").value(successResponse.getMessage()))
                .andExpect(jsonPath("$.data").isNotEmpty());


        verify(productService, times(1)).retrieveSubCategoryList(any(String.class),any(String.class));
    }
}
