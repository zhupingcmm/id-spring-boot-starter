package com.mf.id.springboot.starter.core.impl;

import com.mf.id.springboot.starter.core.IdGeneratorAbstract;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisIdGeneratorImpl extends IdGeneratorAbstract {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public long getId() {
        checkServerConfig();
        return mutateId(redissonClient.getAtomicLong(getName()).incrementAndGet());
    }

    @Override
    public boolean removeId() {
        return redissonClient.getAtomicLong(getName()).delete();
    }
}
