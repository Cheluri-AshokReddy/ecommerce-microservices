package com.MicroServicesEcom.product_service.Service;

import com.MicroServicesEcom.product_service.DTO.CategoryDTO;
import com.MicroServicesEcom.product_service.Model.Category;

import java.util.List;

public interface categoryService {
    public CategoryDTO addCategory(Category category);
    public List<CategoryDTO> getAllCategories();
    public CategoryDTO getCategoryById(Long id);
    public CategoryDTO deleteCategoryById(Long id);
}
