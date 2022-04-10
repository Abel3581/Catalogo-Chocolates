package com.chocolate.amaro.model.entity;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@Entity
public class Invoice {

    @Id
    @Column(name = "invoice_id")
    private Long id;
    private int amount = 0;
    private Integer quantity = 0;

    @OneToMany(mappedBy = "invoiceId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Product> productList;

    //REFERENCIA AL DUEÑO DEL CARRITO
    @OneToOne
    @JoinColumn(name = "user_id", insertable = true)
    private User user;
}
