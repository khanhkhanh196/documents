package com.restapi.restapidemo.dto.converter;

import com.restapi.restapidemo.dto.CategoryDTO;
import com.restapi.restapidemo.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class CategoryDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CategoryDTO convertToCategoryDTO(Category category) {
        CategoryDTO dto = modelMapper.map(category,CategoryDTO.class);
        return dto;
    }

    public Category convertCategoryDtoToEntity(CategoryDTO CategoryDTO) throws ParseException {
        Category category = modelMapper.map(CategoryDTO,Category.class);
        return category;
    }
}
