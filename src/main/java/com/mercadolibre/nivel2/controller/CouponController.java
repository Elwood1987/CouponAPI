package com.mercadolibre.nivel2.controller;

import com.mercadolibre.nivel2.model.CouponRequest;
import com.mercadolibre.nivel2.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller

public class CouponController {

    @Autowired
    CouponService couponService;

    @PostMapping(value = "/coupon", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity calculate(@RequestBody CouponRequest couponRequest){
        return couponService.calculate(couponRequest);
    }

}
