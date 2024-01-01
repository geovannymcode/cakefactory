package com.geovannycode.cakefactory.service.impl;

import com.geovannycode.cakefactory.entity.Item;
import com.geovannycode.cakefactory.entity.ItemEntity;
import com.geovannycode.cakefactory.repository.ItemRepository;
import com.geovannycode.cakefactory.service.CatalogService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class JpaCatalogService implements CatalogService {

    private final ItemRepository itemRepository;

    public JpaCatalogService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    @Override
    public Iterable<Item> getItems() {
        return StreamSupport.stream(itemRepository.findAll().spliterator(), false)
                .map(this::mapEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Item getItemBySku(String sku) {
        ItemEntity entity = this.itemRepository.findBySku(sku);
        if (entity == null) {
            return null;
        }

        return mapEntity(entity);
    }

    Item mapEntity(ItemEntity entity) {
        return new Item(entity.sku, entity.title, entity.price);
    }

}
