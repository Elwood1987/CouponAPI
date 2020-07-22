package com.mercadolibre.nivel2;

import com.mercadolibre.nivel2.controller.CouponController;
import com.mercadolibre.nivel2.model.Coupon;
import com.mercadolibre.nivel2.model.CouponRequest;
import com.mercadolibre.nivel2.model.CouponResponse;
import com.mercadolibre.nivel2.service.CouponService;
import com.mercadolibre.nivel2.util.CouponUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class Nivel2ApplicationTests {

    @Autowired
    ApplicationContext ac;

    @Test
    void contextLoads() {
        CouponService couponService = ac.getBean(CouponService.class);
        assert (couponService instanceof CouponService);

        CouponController couponController = ac.getBean(CouponController.class);
        assert (couponController instanceof CouponController);

        Coupon coupon = new Coupon();
        assert (coupon instanceof Coupon);
        coupon.setItemIds(new ArrayList<String>());

        CouponRequest couponRequest = new CouponRequest((float) 0);
        assert (couponRequest instanceof CouponRequest);

        CouponRequest couponRequest1= new CouponRequest(new ArrayList<String>(), (float) 0);
        assert (couponRequest1 instanceof CouponRequest);
        couponRequest.setAmount((float) 10);

        CouponResponse couponResponse = new CouponResponse(new ArrayList<String>(), (float) 0);
        assert (couponResponse instanceof CouponResponse);

        CouponResponse couponResponse1 = new CouponResponse((float) 0);
        assert (couponResponse1 instanceof CouponResponse);
        couponResponse.setTotal((float) 10);
    }

    @Test
    void couponServiceTestOK() {
        CouponService couponService = ac.getBean(CouponService.class);
        assert (couponService instanceof CouponService);
        CouponRequest couponRequest = new CouponRequest(new ArrayList<String>(), (float) 500000);
        couponRequest.getItemIds().add("MCO498011211");
        couponRequest.getItemIds().add("MCO458208093");
        couponRequest.getItemIds().add("MCO457953350");
        couponRequest.getItemIds().add("MCO458209042");
        couponRequest.getItemIds().add("MCO558174274");
        couponService.calculate(couponRequest);
    }

    @Test
    void couponServiceTestOError() {
        CouponService couponService = ac.getBean(CouponService.class);
        assert (couponService instanceof CouponService);
        CouponRequest couponRequest = new CouponRequest(new ArrayList<String>(), (float) 1000);
        couponService.calculate(couponRequest);
    }

    @Test
    void couponServiceTestOException() {
        CouponService couponService = ac.getBean(CouponService.class);
        assert (couponService instanceof CouponService);
        CouponRequest couponRequest = new CouponRequest(null, (float) 1000);
        couponService.calculate(couponRequest);
    }

    @Test
    void couponServiceTestOException2() {
        CouponService couponService = ac.getBean(CouponService.class);
        assert (couponService instanceof CouponService);
        CouponRequest couponRequest = new CouponRequest(new ArrayList<String>(), (float) 1000);
        couponRequest.getItemIds().add("MCO-498011211");
        couponService.calculate(couponRequest);
    }

    @Test
    void couponControllerTest(){
        CouponController couponController = ac.getBean(CouponController.class);
        assert (couponController instanceof CouponController);
        CouponRequest couponRequest = new CouponRequest(new ArrayList<String>(), (float) 1000);
        couponRequest.getItemIds().add("MCO498011211");
        couponController.calculate(couponRequest);
    }

    @Test
    void couponUtilCalculateTest(){
        CouponUtil couponUtil = new CouponUtil();
        assert (couponUtil instanceof CouponUtil);
        Map m = new HashMap();
        m.put("MLA1", (float) 100);
        m.put("MLA2", (float) 210);
        m.put("MLA3",  (float) 260);
        m.put("MLA4", (float) 80);
        m.put("MLA5", (float) 90);
        CouponUtil.calculate(m, (float) 500);
    }

    @Test
    void couponUtilCalculateErrorTest(){
        CouponUtil couponUtil = new CouponUtil();
        assert (couponUtil instanceof CouponUtil);
        Map m = null;
        CouponUtil.calculate(m, (float) 500);
    }

    @Test
    void couponUtilPropertiesTest(){
        CouponUtil couponUtil = new CouponUtil();
        assert (couponUtil instanceof CouponUtil);
        CouponUtil.getPropertiesFile();
    }

    @Test
    void couponUtilOrderMapTest(){
        CouponUtil couponUtil = new CouponUtil();
        assert (couponUtil instanceof CouponUtil);
        Map m = new HashMap();
        m.put("MLA1", (float) 100);
        m.put("MLA2", (float) 210);
        m.put("MLA3",  (float) 260);
        m.put("MLA4", (float) 80);
        m.put("MLA5", (float) 90);
        CouponUtil.orderMap(m);
    }

    @Test
    void couponUtilOrderMapErrorTest(){
        CouponUtil couponUtil = new CouponUtil();
        assert (couponUtil instanceof CouponUtil);
        Map m = null;

        CouponUtil.orderMap(m);
    }
}
