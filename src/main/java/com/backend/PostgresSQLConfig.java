package com.backend;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan("com.backend.mapper")
public class PostgresSQLConfig {

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    Resource[] resources = new PathMatchingResourcePatternResolver()
            .getResources("classpath*:mapper/**/*.xml");
    sessionFactory.setMapperLocations(resources);

    org.apache.ibatis.session.Configuration mybatisConfig =
            new org.apache.ibatis.session.Configuration();
    mybatisConfig.setMapUnderscoreToCamelCase(true);
    sessionFactory.setConfiguration(mybatisConfig);

    return sessionFactory.getObject();
  }

  @Bean
  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  @Bean
  public DataSourceTransactionManager txManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
