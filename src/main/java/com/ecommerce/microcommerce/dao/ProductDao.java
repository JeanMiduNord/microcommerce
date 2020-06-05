package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;

import java.util.List;

public interface ProductDao  {
    public List<Product> findAll();

    public Product findById(Integer id);

    public Product save(Product product);
}
