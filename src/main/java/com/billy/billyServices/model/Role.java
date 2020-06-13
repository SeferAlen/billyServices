package com.billy.billyServices.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Class for Role db entity
 */
@Entity
@Table(name = "\"Role\"")
@org.hibernate.annotations.Immutable
public class Role implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "\"Role_ID\"")
    private UUID roleID;
    @NotNull
    @Column(name = "\"Name\"")
    private String name;
    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "roles"
    )
    private Set<Login> logins;

    /**
     * Instantiates a new Role.
     */
    public Role() {
    }

    /**
     * Instantiates a new Role.
     *
     * @param name the name
     */
    public Role(@NotNull String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
