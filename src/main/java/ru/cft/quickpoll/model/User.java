package ru.cft.quickpoll.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "USERS")
@Data
public class User {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;

    @Column(name="user_name")
    @NotEmpty
    private String userName;

    @Column(name="password")
    @NotEmpty
    @JsonIgnore
    private String password;

    @Column(name="first_name")
    @NotEmpty
    private String firstName;

    @Column(name="last_name")
    @NotEmpty
    private String lastName;

    @Column(name="admin", columnDefinition = "varchar(3)")
    @Type(type="yes_no")
    @NotEmpty
    private boolean admin;
}
