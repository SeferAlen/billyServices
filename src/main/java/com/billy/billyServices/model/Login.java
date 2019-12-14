package com.billy.billyServices.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Class for Login db entity
 */
@Entity
@Table(name = "login")
public class Login implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loginID")
    private UUID loginID;
    @NotNull
    @Column(name = "username")
    private String username;
    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Column(name = "password")
    private String password;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billy_userID")
    private BillyUser billyUser;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "login_roles",
            joinColumns = @JoinColumn(name = "loginID"),
            inverseJoinColumns = @JoinColumn(name = "roleID"))
    private Set<Role> roles;

    public Login() {
    }

    public Login(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }

    public Login(String username, @NotNull @Size(min = 8, message = "Password must be at least 8 characters long") String password, BillyUser billyUser) {
        this.username = username;
        this.password = password;
        this.billyUser = billyUser;
    }

    public UUID getLoginID() {
        return loginID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BillyUser getBillyUser() {
        return billyUser;
    }

    public void setBillyUser(BillyUser billyUser) {
        this.billyUser = billyUser;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
