package com.mercadolibre.nivel2.util;

import com.mercadolibre.nivel2.model.CouponRequest;
import com.mercadolibre.nivel2.model.CouponResponse;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class CouponUtil {

    public static Properties getPropertiesFile() {
        InputStream inputStream;
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            inputStream = CouponUtil.class.getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            return prop;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CouponResponse calculate (Map<String, Float> items, Float amount) {
        try{
            ArrayList<String> orderList = (ArrayList) orderMap(items);
            CouponResponse couponResponse = new CouponResponse(new ArrayList<String>(), (float) 0);
            for(int i = 0; i < orderList.size(); i++) {
                if (items.get(orderList.get(i))>amount){
                    continue;
                }else {
                    CouponResponse couponResponseTmp = new CouponResponse(new ArrayList<String>(), (float) 0);
                    couponResponseTmp.getItemIds().add(orderList.get(i));
                    couponResponseTmp.setTotal(couponResponseTmp.getTotal()+items.get(orderList.get(i)));
                    for (int j = 0; j < orderList.size(); j++) {
                        if (j!=i){
                            if( couponResponseTmp.getTotal() + items.get(orderList.get(j)) <= amount) {
                                couponResponseTmp.getItemIds().add(orderList.get(j));
                                couponResponseTmp.setTotal(couponResponseTmp.getTotal()+items.get(orderList.get(j)));
                            }
                        }
                    }
                    if(couponResponseTmp.getTotal()> couponResponse.getTotal()){
                        couponResponse = couponResponseTmp;
                    }
                }
            }
            return couponResponse;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static List orderMap(Map<String, Float> items){
        try {
            Iterator iterator = items.entrySet().iterator();
            ArrayList orderList = new ArrayList();
            while (iterator.hasNext()){
                Map.Entry entry = (Map.Entry)iterator.next();
                orderList.add(entry.getKey());
            }
            Collections.sort(orderList);
            return orderList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
