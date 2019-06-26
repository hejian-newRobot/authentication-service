package com.service.auth.config;

import com.zaxxer.hikari.HikariDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 项目名称：compwechat
 * 包名称:conf
 * 类描述：
 * 创建人：何健
 *
 * @author hejian
 */
@Configuration
@MapperScan(basePackages = "com.service.auth.serviceauth.dao", sqlSessionTemplateRef = "testDBSqlSessionTemplate")
public class DataSourceConfigForTest {

    @Value("${ibatis.mapper.locations.test}")
    private String mapperLocations;

    @Bean(name = "testDB")
    @ConfigurationProperties(prefix = "datasource")
    public DataSource fsAppByCateringDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "testDBSqlSessionFactory")
    public SqlSessionFactory fsAppByCateringSqlSessionFactory(@Qualifier("testDB") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        bean.setTypeAliasesPackage(MybatisConfig.getTypeAliasesPackage());
        return bean.getObject();
    }

    @Bean(name = "testDBTransactionManager")
    public DataSourceTransactionManager fsAppByCateringTransactionManager(@Qualifier("testDB") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "testDBSqlSessionTemplate")
    public SqlSessionTemplate testDBSqlSessionTemplate(
            @Qualifier("testDBSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

