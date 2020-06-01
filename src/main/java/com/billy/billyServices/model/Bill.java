package com.billy.billyServices.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Class for Bill db entity
 */
@Entity
@Table(name = "\"Bill\"")
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "\"Bill_ID\"")
    private UUID billID;
    @NotNull
    @Column(name = "\"Total\"")
    private float total;
    @NotNull
    @JsonFormat(pattern="yyyy-MM")
    @Column(name = "\"Date\"")
    private Date date;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Owner\"")
    private BillyUser owner;

    /**
     * Instantiates a new Bill.
     */
    public Bill() {
    }

    /**
     * Instantiates a new Bill.
     *
     * @param total the total
     * @param date  the date
     */
    public Bill(@NotNull float total, @NotNull Date date) {
        this.total = total;
        this.date = date;
    }

    /**
     * Instantiates a new Bill.
     *
     * @param total the total
     * @param date  the date
     * @param owner the owner
     */
    public Bill(@NotNull float total, @NotNull Date date, @NotNull BillyUser owner) {
        this.total = total;
        this.date = date;
        this.owner = owner;
    }

    /**
     * Gets bill id.
     *
     * @return the bill id
     */
    public UUID getBillID() {
        return billID;
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * Sets total.
     *
     * @param total the total
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public BillyUser getOwner() {
        return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(BillyUser owner) {
        this.owner = owner;
    }
}
