package com.mercadolibre.nivel2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Coupon {
    @JsonProperty("item_ids")
    private ArrayList<String> itemIds;

    public Coupon() {
        this.itemIds = new ArrayList<String>();
    }

    public Coupon(ArrayList<String> itemIds) {
        this.itemIds = itemIds;
    }

    public ArrayList<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(ArrayList<String> itemIds) {
        this.itemIds = itemIds;
    }
}
