package com.geovannycode.cakefactory.service;

import com.geovannycode.cakefactory.entity.BasketItem;
import lombok.Data;

import java.util.Collection;

@Data
public class OrderReceivedEvent {

    private final String deliveryAddress;
    private final Collection<BasketItem> items;
}
