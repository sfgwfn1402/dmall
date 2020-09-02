package com.xs.dmall.java.amount;

//import com.google.common.base.Optional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 计算工具类
 * @author chengshuosheng
 * @version 2.0, 2017/8/17
 * @since 2.0
 */
public class BigDecimalUtils {
    private static int ROUND_HALF_UP = BigDecimal.ROUND_HALF_UP;//四舍五入

    /**
     * 保留整数格式
     */
    public static final DecimalFormat decimalFormat_integer = new DecimalFormat("#");

    private BigDecimalUtils(){}
    /**
     * 将数字字符串转化为BigDecimal
     *
     * @param str 数字字符串
     *
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimalForStr(String str){
        return new BigDecimal(str);
    }

    /**
     * 将数字字符串转化为double,并保留指定小数位
     *
     * @param str 数字字符串
     * @param scale 指定要保留的小数位(可为空)
     *
     * @return double
     */
    public static double getBigDecimalForStrReturnDouble(String str,Integer scale){
        BigDecimal one = getBigDecimalForStr(str);
        if(null != scale){
            return one.setScale(scale, ROUND_HALF_UP).doubleValue();
        }
        return one.doubleValue();
    }

    /**
     * 将double类型数字转化为BigDecimal
     *
     * @param str 数字字符串
     *
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimalForDouble(double one){
        return getBigDecimalForStr(one + "");
    }

    /**
     * 获取指定小数位的double
     *
     * @param one 数字
     *
     * @return double
     */
    public static double getScaleDouble(double one,Integer scale){
        return getBigDecimalForStrReturnDouble(one + "", scale);
    }

    /**
     * 获取2位小数点的double
     *
     * @param one 数字
     * @return double
     */
    public static double getScaleDouble(double one){
        return getScaleDouble(one, 2);
    }

    /**
     * 获取货币格式化字符串(￥#.###.##)
     *
     * @param one 数字
     *
     * @return String
     */
    public static String getCurrencyFormat(BigDecimal one){
        NumberFormat currency = NumberFormat.getCurrencyInstance(); //建立货币格式化引用
        return currency.format(one);
    }

    /**
     * 获取货币格式化字符串(￥#.###.##)
     *
     * @param one 数字
     *
     * @return String
     */
    public static String getCurrencyFormat(double one){
        return getCurrencyFormat(getBigDecimalForStr(one + ""));
    }

    /**
     * 两个BigDecimal数字相加
     *
     * @param one 第一个数字
     * @param two 第二个数字
     *
     * @return BigDecimal
     */
    public static BigDecimal add(BigDecimal one,BigDecimal two){
        return one.add(two);//相加
    }

    /**
     * 两个数字字符串相加
     *
     * @param oneNumber 第一个数字字符串
     * @param twoNumber 第二个数字字符串
     *
     * @return BigDecimal
     */
    public static BigDecimal add(String oneNumber,String twoNumber){
        BigDecimal one = new BigDecimal(oneNumber);
        BigDecimal two = new BigDecimal(twoNumber);
        return add(one, two);
    }

    /**
     * 两个double数字相加
     *
     * @param oneNumber 第一个数字
     * @param twoNumber 第二个数字
     *
     * @return BigDecimal
     */
    public static BigDecimal add(double oneNumber,double twoNumber){
        return add(oneNumber + "", twoNumber + "");
    }

    /**
     * 两个double数字相加并保留指定小数位
     *
     * @param one 第一个数字
     * @param two 第二个数字
     * @param scale 指定要保留的小数位(可为空)
     *
     * @return BigDecimal
     */
    public static double add(double one,double two,Integer scale){
        BigDecimal b = add(one, two);
        if(null != scale){
            return b.setScale(scale, ROUND_HALF_UP).doubleValue();
        }
        return b.doubleValue();
    }

    /**
     * 两个数字字符串相加并保留指定小数位
     *
     * @param oneNumber 第一个数字字符串
     * @param twoNumber 第二个数字字符串
     * @param scale 指定要保留的小数位(可为空)
     *
     * @return double
     */
    public static double addReturnDouble(String oneNumber,String twoNumber,Integer scale){
        BigDecimal b = add(oneNumber, twoNumber);
        if(null != scale){
            return b.setScale(scale, ROUND_HALF_UP).doubleValue();
        }
        return b.doubleValue();
    }

    /**
     * 两个BigDecimal数字相减
     *
     * @param one 第一个数
     * @param two 第二个数
     * @param scale 指定小数位
     *
     * @return BigDecimal
     */
    public static BigDecimal subtract(BigDecimal one,BigDecimal two){
        return one.subtract(two);//相减
    }

    /**
     * 两个BigDecimal数字相减并保留指定小数位
     *
     * @param one 第一个数
     * @param two 第二个数
     * @param scale 指定小数位(可为空)
     *
     * @return double
     */
    public static double subtractReturnDouble(BigDecimal one,BigDecimal two,Integer scale){
        BigDecimal b = subtract(one, two);
        if(null != scale){
            return b.setScale(scale, ROUND_HALF_UP).doubleValue();
        }
        return b.doubleValue();
    }

