package com.MicroServicesEcom.product_service.DTO;

import com.MicroServicesEcom.product_service.Model.Product;
import jakarta.persistence.OneToMany;

import java.util.List;

public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
    private List<ProductDTO> products;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
