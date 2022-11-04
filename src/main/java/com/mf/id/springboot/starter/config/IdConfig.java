package com.mf.id.springboot.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(value = "spring.zid")
public class IdConfig {
    private String serverName = "id-generator";

    private long serverId = 1l;

    private String address;

    private String password;

    private int database = 15;

    /**
     *  the redis Serializable method
     */
    private String codec = "org.redisson.codec.JsonJacksonCodec";

    /**
     * if redis is cluster mode us the config
     */
    private ClusterServer clusterServer;

    @Data
    public static class ClusterServer {
        private String [] nodeAddress;
    }

}
