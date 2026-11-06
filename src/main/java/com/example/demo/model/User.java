package com.example.demo.model;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "keycloak_id", unique = true)
    private String keycloakId;

    @NotBlank(message = "Username is required")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Fullname is required")
    @Column(nullable = false)
    private String fullname;

    @NotBlank(message = "Email is required")
    @Email(message = "Invaild email format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public User() {

    }

    public User(String username, String fullname, String email, Status status) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.status = status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public String getKeycloakId() {
    return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }
}
