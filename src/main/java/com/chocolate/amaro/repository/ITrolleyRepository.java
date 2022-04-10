package com.chocolate.amaro.repository;

import com.chocolate.amaro.model.entity.Product;
import com.chocolate.amaro.model.entity.Trolley;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITrolleyRepository extends JpaRepository<Trolley, Long> {

}
