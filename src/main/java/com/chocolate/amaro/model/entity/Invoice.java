package com.chocolate.amaro.model.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter @Setter
@Entity
public class Invoice {

    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int totalPrice = 0;
    private Integer quantity = 0;
    private String nameUser, username, lastname;
    @CreationTimestamp
    private Timestamp timestamp;

    //REFERENCIA AL DUEÑO DEL CARRITO
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
