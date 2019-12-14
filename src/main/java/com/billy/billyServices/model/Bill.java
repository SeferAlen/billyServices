package com.billy.billyServices.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Class for messages
 */
@Entity
@Table(name = "bill")
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "billID")
    private UUID billID;
    @NotNull
    @Column(name = "total")
    private float total;
    @NotNull
    @JsonFormat(pattern="yyyy-MM")
    @Column(name = "date")
    private Date date;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private BillyUser owner;

    public Bill() {
    }

    public Bill(@NotNull float total, @NotNull Date date) {
        this.total = total;
        this.date = date;
    }

    public Bill(@NotNull float total, @NotNull Date date, @NotNull BillyUser owner) {
        this.total = total;
        this.date = date;
        this.owner = owner;
    }

    public UUID getBillID() {
        return billID;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BillyUser getOwner() {
        return owner;
    }

    public void setOwner(BillyUser owner) {
        this.owner = owner;
    }
}
