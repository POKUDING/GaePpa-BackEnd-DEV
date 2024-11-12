package com.sparta.gaeppa.order.dto;

import com.sparta.gaeppa.order.entity.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressDto {

    private String address;
    private String detailAddress;
    private int postAddress;

    @Builder
    private AddressDto(String address, String detailAddress, int postAddress) {
        this.address = address;
        this.detailAddress = detailAddress;
        this.postAddress = postAddress;
    }

    public static AddressDto from(Address address) {
        return AddressDto.builder()
                .address(address.getAddress())
                .detailAddress(address.getDetailAddress())
                .postAddress(address.getPostAddress())
                .build();
    }
}
