package com.example.TrelloLive.config;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public HikariDataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/TrelloLive");
        config.setUsername("root");
        config.setPassword("admin");
        config.setMaximumPoolSize(10);
        config.setPoolName("MyHikariCP");

        HikariDataSource dataSource = new HikariDataSource(config);
        // Log the pool status at startup
        System.out.println("HikariCP Pool Name: " + dataSource.getPoolName());
        System.out.println("HikariCP Total Connections: " + dataSource.getHikariPoolMXBean().getTotalConnections());
        System.out.println("HikariCP Active Connections: " + dataSource.getHikariPoolMXBean().getActiveConnections());
        System.out.println("HikariCP Idle Connections: " + dataSource.getHikariPoolMXBean().getIdleConnections());

        return dataSource;
    }
}
