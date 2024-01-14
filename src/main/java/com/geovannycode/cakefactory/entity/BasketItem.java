package com.geovannycode.cakefactory.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BasketItem {

    final private Item item;
    final private int qty;

    public BigDecimal getTotal() {
        return this.item.getPrice().multiply(BigDecimal.valueOf(qty));
    };


}
