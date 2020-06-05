package com.billy.billyServices.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Embeddable class for address representation
 */
@Embeddable
public class Address {

    @NotNull
    @Column(name = "\"Owner_name\"", nullable = false) // nullable = false, because at this time Hibernate has a bug when using
    // @NotNull on embeddable - it only check runtime for bean validation, and do not create db NOT NULL constraint
    private String ownerName;

    @NotNull
    @AttributeOverrides(
            @AttributeOverride(
                    name = "\"Name\"",
                    column = @Column(name = "\"City\"", nullable = false)
            )
    )
    private City city;

    /**
     * Instantiates a new Address.
     */
    public Address() {
    }

    /**
     * Instantiates a new Address.
     *
     * @param ownerName the owner name
     * @param city      the city
     */
    public Address(@NotNull String ownerName, @NotNull City city) {
        this.ownerName = ownerName;
        this.city = city;
    }

    /**
     * Gets owner name.
     *
     * @return the owner name
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Sets owner name.
     *
     * @param ownerName the owner name
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public City getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Override equals
     *
     * @param o {@link Object} the object
     * @return {@link boolean} the boolean representing equality of {link Object} o with this
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(ownerName, address.ownerName) &&
                Objects.equals(city, address.city);
    }

    /**
     * Override hashCode
     *
     * @return {@link int} the int hashcode
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + ownerName.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }
}
