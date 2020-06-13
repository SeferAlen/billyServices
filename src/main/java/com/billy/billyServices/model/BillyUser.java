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
@Table(name = "\"Billy_User\"")
public class BillyUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "\"Billy_user_ID\"")
    private UUID billy_userID;
    @NotNull
    @Column(name = "\"First_name\"")
    private String first_name;
    @NotNull
    @Column(name = "\"Last_name\"")
    private String last_name;
    private Address address;
    @NotNull
    @Column(name = "\"Phone\"")
    private String phone;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToOne(mappedBy = "billyUser")
    private Login login;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(
            mappedBy = "owner",
            cascade = CascadeType.ALL
    )
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
    public BillyUser(@NotNull String first_name, @NotNull String last_name, @NotNull Address address, @NotNull String phone) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Instantiates a new Billy user.
     *
     * @param billy_userID the billy user id
     * @param first_name   the first name
     * @param last_name    the last name
     * @param address      the address
     * @param phone        the phone
     */
    public BillyUser(UUID billy_userID, @NotNull String first_name, @NotNull String last_name, @NotNull Address address, @NotNull String phone) {
        this.billy_userID = billy_userID;
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
     * Sets billy user id.
     *
     * @param billy_userID the billy user id
     */
    public void setBilly_userID(UUID billy_userID) {
        this.billy_userID = billy_userID;
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
    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
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
