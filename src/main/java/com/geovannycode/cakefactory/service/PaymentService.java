package com.geovannycode.cakefactory.service;

import com.geovannycode.cakefactory.entity.Order;
import com.geovannycode.cakefactory.entity.PendingPayment;

import java.net.URI;

public interface PaymentService {
    String DEFAULT_CURRENCY = "GBP";

    PendingPayment create(Order orderToPay, URI returnUri);
    String complete(String orderId);
}
