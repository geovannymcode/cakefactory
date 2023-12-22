package com.geovannycode.cakefactory.service;

import com.geovannycode.cakefactory.entity.Item;

public interface CatalogService {

    Iterable<Item> getItems();
}
