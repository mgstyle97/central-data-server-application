package io.migni.central.data.server.application.setting;

import io.migni.central.data.server.application.domain.welding.persistence.WeldingDataJdbcRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@TestConfiguration
public class JdbcTestConfig {

    @Bean
    public WeldingDataJdbcRepository weldingDataJdbcRepository(final DataSource dataSource) {
        return new WeldingDataJdbcRepository(new JdbcTemplate(dataSource));
    }

}
