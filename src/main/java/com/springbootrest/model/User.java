package com.springbootrest.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "usertab")
public class User {

    // One table like parent table
    @Id
    @GeneratedValue()
    private Integer id;
    private String name;
    private String username;
    private String password;

    //One Child table will be creted
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "rolestab",
            joinColumns = @JoinColumn(name="id")

    )
    @Column(name = "role")
    private Set<String> roles;

}
