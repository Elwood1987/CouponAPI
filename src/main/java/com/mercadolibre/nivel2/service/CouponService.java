package com.mercadolibre.nivel2.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mercadolibre.nivel2.model.CouponRequest;
import com.mercadolibre.nivel2.model.CouponResponse;
import com.mercadolibre.nivel2.util.CouponUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class CouponService {
    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate rest() {
        return new RestTemplate();
    }

    public ResponseEntity calculate(CouponRequest couponRequest){
        try {
            Properties prop = CouponUtil.getPropertiesFile();
            String url = prop.getProperty("URL_API_ITEM");
            Map<String,Float> mapItems = getMapItemsPrices(couponRequest,url);
            if (mapItems.size()>0) {
                CouponResponse response = CouponUtil.calculate(mapItems, couponRequest.getAmount());
                if (response.getTotal()== (float) 0){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND.value() + "-" +HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
                }else {
                    return new ResponseEntity<CouponResponse>(response, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND.value() + "-" +HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.value() + "-" +HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    public Map getMapItemsPrices(CouponRequest couponRequest, String url){
        try {
            Map<String, Float> mapItems = new HashMap<>();
            for (int i = 0; i < couponRequest.getItemIds().size(); i++){
                try {
                    ResponseEntity<String> result = restTemplate.getForEntity( url + couponRequest.getItemIds().get(i) , String.class);
                    String responseStr = result.getBody();
                    responseStr = responseStr.substring( responseStr.indexOf("{"), responseStr.lastIndexOf("}") + 1);
                    JsonObject jsonObject = new JsonParser().parse(responseStr).getAsJsonObject();
                    mapItems.put((String) couponRequest.getItemIds().get(i),jsonObject.get("price").getAsFloat());
                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
                    continue;
                }
            }
            return  mapItems;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



}
