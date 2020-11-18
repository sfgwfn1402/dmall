package com.xs.dmall.dubbo.demo.service;

/**
 * @author Yang Hao
 * @date 2020-09-27 11:58
 */
public interface StorageService {
    void decrease(Long productId, Integer count);
}
