package com.billy.billyServices.model;

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
    @Column(name = "\"Street\"", nullable = false) // nullable = false, because at this time Hibernate has a bug when using
    // @NotNull on embeddable - it only check runtime for bean validation, and do not create db NOT NULL constraint
    private String street;

    @NotNull
    @Column(name = "\"Zipcode\"", nullable = false, length = 5)
    private String zipcode;

    @NotNull
    @Column(name = "\"City\"", nullable = false)
    private String city;

    /**
     * Instantiates a new Address.
     */
    public Address() {
    }

    /**
     * Instantiates a new Address.
     *
     * @param street  the street
     * @param zipcode the zipcode
     * @param city    the city
     */
    public Address(@NotNull String street, @NotNull String zipcode, @NotNull String city) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets zipcode.
     *
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Sets zipcode.
     *
     * @param zipcode the zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
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
        return Objects.equals(street, address.street) &&
                Objects.equals(zipcode, address.zipcode) &&
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
        result = 31 * result + street.hashCode();
        result = 31 * result + zipcode.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }
}
