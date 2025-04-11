package com.MicroServicesEcom.product_service.Repositories;

import com.MicroServicesEcom.product_service.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryRepository extends JpaRepository<Category, Long> {
}
