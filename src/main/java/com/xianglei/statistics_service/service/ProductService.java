package com.xianglei.statistics_service.service;

import com.xianglei.statistics_service.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> listProduct();

    Product findById(int id);


}
