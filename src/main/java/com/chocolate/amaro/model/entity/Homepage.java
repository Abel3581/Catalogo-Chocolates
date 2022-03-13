package com.chocolate.amaro.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE homepage SET soft_delete=true WHERE id=?")
@Where(clause = "soft_delete=false")
@Table(name = "homepage")
public class Homepage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "home_id", nullable = false)
    private Long id;

    @Column(name = "org_name", nullable = false)
    private String name;

    @Column(name = "org_image_url", nullable = false)
    private String image;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "welcome_text", nullable = false)
    private String welcomeText;

    @Column(name = "about_us_text", nullable = false)
    private String aboutUsText;

    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timeStamp;

    @Column(name = "soft_delete")
    private boolean softDelete;

    @Column(name = "org_facebook_url")
    private String facebook;

    @Column(name = "org_instagram_url")
    private String instagram;

    @Column(name = "org_linkedin_url")
    private String linkedin;

    @OneToMany(mappedBy = "homepageId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Slide> slideList = new ArrayList<>();

}
