package com.xs.dmall.java.amount;

import com.alibaba.fastjson.util.ThreadLocalCache;

import java.math.BigDecimal;

public class AmountTest {

    public static void main(String[] args){
        boolean f = false;
        BigDecimal zstAmount = BigDecimal.ZERO;
        BigDecimal count = new BigDecimal(15);
        BigDecimal totalAmount = BigDecimalUtils.safeAdd(new BigDecimal(3585),new BigDecimal(4000),new BigDecimal(0));
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
