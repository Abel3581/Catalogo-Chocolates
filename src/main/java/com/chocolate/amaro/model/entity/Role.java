package com.chocolate.amaro.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roles_id")
    private long id;
    @Column(name = "name",nullable = false)
    private String name;
    private String description;
    @CreationTimestamp
    private Timestamp timestamp;
}
