package com.example.JavaProject2SpringSecurity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getAllProducts() throws SQLException {
        return ProductService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") int id) throws Exception {
        return ProductService.getProduct(id);
    }

    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product) throws Exception {
        int generatedId = ProductService.saveProduct(product);
        product.setId(generatedId);
        return ProductService.getProduct(product.getId());
    }

    @PatchMapping("/products/{id}")
    public Product updateProductPrice(@PathVariable("id") int id, @RequestParam(value = "price") BigDecimal price) throws Exception {
        ProductService.updateProductPrice(id, price);
        return ProductService.getProduct(id);
    }

    @DeleteMapping("/products")
    public String deleteProductsNotForSale() throws SQLException {
        return ProductService.deleteProductsNotForSale();
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable("id") int id) throws Exception {
        return ProductService.deleteProduct(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        return new ErrorResponse(e.getMessage());
    }

}