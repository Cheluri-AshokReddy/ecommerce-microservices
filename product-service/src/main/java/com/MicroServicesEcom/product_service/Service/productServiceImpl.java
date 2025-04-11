package com.MicroServicesEcom.product_service.Service;

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
public class productServiceImpl implements productService {

    @Autowired
    private categoryRepository categoryrepository;

    @Autowired
    private productRepository productrepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(Product product, Long categoryId) {
        Category category = categoryrepository.findById(categoryId).orElse(null);
        product.setCategory(category);
        Product productresponse=productrepository.save(product);
       // ProductDTO productDTO= modelMapper.map(productresponse, ProductDTO.class);
       // productDTO.setCategoryName(category.getCategoryName());
        return modelMapper.map(productresponse, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> productlist=productrepository.findAll();
        List<ProductDTO>productDTOS=productlist
                .stream()
                .map(product->modelMapper.map(product, ProductDTO.class))
                .toList();
        return productDTOS;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product=productrepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", id));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProductById(Long id) {
        Product product = productrepository.findById(id).orElse(null);
        if (product != null) {
            productrepository.delete(product);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
        return modelMapper.map(product, ProductDTO.class);
    }
}
