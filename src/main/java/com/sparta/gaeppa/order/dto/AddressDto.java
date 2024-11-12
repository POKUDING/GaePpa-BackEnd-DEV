package com.sparta.gaeppa.order.dto;

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
    public AddressDto(String address, String detailAddress, int postAddress) {
        this.address = address;
        this.detailAddress = detailAddress;
        this.postAddress = postAddress;
    }
}
