package com.geovannycode.cakefactory.service;

import com.geovannycode.cakefactory.entity.BasketItem;
import lombok.Data;
import org.springframework.context.event.EventListener;

@Data
public class SimpleOrderReceiver {

    @EventListener
    public void onNewOrder(OrderReceivedEvent event) {
        System.out.println("New order received:");
        System.out.println("Delivery address " + event.getDeliveryAddress());
        for (BasketItem basketItem : event.getItems()) {
            System.out.println(basketItem.getItem().getTitle() + " - " + basketItem.getQty());
        }
    }
}
