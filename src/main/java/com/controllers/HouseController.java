package com.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.util.Calculate;
import com.pojo.vo.PayInfo;
import com.reposity.mysql.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HouseController {

    private static final Logger logger = LoggerFactory.getLogger(HouseController.class);

    private final static double Commerical_Rate = 0.049;
    private final static double Fund_Rate = 0.0325;


    @PostMapping("/api/house")
    @ResponseBody
    public JSONObject apiHouse(String type, @RequestBody JSONObject jsonObject) {
        logger.info("json:{}", JSON.toJSONString(jsonObject));
        Double price = jsonObject.getDouble("price");
        Double area = jsonObject.getDouble("area");
        Double percentage = jsonObject.getDouble("percentage") / 100;
        Integer year = jsonObject.getInteger("year");
        Double rate = jsonObject.getDouble("rate");
        Double businessRate = jsonObject.getDouble("businessRate");
        Double fundTotal = jsonObject.getDouble("fundTotal");
        Double fundTotalM = jsonObject.getDouble("fundTotalM");
        Double businessTotal = jsonObject.getDouble("businessTotal");
        Integer fundYear = jsonObject.getInteger("fundYear");
        Integer businessYear = jsonObject.getInteger("businessYear");
        PayInfo payInfo = new PayInfo();
        if (type.equals("1")) {
            if (jsonObject.getString("type").equals("等额本金")) {
                payInfo = Calculate.calculateEqualPrincipal(area * price * percentage,
                        year * 12, rate * Commerical_Rate, 1);
            }
            if (jsonObject.getString("type").equals("等额本息")) {
                payInfo = Calculate.calculateEqualPrincipalAndInterest(area * price * percentage,
                        year * 12, rate * Commerical_Rate, 1);
            }
        }
        if (type.equals("2")) {
            if (jsonObject.getString("type").equals("等额本金")) {
                payInfo = Calculate.calculateEqualPrincipal(fundTotal * 10000,
                        year * 12, Fund_Rate, 1);
            }
            if (jsonObject.getString("type").equals("等额本息")) {
                payInfo = Calculate.calculateEqualPrincipalAndInterest(fundTotal * 10000,
                        year * 12, Fund_Rate, 1);
            }
        }
        if (type.equals("3")) {
            PayInfo payInfo1 = null;
            if (jsonObject.getString("fundType").equals("等额本金")) {
                payInfo1 = Calculate.calculateEqualPrincipal(fundTotalM * 10000,
                        fundYear * 12, Fund_Rate, 1);
            }
            if (jsonObject.getString("fundType").equals("等额本息")) {
                payInfo1 = Calculate.calculateEqualPrincipalAndInterest(fundTotalM * 10000,
                        fundYear * 12, Fund_Rate, 1);
            }
            PayInfo payInfo2 = null;
            if (jsonObject.getString("businessType").equals("等额本金")) {
                payInfo2 = Calculate.calculateEqualPrincipal(businessTotal * 10000,
                        businessYear * 12, businessRate * Commerical_Rate, 1);
            }
            if (jsonObject.getString("businessType").equals("等额本息")) {
                payInfo2 = Calculate.calculateEqualPrincipalAndInterest(businessTotal * 10000,
                        businessYear * 12, businessRate * Commerical_Rate, 1);
            }
            payInfo.setPayPerMonth(payInfo1.getPayPerMonth() + payInfo2.getPayPerMonth());
            payInfo.setTotal(payInfo1.getTotal() + payInfo2.getTotal());
        }
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("总共还款额", payInfo.getTotal().intValue());
        jsonObject1.put("首月还款额", payInfo.getPayPerMonth().intValue());
        return jsonObject1;
    }


}