    /**
     * 两个数字字符串相减
     *
     * @param oneNumber 第一个数字字符串
     * @param twoNumber 第二个数字字符串
     * @param scale 指定小数位
     *
     * @return BigDecimal
     */
    public static BigDecimal subtract(String oneNumber,String twoNumber){
        BigDecimal one = new BigDecimal(oneNumber);
        BigDecimal two = new BigDecimal(twoNumber);
        return subtract(one, two);
    }

    /**
     * 两个数字相减并保留指定小数位
     *
     * @param oneNumber 第一个数字
     * @param twoNumber 第二个数字
     * @param scale 指定小数位(可为空)
     *
     * @return double
     */
    public static double subtractReturnDouble(double oneNumber,double twoNumber,Integer scale){
        BigDecimal one = new BigDecimal(oneNumber + "");
        BigDecimal two = new BigDecimal(twoNumber + "");
        return subtractReturnDouble(one, two, scale);
    }
    /**
     * BigDecimal的加法运算封装
     * @param b1
     * @param bn
     * @return
     */
    public static BigDecimal safeAdd(BigDecimal b1, BigDecimal... bn) {
        if (null == b1) {
            b1 = BigDecimal.ZERO;
        }
        if (null != bn) {
            for (BigDecimal b : bn) {
                b1 = b1.add(null == b ? BigDecimal.ZERO : b);
            }
        }
        return b1;
    }

    /**
     * Integer加法运算的封装
     * @param b1   第一个数
     * @param bn   需要加的加法数组
     * @注 ： Optional  是属于com.google.common.base.Optional<T> 下面的class
     * @return
     */
//    public static Integer safeAdd(Integer b1, Integer... bn) {
//        if (null == b1) {
//            b1 = 0;
//        }
//        Integer r = b1;
//        if (null != bn) {
//            for (Integer b : bn) {
//                r += Optional.fromNullable(b).or(0);
//            }
//        }
//        return r > 0 ? r : 0;
//    }

    /**
     * 计算金额方法
     * @param b1
     * @param bn
     * @return
     */
    public static BigDecimal safeSubtract(BigDecimal b1, BigDecimal... bn) {
        return safeSubtract(true, b1, bn);
    }

    /**
     * BigDecimal的安全减法运算
     * @param isZero  减法结果为负数时是否返回0，true是返回0（金额计算时使用），false是返回负数结果
     * @param b1        被减数
     * @param bn        需要减的减数数组
     * @return
     */
    public static BigDecimal safeSubtract(Boolean isZero, BigDecimal b1, BigDecimal... bn) {
        if (null == b1) {
            b1 = BigDecimal.ZERO;
        }
        BigDecimal r = b1;
        if (null != bn) {
            for (BigDecimal b : bn) {
                r = r.subtract((null == b ? BigDecimal.ZERO : b));
            }
        }
        return isZero ? (r.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : r) : r;
    }

    /**
     * 整型的减法运算，小于0时返回0
     * @param b1
     * @param bn
     * @return
     */
//    public static Integer safeSubtract(Integer b1, Integer... bn) {
//        if (null == b1) {
//            b1 = 0;
//        }
//        Integer r = b1;
//        if (null != bn) {
//            for (Integer b : bn) {
//                r -= Optional.fromNullable(b).or(0);
//            }
//        }
//        return null != r && r > 0 ? r : 0;
//    }

    /**
     * 金额除法计算，返回2位小数（具体的返回多少位大家自己看着改吧）
     * @param b1
     * @param b2
     * @return
     */
    public static <T extends Number> BigDecimal safeDivide(T b1, T b2){
        return safeDivide(b1, b2, BigDecimal.ZERO);
    }

    /**
     * BigDecimal的除法运算封装，如果除数或者被除数为0，返回默认值
     * 默认返回小数位后2位，用于金额计算
     * @param b1
     * @param b2
     * @param defaultValue
     * @return
     */
    public static <T extends Number> BigDecimal safeDivide(T b1, T b2, BigDecimal defaultValue) {
        if (null == b1 || null == b2) {
            return defaultValue;
        }
        try {
            return BigDecimal.valueOf(b1.doubleValue()).divide(BigDecimal.valueOf(b2.doubleValue()), 2, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * BigDecimal的乘法运算封装
     * @param b1
     * @param b2
     * @return
     */
    public static <T extends Number> BigDecimal safeMultiply(T b1, T b2) {
        if (null == b1 || null == b2) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(b1.doubleValue()).multiply(BigDecimal.valueOf(b2.doubleValue()));
    }

    /**
     * 金额格式化,不做四舍五入处理
     *@author: huangshipiao
     *@version 2.0
     *@since 2.0
     *@date: 2017年10月15日 下午2:31:37
     *@param value
     *@param decimalFormat
     *@return
     */
    public static String bigDecimalFormat(BigDecimal value,DecimalFormat decimalFormat) {
    	decimalFormat.setRoundingMode(RoundingMode.FLOOR);
    	 return decimalFormat.format(value);
    }


    public static BigDecimal isNullDefaultValue(BigDecimal big, BigDecimal defaultValue) {
        if(java.util.Objects.nonNull(big)){
            return big;
        }
        return defaultValue;
    }

    public static BigDecimal isZeroDefaultValue(BigDecimal big, BigDecimal defaultValue) {
        if(java.util.Objects.nonNull(big) &&
                big.compareTo(BigDecimal.ZERO) == 0) {
            return defaultValue;
        }
        return big;
    }
}
