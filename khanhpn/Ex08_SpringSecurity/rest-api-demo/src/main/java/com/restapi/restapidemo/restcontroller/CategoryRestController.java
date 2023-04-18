package com.restapi.restapidemo.restcontroller;

import com.restapi.restapidemo.dao.daointerface.CategoryJpaDAO;
import com.restapi.restapidemo.dto.CategoryDTO;
import com.restapi.restapidemo.dto.converter.CategoryDTOConverter;
import com.restapi.restapidemo.entity.Category;
import com.restapi.restapidemo.exception.CategoryException.CategoryNotFoundExeption;
import com.restapi.restapidemo.service.serviceinterface.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryRestController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryDTOConverter categoryDTOConverter;

    @Autowired
    CategoryJpaDAO jpaDAO;

    @GetMapping("/category")
    public List<CategoryDTO> getAllCategory() {
        List<Category> allCategory = categoryService.getAllCategory();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : allCategory) {
            CategoryDTO categoryDTO = categoryDTOConverter.convertToCategoryDTO(category);
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

    @GetMapping("/category/{id}")
    public CategoryDTO getCategoryById(@PathVariable int id) {
        Category category = categoryService.getCategoryById(id);
        CategoryDTO categoryDTO = categoryDTOConverter.convertToCategoryDTO(category);
        if (category == null) {
            throw new CategoryNotFoundExeption("Category not found " + id);
        }
        return categoryDTO;
    }

    @PutMapping("/category/{id}")
    public void updateCategory(@PathVariable int id, @Valid @RequestBody CategoryDTO categoryDTO) {
        Category existedCategory = categoryService.getCategoryById(id);
        if (existedCategory != null && categoryDTO.getId() == existedCategory.getId()) {
            Category category = null;
            try {
                category = categoryDTOConverter.convertCategoryDtoToEntity(categoryDTO);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            categoryService.saveCategory(category);
        } else {
            throw new CategoryNotFoundExeption("Category not found " + id);
        }
    }

    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable int id) {
        Category existedCategory = categoryService.getCategoryById(id);
        if (existedCategory == null) {
            throw new CategoryNotFoundExeption("Category not found " + id);
        } else {
            categoryService.deleteCategory(id);
        }
    }

    @PostMapping("/category")
    public void addNewCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = null;
        try {
            category = categoryDTOConverter.convertCategoryDtoToEntity(categoryDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        category.setId(0);
        categoryService.saveCategory(category);
    }

    @GetMapping("/categories")
    public Page<Category> listCategory(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(page, size, sortable);
        Page<Category> listCategory = jpaDAO.findAll(pageable);

        return listCategory;
    }
}