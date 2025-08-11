package com.backend;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@PropertySource("classpath:application.properties")
public class PostgreSQLConfig {
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.hikari")
  HikariConfig hikariConfig() {
    return new HikariConfig();
  }

  @Bean
  DataSource dataSource() throws Exception {
    DataSource dataSource = new HikariDataSource(hikariConfig());
    System.out.println("dataSource:" + dataSource.toString());
    return dataSource;
  }

  @Bean
  SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);

    Resource[] resources =
            new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*Mapper.xml");
    sessionFactory.setMapperLocations(resources);

    // ✅ 1. MyBatis의 상세 설정을 담을 Configuration 객체를 생성합니다.
    org.apache.ibatis.session.Configuration mybatisConfig = new org.apache.ibatis.session.Configuration();

    // ✅ 2. 여기에 camelCase 매핑 설정을 직접 추가합니다.
    mybatisConfig.setMapUnderscoreToCamelCase(true);

    // ✅ 3. 만들어진 설정을 SqlSessionFactoryBean에 적용합니다.
    sessionFactory.setConfiguration(mybatisConfig);

    return sessionFactory.getObject();
  }

  @Bean
  SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  @Bean
  DataSourceTransactionManager txManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
