package com.geovannycode.cakefactory.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressPayment {
    private final String line1;
    private final String line2;
    private final String postcode;
}
