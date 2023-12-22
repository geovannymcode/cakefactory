package com.geovannycode.cakefactory.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {

    final private String title;
    final private BigDecimal price;

}
