package com.mf.id.springboot.starter.config;

import com.mf.id.springboot.starter.model.ServiceType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(value = "spring.zid")
public class IdConfig {

    private String serverName = "id-generator";

    private long serverId = 1l;

    private ServiceType serviceType = ServiceType.REDIS;

    private Redis redis;

    private Zookeeper zookeeper;

    @Data
    public static class Redis {

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
    }


    public static class Zookeeper {

    }


    @Data
    public static class ClusterServer {
        private String [] nodeAddress;
    }

}
