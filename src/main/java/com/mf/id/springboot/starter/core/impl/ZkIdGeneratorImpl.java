package com.mf.id.springboot.starter.core.impl;

import com.mf.id.springboot.starter.core.IdGeneratorAbstract;

public class ZkIdGeneratorImpl extends IdGeneratorAbstract {


    @Override
    public long getId() {
        return 0;
    }

    @Override
    public boolean removeId() {
        return false;
    }
}
