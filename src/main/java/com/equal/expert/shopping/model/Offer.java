package com.equal.expert.shopping.model;

import com.equal.expert.shopping.constant.OfferType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class Offer {
    private OfferType offerType;
    private BigDecimal discountPercent;
}
