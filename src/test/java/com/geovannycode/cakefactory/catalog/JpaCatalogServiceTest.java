package com.geovannycode.cakefactory.catalog;

import com.geovannycode.cakefactory.entity.Item;
import com.geovannycode.cakefactory.entity.ItemEntity;
import com.geovannycode.cakefactory.repository.ItemRepository;
import com.geovannycode.cakefactory.service.JpaCatalogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@DataJpaTest
public class JpaCatalogServiceTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    ItemRepository itemRepository;

    private JpaCatalogService jpaCatalogService;

    @BeforeEach
    void setup() {
        this.jpaCatalogService = new JpaCatalogService(this.itemRepository);
    }

    @Test
    @DisplayName("returns data from the database")
    void returnsDataFromDatabase() {
        String expectedTitle = "Victoria Sponge";
        saveTestItem(expectedTitle, BigDecimal.valueOf(5.55));

        Iterable<Item> items = jpaCatalogService.getItems();

        org.assertj.core.api.Assertions.assertThat(items).anyMatch(item -> expectedTitle.equals(item.getTitle()));
    }

    private void saveTestItem(String title, BigDecimal price) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.sku = "test-item-1";
        itemEntity.title = title;
        itemEntity.price = price;

        testEntityManager.persistAndFlush(itemEntity);
    }
}
