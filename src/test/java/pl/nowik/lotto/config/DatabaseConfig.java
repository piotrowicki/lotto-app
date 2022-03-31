package pl.nowik.lotto.config;

import java.util.Map;

import org.testcontainers.containers.MariaDBContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class DatabaseConfig implements QuarkusTestResourceLifecycleManager {

    private static final MariaDBContainer DATABASE = new MariaDBContainer<>("mariadb")
            .withDatabaseName("lotto")
            .withUsername("lotto")
            .withPassword("lotto")
            .withExposedPorts(3306);

    @Override
    public Map<String, String> start() {
        DATABASE.start();
        return Map.of("quarkus.datasource.jdbc.url", DATABASE.getJdbcUrl(),
                "quarkus.datasource.username", "lotto",
                "quarkus.datasource.password", "lotto");
    }

    @Override
    public void stop() {
        DATABASE.stop();
    }
}
