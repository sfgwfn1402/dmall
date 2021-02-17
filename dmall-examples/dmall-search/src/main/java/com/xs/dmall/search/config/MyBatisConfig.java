package com.xs.dmall.search.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis相关配置
 * Created by macro on 2019/4/8.
 */
@Configuration
@MapperScan({"com.xs.dmall.mapper","com.xs.dmall.search.dao"})
public class MyBatisConfig {
}
