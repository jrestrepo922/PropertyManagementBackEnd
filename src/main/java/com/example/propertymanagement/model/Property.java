package com.example.propertymanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue
    private int id;

    private String street;

    private String zipcode;

    private String state;

    private String imgUrl;

    @Column(name = "bedroom_count")
    private int bedroomCount;

    @Column(name = "bathroom_count")
    private int bathroomCount;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Tenant> tenants = new ArrayList<Tenant>();

    public void addTenant(Tenant tenant){
        //bidirectional mapping
        this.tenants.add(tenant);
        tenant.setProperty(this);
    }

    public Property(String street, String zipcode, String state, int bedroomCount, int bathroomCount, String imgUrl) {
        this.street = street;
        this.zipcode = zipcode;
        this.state = state;
        this.bedroomCount = bedroomCount;
        this.bathroomCount = bathroomCount;
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", state='" + state + '\'' +
                ", bedroomCount=" + bedroomCount +
                ", bathroomCount=" + bathroomCount +
                ", tenants=" + tenants +
                '}';
    }
}
