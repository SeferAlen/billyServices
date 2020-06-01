package com.billy.billyServices.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Class for Login db entity
 */
@Entity
@Table(name = "\"Login\"")
public class Login implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "\"Login_ID\"")
    private UUID loginID;
    @NotNull
    @Column(name = "\"Username\"")
    private String username;
    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Column(name = "\"Password\"")
    private String password;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Billy_user\"")
    private BillyUser billyUser;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "\"Login_Role\"",
            joinColumns = @JoinColumn(name = "\"Login_ID\""),
            inverseJoinColumns = @JoinColumn(name = "\"Role_ID\""))
    private Set<Role> roles;

    /**
     * Instantiates a new Login.
     */
    public Login() {
    }

    /**
     * Instantiates a new Login.
     *
     * @param username the username
     * @param password the password
     */
    public Login(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Instantiates a new Login.
     *
     * @param username  the username
     * @param password  the password
     * @param billyUser the billy user
     */
    public Login(String username, @NotNull @Size(min = 8, message = "Password must be at least 8 characters long") String password, BillyUser billyUser) {
        this.username = username;
        this.password = password;
        this.billyUser = billyUser;
    }

    /**
     * Instantiates a new Login.
     *
     * @param username  the username
     * @param password  the password
     * @param billyUser the billy user
     * @param roles     the roles
     */
    public Login(@NotNull String username, @NotNull @Size(min = 8, message = "Password must be at least 8 characters long") String password, BillyUser billyUser, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.billyUser = billyUser;
        this.roles = roles;
    }

    /**
     * Gets login id.
     *
     * @return the login id
     */
    public UUID getLoginID() {
        return loginID;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets billy user.
     *
     * @return the billy user
     */
    public BillyUser getBillyUser() {
        return billyUser;
    }

    /**
     * Sets billy user.
     *
     * @param billyUser the billy user
     */
    public void setBillyUser(BillyUser billyUser) {
        this.billyUser = billyUser;
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
