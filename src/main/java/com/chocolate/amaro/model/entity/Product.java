package com.chocolate.amaro.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE products SET soft_delete = true WHERE products_id=?")
@Where(clause = "soft_delete = false")
@Entity
@Table(name = "products")

public class Product extends RepresentationModel<Product> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "products_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private Long price;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "categories_id", insertable = false, updatable = false)
    private Category category;

    @Column(name = "categories_id", nullable = false)
    private Long categoryId;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<Trolley> trolleyList = new ArrayList<>();

    @Column(name = "soft_delete")
    private boolean softDelete = Boolean.FALSE;








}
