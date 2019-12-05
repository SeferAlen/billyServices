package com.billy.billyServices.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Class for Role db entity
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "roleID")
    private UUID roleID;
    @NotNull
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<Login> logins;

    public Role(@NotNull String name) {
        this.name = name;
    }
}
