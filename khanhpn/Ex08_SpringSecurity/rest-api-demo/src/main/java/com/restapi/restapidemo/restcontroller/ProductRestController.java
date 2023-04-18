package com.restapi.restapidemo.restcontroller;

import com.restapi.restapidemo.dto.ProductDTO;
import com.restapi.restapidemo.dto.converter.ProductDTOConverter;
import com.restapi.restapidemo.entity.Product;
import com.restapi.restapidemo.exception.ProductException.ProductNotFoundException;
import com.restapi.restapidemo.service.serviceinterface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductDTOConverter converter;

    @GetMapping("/products")
    public List<ProductDTO> getAllProduct(){
        int position = 0;
        int pageSize = 10;
        List<Product> allProduct = productService.getAllProduct(position, pageSize);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product product: allProduct) {
            System.out.println(product);
            ProductDTO productDTO = converter.convertToProductDTO(product);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @GetMapping("/products/{id}")
    public ProductDTO getSingleProduct(@PathVariable int id) {
        Product product = productService.getProduct(id);
        if(product == null) {
            throw new ProductNotFoundException("Product not found " + id);
        }
        ProductDTO productDTO = converter.convertToProductDTO(product);
        return productDTO;
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable int id,@Valid @RequestBody ProductDTO productDTO) {

        Product existedProduct = productService.getProduct(id);
        if(existedProduct!=null && existedProduct.getId() == productDTO.getId()) {
            Product product = null;
            try {
                product = converter.convertProductDtoToEntity(productDTO);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            productService.saveProduct(product);
        } else {
            throw new ProductNotFoundException("Product not found " + id);
        }
    }
    @PostMapping("/products")
    public void addNewProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = null;
        try {
            product = converter.convertProductDtoToEntity(productDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        product.setId(0);
        productService.saveProduct(product);
    }
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        Product existedProduct = productService.getProduct(id);
        if(existedProduct!=null) {
            productService.deleteProduct(id);
        } else {
            throw new ProductNotFoundException("Product not found " + id);
        }
    }
}
