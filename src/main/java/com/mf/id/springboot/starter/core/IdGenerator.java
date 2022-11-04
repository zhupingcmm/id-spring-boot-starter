package com.mf.id.springboot.starter.core;

import com.mf.id.springboot.starter.config.IdConfig;
import com.mf.id.springboot.starter.exception.IdException;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IdGenerator {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");
    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private IdConfig idConfig;

    public long getId () {
        checkServerConfig();

        LocalDateTime now = LocalDateTime.now();
        String dateTime = dateTimeFormatter.format(now);
        long value = redissonClient.getAtomicLong(getName()).incrementAndGet();
        if (value >= 1000) {
            value = value % 1000;
        }
        String seq = StringUtils.leftPad(Long.toString(value), 3, "0");

        // 业务组件id + 时间戳 + 序列号
        String result = idConfig.getServerId() +  dateTime + seq;

       return Long.parseLong(result);
    }

    private void checkServerConfig () {
        String serverName = idConfig.getServerName();
        if (StringUtils.isBlank(serverName)) {
            throw new IdException("");
        }
    }

    private String getName() {
        return idConfig.getServerName() + "-" + idConfig.getServerId();
    }
}
