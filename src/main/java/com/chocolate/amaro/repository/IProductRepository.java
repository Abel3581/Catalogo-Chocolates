package com.chocolate.amaro.repository;

import com.chocolate.amaro.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{


    List<Product> findByName(String name);
}
