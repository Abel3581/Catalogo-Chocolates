package com.chocolate.amaro.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.sql.Timestamp;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "products_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "image_url",nullable = false)
    private String image;

    @Column(nullable = false)
    private Long price;

    @CreationTimestamp
    private Timestamp timestamp;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id", insertable = true, updatable = true)
    private Category category;
    private Long categoryId;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<Trolley> trolleyList = new ArrayList<>();

    //@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    //@JoinColumn(name = "user_id")
    //private User user;

    @Column(name = "soft_delete")
    private boolean softDelete = Boolean.FALSE;








}
