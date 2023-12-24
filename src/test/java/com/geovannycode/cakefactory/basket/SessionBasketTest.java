package com.geovannycode.cakefactory.basket;

import com.geovannycode.cakefactory.entity.BasketItem;
import com.geovannycode.cakefactory.entity.Item;
import com.geovannycode.cakefactory.repository.Basket;
import com.geovannycode.cakefactory.service.CatalogService;
import com.geovannycode.cakefactory.service.SessionBasket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SessionBasketTest {

    String EXPECTED_TITLE = "Test item";

    Basket basket;
    CatalogService catalogService;

    @BeforeEach
    void setUp()
    {
        catalogService = mock(CatalogService.class);
        basket = new SessionBasket(catalogService);

        when(catalogService.getItemBySku("test")).thenReturn(new Item("test1", EXPECTED_TITLE, BigDecimal.TEN));
    }

    @Test
    void increasesTotal() {
        basket.add("test");
        basket.add("test");

        assertThat(basket.getTotalItems()).isEqualTo(2);
    }

    @Test
    void addsNewItem() {
        basket.add("test");
        assertHasSingleItemWithQty(1);
    }

    @Test
    void increasesQtyForExistingItem() {
        basket.add("test");
        basket.add("test");
        assertHasSingleItemWithQty(2);
    }

    @Test
    void removesItem() {
        basket.add("test");
        basket.remove("test");
        assertThat(basket.getItems()).isEmpty();
    }

    @Test
    void decreasesQtyForExistingItem() {
        basket.add("test");
        basket.add("test");
        basket.remove("test");
        assertHasSingleItemWithQty(1);
    }

    @Test
    void clearsBasket() {
        basket.add("test");
        basket.clear();

        assertThat(basket.getItems()).isEmpty();
        assertThat(basket.getTotalItems()).isEqualTo(0);
    }

    private void assertHasSingleItemWithQty(int qty) {
        Collection<BasketItem> items = basket.getItems();
        assertThat(items).hasSize(1);
        assertThat(items).allMatch(item -> EXPECTED_TITLE.equals(item.getItem().getTitle()) && item.getQty() == qty);
    }
}
