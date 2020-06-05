package com.billy.billyServices.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Embeddable class for address representation
 */
@Embeddable
public class City {

    @NotNull
    @Column(name = "\"Name\"", nullable = false)
    private String name;

    @NotNull
    @Column(name = "\"Street\"", nullable = false) // nullable = false, because at this time Hibernate has a bug when using
    // @NotNull on embeddable - it only check runtime for bean validation, and do not create db NOT NULL constraint
    private String street;

    @NotNull
    @Column(name = "\"Zipcode\"", nullable = false, length = 5)
    private String zipcode;

    @NotNull
    @Column(name = "\"Country\"", nullable = false)
    private String country;

    /**
     * Instantiates a new City.
     */
    public City() {
    }

    /**
     * Instantiates a new City.
     *
     * @param name    the name
     * @param street  the street
     * @param zipcode the zipcode
     * @param country the country
     */
    public City(@NotNull String name, @NotNull String street, @NotNull String zipcode, @NotNull String country) {
        this.name = name;
        this.street = street;
        this.zipcode = zipcode;
        this.country = country;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
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
        City city = (City) o;
        return Objects.equals(name, city.name) &&
                Objects.equals(street, city.street) &&
                Objects.equals(zipcode, city.zipcode);
    }

    /**
     * Override hashCode
     *
     * @return {@link int} the int hashcode
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + zipcode.hashCode();
        return result;
    }
}
