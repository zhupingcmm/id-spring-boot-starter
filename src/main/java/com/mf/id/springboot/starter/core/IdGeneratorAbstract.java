package com.mf.id.springboot.starter.core;

import com.mf.id.springboot.starter.config.IdConfig;
import com.mf.id.springboot.starter.exception.IdException;
import com.mf.id.springboot.starter.util.TimeUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class IdGeneratorAbstract implements IdGeneratorService {

    @Autowired
    private IdConfig idConfig;


    public String getName() {
        return idConfig.getServerName() + "-" + idConfig.getServerId();
    }


    public void checkServerConfig () {
        String serverName = idConfig.getServerName();
        if (StringUtils.isBlank(serverName)) {
            throw new IdException("");
        }
    }

    public long mutateId(long value) {
        String dateTime = TimeUtils.getDateTime();
        if (value >= 1000) {
            value = value % 1000;
        }
        String seq = StringUtils.leftPad(Long.toString(value), 3, "0");

        // 业务组件id + 时间戳 + 序列号
        String result = idConfig.getServerId() +  dateTime + seq;

        return Long.parseLong(result);
    }

}
