package com.mf.id.springboot.starter.core;

import com.mf.id.springboot.starter.config.IdConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class IdGeneratorManager implements IdGeneratorService, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private IdConfig idConfig;


    @Override
    public long getId() {

        IdGeneratorService idGeneratorService = (IdGeneratorService) applicationContext.getBean(idConfig.getServiceType().getName());
        return idGeneratorService.getId();
    }

    @Override
    public boolean removeId() {
        IdGeneratorService idGeneratorService = (IdGeneratorService) applicationContext.getBean(idConfig.getServiceType().getName());
        return idGeneratorService.removeId();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
