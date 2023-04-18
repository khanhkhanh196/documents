package com.restapi.restapidemo.payload.product;

import com.restapi.restapidemo.entity.Product;
import com.restapi.restapidemo.entity.ProductPayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvertProductDetailToPayload {
    @Autowired
    private ModelMapper modelMapper;

    public ProductPayload convertProductToPayload(Product product) {
        ProductPayload payload = modelMapper.map(product,ProductPayload.class);
        return payload;
    }
}
