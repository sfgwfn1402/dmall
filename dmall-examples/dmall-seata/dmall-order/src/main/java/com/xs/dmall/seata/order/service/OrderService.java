package com.xs.dmall.seata.order.service;


import com.xs.dmall.seata.order.domain.Order;

/**
 * @author Yang Hao
 * @date 2020-09-27 11:30
 */
public interface OrderService {
    void create(Order order);
}
