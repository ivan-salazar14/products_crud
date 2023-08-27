package io.isalazar.products.products.model;

import jakarta.validation.constraints.Size;


public class ProductsDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    private Double price;

    private Integer stock;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(final Integer stock) {
        this.stock = stock;
    }

}
