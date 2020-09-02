package com.xs.dmall.java;

import com.xiaoleilu.hutool.http.HttpRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DmallJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmallJavaApplication.class, args);
        try {
            String a = "https://rsapistore.huijuer.com/call/api?rs_apistore_appid=app_invoice_lib&rs_apistore_timestamp=1592904637080&rs_apistore_token=727bc6c8db4bf82fd16838f30e35eadd044f3259&rs_apistore_code=/openApi/cust/invoiceInfo/add&orderNo=158814677800002&invoiceType=1&invoiceTitle=%E5%9B%9B%E5%B7%9D%E8%B1%A1%E5%8A%88%E5%8F%89%E7%94%B5%E5%AD%90%E5%95%86%E5%8A%A1%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8&taxpayerIdentifier=91510107MA67R5NF4C&custPhone=028-85429459&custAddr=%E5%9B%9B%E5%B7%9D%E7%9C%81%E6%88%90%E9%83%BD%E5%B8%82%E6%AD%A6%E4%BE%AF%E5%8C%BA%E6%BD%AE%E9%9F%B3%E8%B7%AF5%E5%8F%B7%E9%99%841%E5%8F%B7&custDepositBank=631858891&custBankAccount=中国民生银行股份有限公司成都蜀汉支行&receiveContact=%E6%B1%AA%E4%B8%B9&electronReceiveContact=%E6%B1%AA%E4%B8%B9&receivePhone=15928997006&electronReceivePhone=15928997006&receiveAddr=%E6%88%90%E9%83%BD%E5%B8%82%E6%AD%A6%E4%BE%AF%E5%8C%BA%E6%AD%A6%E5%85%B4%E5%9B%9B%E8%B7%AF166%E5%8F%B7%E8%A5%BF%E9%83%A8%E6%99%BA%E8%B0%B7D%E5%8C%BA1%E6%A0%8B1%E5%8D%95%E5%85%83301%E5%8F%B7&taxpayerProve=http://wtoip-prod-publish.oss-cn-hangzhou.aliyuncs.com/deal/2020/04/30/17/30/bf32daa3ee1a4546aaeaae3a5a11c9f5.png";
            String result = HttpRequest.get(a.replaceAll("\\s", "")).execute().body();
            System.out.println();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
