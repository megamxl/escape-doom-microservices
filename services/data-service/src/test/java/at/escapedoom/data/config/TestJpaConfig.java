package at.escapedoom.data.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@TestConfiguration
@EnableConfigurationProperties(DatasourceProperties.class)
public class TestJpaConfig {

    private final DatasourceProperties datasourceProperties;

    public TestJpaConfig(DatasourceProperties datasourceProperties) {
        this.datasourceProperties = datasourceProperties;
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        System.out.println("Datasource URL: " + datasourceProperties.getUrl());
        System.out.println("Datasource Username: " + datasourceProperties.getUsername());
        return DataSourceBuilder.create().url(datasourceProperties.getUrl())
                .username(datasourceProperties.getUsername()).password(datasourceProperties.getPassword())
                .driverClassName("org.h2.Driver") // H2 driver
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.escapedoom.gamesession.dataaccess");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        em.setJpaPropertyMap(properties);

        return em;
    }

}
