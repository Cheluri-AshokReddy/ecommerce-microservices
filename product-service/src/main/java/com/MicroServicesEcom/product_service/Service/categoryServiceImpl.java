package com.MicroServicesEcom.product_service.Service;

import com.MicroServicesEcom.product_service.DTO.CategoryDTO;
import com.MicroServicesEcom.product_service.DTO.ProductDTO;
import com.MicroServicesEcom.product_service.Exceptions.ResourceNotFoundException;
import com.MicroServicesEcom.product_service.Model.Category;
import com.MicroServicesEcom.product_service.Model.Product;
import com.MicroServicesEcom.product_service.Repositories.categoryRepository;
import com.MicroServicesEcom.product_service.Repositories.productRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryServiceImpl implements categoryService {

    @Autowired
    private categoryRepository categoryrepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private productRepository productrepository;

    @Override
    public CategoryDTO addCategory(Category category) {
        CategoryDTO categoryDTO = modelMapper.map(categoryrepository.save(category), CategoryDTO.class);
        List<Product>products = productrepository.findByCategory_CategoryId(category.getCategoryId());
        List<ProductDTO>productDTOS=products
                .stream()
                .map(product->modelMapper.map(product, ProductDTO.class))
                .toList();
        categoryDTO.setProducts(productDTOS);
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category>categories=categoryrepository.findAll();
        List<CategoryDTO>categoryDTOS=categories
                .stream()
                .map(category ->modelMapper.map(category, CategoryDTO.class) )
                .toList();
        for(CategoryDTO categoryDTO:categoryDTOS){
            Long categoryid=categoryDTO.getCategoryId();
            List<Product>products=productrepository.findByCategory_CategoryId(categoryid);
            List<ProductDTO>productDTOS=products
                    .stream()
                    .map(product -> modelMapper.map(product, ProductDTO.class))
                    .toList();
            categoryDTO.setProducts(productDTOS);

        }
        return categoryDTOS;
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryrepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",id));
        CategoryDTO categoryDTO=modelMapper.map(category, CategoryDTO.class);
        List<Product>products = productrepository.findByCategory_CategoryId(category.getCategoryId());
        List<ProductDTO>productDTOS=products
                .stream()
                .map(product->modelMapper.map(product, ProductDTO.class))
                .toList();
        categoryDTO.setProducts(productDTOS);
        return categoryDTO;
    }

    @Override
    public CategoryDTO deleteCategoryById(Long id) {
        Category category = categoryrepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",id));
        if(category != null) {
            categoryrepository.delete(category);
        }else{
            throw new RuntimeException("Category not found with id: " + id);
        }
        return modelMapper.map(category, CategoryDTO.class);
    }
}
