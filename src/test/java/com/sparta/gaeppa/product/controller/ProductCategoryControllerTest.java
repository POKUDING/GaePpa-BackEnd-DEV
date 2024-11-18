package com.sparta.gaeppa.product.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.gaeppa.members.entity.MemberRole;
import com.sparta.gaeppa.product.dto.productCategory.ProductCategoryRequestDto;
import com.sparta.gaeppa.product.dto.productCategory.ProductCategoryResponseDto;
import com.sparta.gaeppa.product.service.ProductCategoryService;
import com.sparta.gaeppa.security.jwts.entity.AuthenticatedUserDto;
import com.sparta.gaeppa.security.jwts.entity.CustomUserDetails;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(controllers = ProductCategoryController.class)
@MockBean(JpaMetamodelMappingContext.class) // JPA Auditing을 위한 Mock 객체 생성
@ExtendWith(MockitoExtension.class)
public class ProductCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductCategoryService productCategoryService;

    private ObjectMapper objectMapper;

    private ProductCategoryRequestDto requestDto;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        requestDto = ProductCategoryRequestDto.builder().categoryName("요리류").build();
    }

    //TODO: BeforeEach 와 동작비교
//    @BeforeAll
//    public void setUp() {
//        objectMapper = new ObjectMapper();
//        requestDto = ProductCategoryRequestDto.builder().categoryName("요리류").build();
//    }

    @Test
    @DisplayName("상품 카테고리 등록 성공 테스트")
    public void createProductCategory_ShouldReturnCreated() throws Exception {
        //given
        ProductCategoryResponseDto responseDto = ProductCategoryResponseDto.builder().id(UUID.randomUUID()).name("면류")
                .build();

        given(productCategoryService.createProductCategory(any(ProductCategoryRequestDto.class))).willReturn(
                responseDto);

        //when
        ResultActions resultActions = mockMvc.perform(post("/v1/product-categories")
                .content(objectMapper.writeValueAsString(requestDto))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.response.id").value(responseDto.getId().toString()))
                .andExpect(jsonPath("$.response.name").value(responseDto.getName()));

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
        AuthenticatedUserDto authenticatedUserDto = new AuthenticatedUserDto(UUID.randomUUID(), "example@example.com",
                "master", "password", MemberRole.MASTER, true);
        CustomUserDetails userDetails = new CustomUserDetails(authenticatedUserDto);
        mockMvc.perform(delete("/v1/product-categories/{productCategoryId}", productCategoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Product Category Deleted"));

        // 서비스의 메소드가 호출되었는지 검증
        verify(productCategoryService, times(1)).deleteProductCategory(any(), userDetails);
    }

    @TestConfiguration
    static class SecurityConfig {
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            // Spring Security를 비활성화
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(
                            (authorizeHttpRequests) -> authorizeHttpRequests.anyRequest().permitAll());  // 모든 요청을 허용
            return http.build();
        }
    }
}
