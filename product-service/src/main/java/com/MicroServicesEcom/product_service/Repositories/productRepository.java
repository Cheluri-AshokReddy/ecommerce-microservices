package com.MicroServicesEcom.product_service.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.MicroServicesEcom.product_service.Model.Product;

import java.util.List;

public interface productRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory_CategoryId(Long categoryId);


}
