package com.mercadolibre.nivel2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class CouponRequest extends Coupon{

    @JsonProperty("amount")
    private Float amount;

    public CouponRequest(Float amount) {
        this.amount = amount;
    }

    public CouponRequest(ArrayList<String> itemIds, Float amount) {
        super(itemIds);
        this.amount = amount;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
