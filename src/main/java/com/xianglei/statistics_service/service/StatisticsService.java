package com.xianglei.statistics_service.service;

import com.xianglei.statistics_service.domain.entities.Product;

import java.util.List;

public interface StatisticsService {

    List<Product> listProduct();

    Product findById(int id);


}
