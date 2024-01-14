package com.geovannycode.cakefactory.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "catalog")
public class ItemEntity {

    @Id
    public String sku;

    @NotBlank
    public String title;

    @NotNull
    public BigDecimal price;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ItemEntity) {
            ItemEntity other = (ItemEntity) obj;
            return Objects.equals(this.sku, other.sku);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.sku);
    }
}
