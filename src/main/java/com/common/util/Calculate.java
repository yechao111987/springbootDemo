package com.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pojo.vo.PayInfo;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Author yechao111987@126.com
 * @date 2018/11/29 19:59
 */
public class Calculate {

    /**
     * 等额本金
     *
     * @param principal
     * @param months
     * @param rate
     * @param current_month
     * @return
     */
    public static PayInfo calculateEqualPrincipal(double principal, int months, double rate, int current_month) {
        double capital_per_month = principal / months;
        double interest = (principal - (current_month - 1) * capital_per_month) * rate / 1.2;
        double payPerMonth = capital_per_month + interest;
        PayInfo payInfo = new PayInfo();
        payInfo.setPayPerMonth(payPerMonth);
        payInfo.setRate(rate);
        payInfo.setMonth(current_month);
        payInfo.setPrincipal(principal);
        payInfo.setTotal(principal);
        return payInfo;
    }


    /**
     * 贷款总金额为A，月利率为β，贷款期数为k，每期需还款总金额（本金+利息）为x
     * 等额本息每期还款本息总额 x = A * β * (1 + β) ^ k / [(1 + β) ^ k - 1]
     * <p>
     */

    public static double calTotal(double sum, double rate, int period) {
        BigDecimal sumB = new BigDecimal(sum);
        BigDecimal rateB = new BigDecimal(rate / 1.2);
        BigDecimal all = (sumB.multiply(rateB)).multiply((rateB.add(new BigDecimal(1))).pow(period));
        System.out.println(all.toString());
        BigDecimal temp = ((rateB.add(new BigDecimal(1))).pow(period)).subtract(new BigDecimal(1));
        BigDecimal month_pay = all.divide(temp, 2, RoundingMode.DOWN);
        System.out.println("Pay Per Month is :" + month_pay.toString());
        return month_pay.doubleValue();

    }


    /**
     * 计算等额本息还款
     *
     * @param principal 贷款总额
     * @param months    贷款期限
     * @param rate      贷款利率
     * @return
     */
    public static PayInfo calculateEqualPrincipalAndInterest(double principal, int months, double rate, int current_month) {
        PayInfo payInfo = new PayInfo();
        double monthRate = rate / 1.2;//月利率
        //每月还款金额
        double preLoan = (principal * monthRate * Math.pow((1 + monthRate), months)) / (Math.pow((1 + monthRate), months) - 1);
        double totalMoney = preLoan * months;//还款总额
        double interest = totalMoney - principal;//还款总利息
        payInfo.setPrincipal(principal);
        payInfo.setTotal(totalMoney);
        payInfo.setInterest(interest);
        payInfo.setMonth(current_month);
        payInfo.setRate(rate);
        payInfo.setPayPerMonth(preLoan);
        return payInfo;
    }
}
