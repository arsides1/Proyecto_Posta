package sistema.web.indicadores.com.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager",
        basePackages = {"sistema.web.indicadores.com.*"}
)
@EntityScan(basePackages = "sistema.web.indicadores.com.model.dto")
public class RepositoryConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.db-core")
    public DataSourceProperties coreDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties("spring.datasource.db-core.hikari")
    public DataSource primaryDataSource() {
        return coreDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("primaryDataSource") DataSource primaryDataSource
    ) {
        return builder
                .dataSource(primaryDataSource)
                .packages("sistema.web.indicadores.com.model.dto")
                .build();
    }

    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory
    ) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }

    @Bean
    @Qualifier("jdbcCore")
    public JdbcTemplate dbCoreJdbcTemplate(@Qualifier("primaryDataSource") DataSource primaryDataSource) {
        return new JdbcTemplate(primaryDataSource);
    }
}

