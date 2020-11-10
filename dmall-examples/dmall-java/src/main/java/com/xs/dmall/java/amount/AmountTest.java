package com.xs.dmall.java.amount;


import java.math.BigDecimal;

public class AmountTest {

    public static void main(String[] args){
        boolean f = false;
        BigDecimal zstAmount = BigDecimal.ZERO;
        BigDecimal count = new BigDecimal(2);
        BigDecimal totalAmount = BigDecimalUtils.safeAdd(new BigDecimal(318.8),new BigDecimal(317),new BigDecimal(0));
        BigDecimal modAmount = totalAmount.divideAndRemainder(count)[1];
        // 整数
        zstAmount = totalAmount.subtract(modAmount).divide((count),2,BigDecimal.ROUND_HALF_UP);
        if(!(modAmount.compareTo(BigDecimal.ZERO) == 0) && !f){
            zstAmount = zstAmount.add(modAmount);
            f = true;
        }
        System.out.println(zstAmount);
    }
}
