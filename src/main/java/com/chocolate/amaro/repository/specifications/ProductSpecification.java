package com.chocolate.amaro.repository.specifications;
import com.chocolate.amaro.dto.ProductFiltersDto;
import com.chocolate.amaro.model.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Component
public class ProductSpecification {


     public Specification<Product> getByFilters(ProductFiltersDto filtersDto) {
        return (root, Query, criteriaBuilder) ->{
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.hasLength(filtersDto.getName())){
                predicates.add(criteriaBuilder.equal(root.get("name"), filtersDto.getName()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}

