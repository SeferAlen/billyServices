package com.billy.billyServices.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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

    /**
     * Instantiates a new Billy user.
     */
    public BillyUser() {
    }

    /**
     * Instantiates a new Billy user.
     *
     * @param first_name the first name
     * @param last_name  the last name
     * @param address    the address
     * @param phone      the phone
     */
    public BillyUser(@NotNull String first_name, @NotNull String last_name, @NotNull String address, @NotNull String phone) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Gets billy user id.
     *
     * @return the billy user id
     */
    public UUID getBilly_userID() {
        return billy_userID;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Sets first name.
     *
     * @param first_name the first name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Sets last name.
     *
     * @param last_name the last name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public Login getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(Login login) {
        this.login = login;
    }
}
