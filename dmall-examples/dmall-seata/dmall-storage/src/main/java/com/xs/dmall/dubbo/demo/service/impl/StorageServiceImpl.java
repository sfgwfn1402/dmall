package com.xs.dmall.dubbo.demo.service.impl;

import com.xs.dmall.seata.dao.StorageDao;
import com.xs.dmall.dubbo.demo.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Yang Hao
 * @date 2020-09-27 11:58
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------------->storage-service 中扣减库存开始");
        storageDao.decrease(productId, count);
        log.info("------------->storage-service 中扣减库存结束");
    }

}
