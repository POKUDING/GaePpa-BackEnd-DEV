package com.sparta.gaeppa.product.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.gaeppa.product.dto.productCategory.ProductCategoryRequestDto;
import com.sparta.gaeppa.product.service.ProductCategoryService;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class ProductCategoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductCategoryService productCategoryService;

    @InjectMocks
    private ProductCategoryController productCategoryController;

    private ObjectMapper objectMapper;

    private ProductCategoryRequestDto requestDto;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productCategoryController).build();
        objectMapper = new ObjectMapper();
        requestDto = new ProductCategoryRequestDto();
        // 예시 데이터
        requestDto.setCategoryName("한식");
    }

    @Test
    public void createProductCategory_ShouldReturnCreated() throws Exception {
        mockMvc.perform(post("/v1/product-categories/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.response").value("한식"));
        // 서비스의 메소드가 호출되었는지 검증
        verify(productCategoryService, times(1)).createProductCategory(any());
    }

    @Test
    public void updateProductCategory_ShouldReturnOk() throws Exception {
        UUID productCategoryId = UUID.randomUUID();
        mockMvc.perform(put("/v1/product-categories/{productCategoryId}", productCategoryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Product Category Updated"));

        // 서비스의 메소드가 호출되었는지 검증
        verify(productCategoryService, times(1)).updateProductCategory(any(), any());
    }

    @Test
    public void deleteProductCategory_ShouldReturnOk() throws Exception {
        UUID productCategoryId = UUID.randomUUID();
        mockMvc.perform(delete("/v1/product-categories/{productCategoryId}", productCategoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Product Category Deleted"));

        // 서비스의 메소드가 호출되었는지 검증
        verify(productCategoryService, times(1)).deleteProductCategory(any());
    }
}
