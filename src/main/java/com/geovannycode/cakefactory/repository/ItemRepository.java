package com.geovannycode.cakefactory.repository;

import com.geovannycode.cakefactory.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<ItemEntity, String> {
    ItemEntity findBySku(String sku);
}