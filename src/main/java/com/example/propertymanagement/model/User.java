package com.example.propertymanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String username;

    private String password;

    private String email;

    // this will cause a deletion of user to delete everything else associated with it
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Property> properties = new ArrayList<Property>();

    public void addProperty(Property property){
        // doing bidirectional mapping
        this.properties.add(property);
        property.setUser(this);
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", properties=" + properties +
                '}';
    }
}
