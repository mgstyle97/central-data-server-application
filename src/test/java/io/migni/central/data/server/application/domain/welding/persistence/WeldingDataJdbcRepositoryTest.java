package io.migni.central.data.server.application.domain.welding.persistence;

import autoparams.AutoSource;
import io.migni.central.data.server.application.domain.welding.persistence.WeldingData;
import io.migni.central.data.server.application.domain.welding.persistence.WeldingDataJdbcRepository;
import io.migni.central.data.server.application.setting.JdbcTestConfig;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

@Import(JdbcTestConfig.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJdbcTest
class WeldingDataJdbcRepositoryTest {

    @Autowired
    private WeldingDataJdbcRepository weldingDataJdbcRepository;

    @ParameterizedTest(name = "테스트: Welding Data bulk insert")
    @AutoSource
    void save_bulk_welding_data(final List<WeldingData> weldingDataList) {
        // given

        // when
        final var result = weldingDataJdbcRepository.saveBulk(weldingDataList);

        // then
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(result).isNotNull();
            softAssertions.assertThat(result).isEqualTo(weldingDataList.size());
        });
    }

}