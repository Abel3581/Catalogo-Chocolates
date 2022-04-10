package com.chocolate.amaro.model.entity;

import com.chocolate.amaro.utils.EnumState;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Trolley {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Trolley")
    private Long id;

    @Column(name = "state", nullable = false, updatable = true)
    @Enumerated(value = EnumType.STRING)
    private EnumState enumState;

    @CreationTimestamp
    private Timestamp timestamp;

    //REFERENCIA AL DUEÑO DEL CARRITO
    @OneToOne
    @JoinColumn(name = "user_id", insertable = true)
    private User buyer;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "product_trolley", joinColumns = @JoinColumn(name = "trolley_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id"))
    private List<Product> products = new ArrayList<>();

    @Column(name = "soft_delete")
    private boolean softDelete = Boolean.FALSE;

    @Column(name = "amount", nullable = false, updatable = true)
    private int amount = 0;

    @Column(name = "quantity", nullable = false, updatable = true)
    private Integer quantity = 0;


    public void addProduct(Product product) {
        this.products.add(product);
        quantity += 1;
        amount += product.getPrice();
    }

    public boolean removeProduct(Product product) {
        quantity -= 1;
        amount -= product.getPrice();
        return (this.products.remove(product));
    }


}
