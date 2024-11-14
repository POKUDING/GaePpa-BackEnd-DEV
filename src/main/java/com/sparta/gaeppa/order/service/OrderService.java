package com.sparta.gaeppa.order.service;

import com.sparta.gaeppa.global.exception.ExceptionStatus;
import com.sparta.gaeppa.global.exception.ServiceException;
import com.sparta.gaeppa.order.dto.OrderListResponseDto;
import com.sparta.gaeppa.order.dto.OrderProductDto;
import com.sparta.gaeppa.order.dto.OrderProductOptionDto;
import com.sparta.gaeppa.order.dto.OrderRequestDto;
import com.sparta.gaeppa.order.dto.OrderResponseDto;
import com.sparta.gaeppa.order.entity.OrderOption;
import com.sparta.gaeppa.order.entity.OrderProduct;
import com.sparta.gaeppa.order.entity.Orders;
import com.sparta.gaeppa.order.repository.OrderRepository;
import com.sparta.gaeppa.product.entity.Product;
import com.sparta.gaeppa.product.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    @Transactional(readOnly = true)
    public OrderListResponseDto getAllOrdersByMemberId(UUID memberId) {

        List<Orders> orderList = orderRepository.findAllOrdersByMemberId(memberId);

        return Optional.of(orderList)
                .filter(list -> !list.isEmpty())
                .map(OrderListResponseDto::new)
                .orElseThrow(() -> new ServiceException(ExceptionStatus.ORDER_NOT_FOUND));
    }

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {

        Orders orders = requestDto.toEntity();
        int orderTotalPrice = 0;

        List<OrderProductDto> productListDto = requestDto.getOrderProductList();

        // 상품 리스트 한 개씩 엔티티화
        for (OrderProductDto orderProductDto : productListDto) {

            try {

                Product product = productRepository.findById(orderProductDto.getProductId())
                        .orElseThrow(() -> new ServiceException(ExceptionStatus.PRODUCT_NOT_FOUND));

                OrderProduct orderProduct = orderProductDto.toEntity(product);

                List<OrderProductOptionDto> optionListDto = orderProductDto.getProductOptionList();
                for (OrderProductOptionDto optionDto : optionListDto) {

                    OrderOption option = optionDto.toEntity();
                    orderProduct.putOrderOption(option);
                    orderTotalPrice += option.getOptionPrice();
                }

                orders.putOrderProduct(orderProduct);
                orderTotalPrice += orderProduct.getOrderProductPrice() * orderProduct.getOrderProductPrice();

            } catch (NullPointerException e) {
                throw new ServiceException(ExceptionStatus.ORDER_REQUEST_NOT_FOUND);
            }
        }

        return OrderResponseDto.from(orderRepository.save(orders));
    }

}
