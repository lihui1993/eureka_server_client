package cn.allcheer.springcloud.eureka_config_server.config;

import cn.allcheer.springcloud.eureka_config_server.common.DataConfigEnvironmentRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.cloud.config.server.environment.JdbcEnvironmentProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author lihui
 */
@Configuration
@Profile({"dataConfig"})
public class DataConfigRepositoryConfiguration {

    @Bean
    @ConditionalOnClass(JdbcTemplate.class)
    public EnvironmentRepository dataConfigEnvironmentRepository(JdbcEnvironmentProperties environmentProperties){
        return new DataConfigEnvironmentRepository(environmentProperties);
    }
}
