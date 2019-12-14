package com.billy.billyServices.model;

import com.fasterxml.jackson.annotation.JsonInclude;

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
@Table(name = "billy_user")
public class BillyUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "billy_userID")
    private UUID billy_userID;
    @NotNull
    @Column(name = "first_name")
    private String first_name;
    @NotNull
    @Column(name = "last_name")
    private String last_name;
    @NotNull
    @Column(name = "address")
    private String address;
    @NotNull
    @Column(name = "phone")
    private String phone;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToOne(mappedBy = "billyUser")
    private Login login;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Bill> bills;

    public BillyUser() {
    }

    public BillyUser(@NotNull String first_name, @NotNull String last_name, @NotNull String address, @NotNull String phone) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.phone = phone;
    }

    public UUID getBilly_userID() {
        return billy_userID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
