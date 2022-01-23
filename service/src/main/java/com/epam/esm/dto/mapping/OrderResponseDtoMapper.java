package com.epam.esm.dto.mapping;

import com.epam.esm.dto.OrderItemDto;
import com.epam.esm.dto.OrderResponseDto;
import com.epam.esm.entity.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderResponseDtoMapper {
    private final ModelMapper mapper;
    private final OrderItemDtoMapper orderItemMapper;

    public Order toEntity(OrderResponseDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Order.class);
    }

    public OrderResponseDto toDto(Order entity) {
        OrderResponseDto dto = Objects.isNull(entity) ? null : mapper.map(entity, OrderResponseDto.class);

        List<OrderItemDto> orderItemsDto = new ArrayList<>();
        if (entity != null) {
            orderItemsDto = entity.getOrderItems() == null
                    ? null
                    : entity.getOrderItems().stream()
                    .map(orderItemMapper::toDto)
                    .collect(Collectors.toList());
        }

        BigDecimal totalPrice = BigDecimal.ZERO;
        int count = 0;
        for (OrderItemDto orderItem : orderItemsDto) {
            totalPrice = totalPrice.add(orderItem.getPrice());
            count++;
        }

        dto.setOrderGiftCertificates(orderItemsDto);
        dto.setTotalPrice(totalPrice);
        dto.setCount(count);
        return dto;
    }
}
