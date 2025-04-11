package com.MicroServicesEcom.product_service.Controller;

import com.MicroServicesEcom.product_service.DTO.ProductDTO;
import com.MicroServicesEcom.product_service.Model.Product;
import com.MicroServicesEcom.product_service.Service.productServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class productController {

    @Autowired
    private productServiceImpl productserviceimpl;

    private static final Logger LOGGER
            = LoggerFactory.getLogger(productController.class);

    @PostMapping("/addproduct/{categoryid}")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product, @PathVariable Long categoryid) {
        ProductDTO productresponse = productserviceimpl.addProduct(product,categoryid);
        LOGGER.info("Product add: {}", product);
        return new ResponseEntity<>(productresponse, HttpStatus.CREATED);
    }
    @GetMapping("/getallproducts")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productresponse = productserviceimpl.getAllProducts();
        LOGGER.info("Product list: {}", productresponse);
        return new ResponseEntity<>(productresponse, HttpStatus.OK);
    }

    @GetMapping("/getproductbyid/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO productresponse = productserviceimpl.getProductById(id);
        LOGGER.info("Product find: id={}", id);
        return new ResponseEntity<>(productresponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteproductbyid/{id}")
    public ResponseEntity<ProductDTO> addProduct(@PathVariable Long id) {
        ProductDTO productresponse = productserviceimpl.deleteProductById(id);
        LOGGER.info("Product delete: {}",id);
        return new ResponseEntity<>(productresponse, HttpStatus.CREATED);
    }
}
