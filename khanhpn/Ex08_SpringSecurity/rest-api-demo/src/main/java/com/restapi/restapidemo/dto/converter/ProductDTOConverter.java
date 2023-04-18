package com.restapi.restapidemo.dto.converter;

import com.restapi.restapidemo.dto.ProductDTO;
import com.restapi.restapidemo.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ProductDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO convertToProductDTO(Product product) {
        ProductDTO dto = modelMapper.map(product,ProductDTO.class);
        if(product != null) {
            dto.setCategory(product.getCategory());
            dto.setWarehouse(product.getProductWareHouse());
        }
        return dto;
    }

    public Product convertProductDtoToEntity(ProductDTO productDTO) throws ParseException {
        Product product = modelMapper.map(productDTO,Product.class);
        if(productDTO != null) {
            product.setCategory(productDTO.getCategory());
            product.setWarehouse(productDTO.getWarehouse());
        }
        return product;
    }
}
