package com.MicroServicesEcom.product_service.Service;

import com.MicroServicesEcom.product_service.DTO.ProductDTO;
import com.MicroServicesEcom.product_service.Model.Product;

import java.util.List;

public interface productService {
    public ProductDTO addProduct(Product product, Long categoriid);
    public List<ProductDTO> getAllProducts();
    public ProductDTO getProductById(Long id);
    public ProductDTO deleteProductById(Long id);

}
