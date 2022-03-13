package com.chocolate.amaro.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "slides")
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slides_id", nullable = false)
    private long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "text")
    private String text;

    @Column(name = "slide_order")
    private int order;

    @JoinColumn(name = "home_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Homepage homepageId;
}
