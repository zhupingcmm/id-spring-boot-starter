package com.mf.id.springboot.starter;

import com.mf.id.springboot.starter.config.IdConfig;
import com.mf.id.springboot.starter.core.IdGenerator;
import com.mf.id.springboot.starter.exception.IdException;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "spring.zid", name = "enable", havingValue = "true", matchIfMissing = true)
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(IdConfig.class)
public class IdAutoConfiguration {

    @Autowired
    private IdConfig idConfig;

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    public RedissonClient redissonClient () {
        try {
            Config config = new Config();
            if (idConfig.getClusterServer() != null) {
                config.useClusterServers()
                        .setPassword(idConfig.getPassword())
                        .addNodeAddress(idConfig.getClusterServer().getNodeAddress());
            } else {
                config.useSingleServer()
                        .setAddress(idConfig.getAddress())
                        .setDatabase(idConfig.getDatabase())
                        .setPassword(idConfig.getPassword());
            }
            Codec codec = (Codec) Class.forName(idConfig.getCodec(), true, this.getClass().getClassLoader()).newInstance();
            config.setCodec(codec);
            config.setEventLoopGroup(new NioEventLoopGroup());
            log.info("connecting redis server");
            return Redisson.create(config);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            log.error("failed to connect redis", e);
            throw new IdException("failed to connect redis", e);
        }
    }

    @Bean
    public IdGenerator idGenerator() {
        return new IdGenerator();
    }


}
