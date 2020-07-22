package com.mercadolibre.nivel2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class CouponResponse extends Coupon{
    @JsonProperty("total")
    private Float total;

    public CouponResponse(Float total) {
        this.total = total;
    }

    public CouponResponse(ArrayList<String> itemIds, Float total) {
        super(itemIds);
        this.total = total;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
