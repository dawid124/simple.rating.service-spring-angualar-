package pl.webd.dawid124.simpleratingservice.config;

import org.flywaydb.core.Flyway;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@MapperScan("pl.webd.dawid124.simpleratingservice")
public class ConnectorConfig {

    @Value("${spring.datasource.url}")
    private String dataBaseUrl;
    @Value("${spring.datasource.username}")
    private String dataBaseUser;
    @Value("${spring.datasource.password}")
    private String dataBasePassword;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(dataBaseUrl);
        dataSource.setUsername(dataBaseUser);
        dataSource.setPassword(dataBasePassword);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setTransactionFactory(managedTransactionFactory());
        return sessionFactory;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("classpath:/db/migration/");
        flyway.setDataSource(dataSource());
        return flyway;
    }

//    @Bean
//    public ManagedTransactionFactory managedTransactionFactory() {
//        return new ManagedTransactionFactory();
//    }
}
