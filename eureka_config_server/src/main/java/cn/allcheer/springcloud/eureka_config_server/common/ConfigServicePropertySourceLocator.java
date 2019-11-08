package cn.allcheer.springcloud.eureka_config_server.common;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lihui
 */
@Slf4j
public class ConfigServicePropertySourceLocator implements PropertySourceLocator {


    @Override
    public PropertySource<?> locate(Environment environment) {
        CompositePropertySource source = new CompositePropertySource("configService");

        Map<String,String> stringMap = new HashMap<>(5);
        Connection connection = null;
        try {
            Class.forName(environment.getProperty("spring.datasource.driver-class-name"));
            String url = environment.getProperty("spring.datasource.url");
            String userName = environment.getProperty("spring.datasource.username");
            String pwd = environment.getProperty("spring.datasource.password");
            connection = DriverManager.getConnection(url,userName,pwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT `key`,`value` FROM `base_config_param`");
            while (resultSet.next()){
                stringMap.put(resultSet.getString("key"),resultSet.getString("value"));
            }
        } catch (Exception e) {
            log.error("从数据库读取配置异常：{}",e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("关闭数据库连接异常：{}",e);
            }
        }
        Map<String,Object> objectMap = new HashMap<>(stringMap);
        source.addPropertySource(new MapPropertySource("baseConfig",objectMap));
        return source;
    }
}
