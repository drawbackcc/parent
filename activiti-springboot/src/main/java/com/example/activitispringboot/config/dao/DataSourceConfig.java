package com.example.activitispringboot.config.dao;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration//注解到spring容器中
@MapperScan(basePackages = {"com.example.activitispringboot.dao"}, sqlSessionFactoryRef = "sqlSessionFactory1")
public class DataSourceConfig {
    static final String MAPPER_LOCATION = "classpath:mapper/*.xml";
    static final String TypeAliasesPackage = "com.example.activitispringboot.bean";

    @Bean(name="dataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.data1")
    public DataSource dataSource(){
//        DruidDataSource
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactory1")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource1") DataSource ds) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        bean.setTypeAliasesPackage(TypeAliasesPackage);
        return bean.getObject();
    }

//    @Bean(name = "dataSqlSessionTemplate1")
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory1") SqlSessionFactory sessionFactory) throws  Exception{
//        return  new SqlSessionTemplate(sessionFactory);
//    }

    @Bean(name = "dataTransactionManager1")
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource1") DataSource ds){
        return new DataSourceTransactionManager(ds);
    }
}
