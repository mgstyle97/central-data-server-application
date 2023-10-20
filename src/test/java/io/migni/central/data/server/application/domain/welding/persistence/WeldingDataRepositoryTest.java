package io.migni.central.data.server.application.domain.welding.persistence;

import autoparams.AutoSource;
import io.migni.central.data.server.application.domain.welding.persistence.WeldingData;
import io.migni.central.data.server.application.domain.welding.persistence.WeldingDataRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class WeldingDataRepositoryTest {

    @Autowired
    private WeldingDataRepository weldingDataRepository;

    @ParameterizedTest(name = "테스트: Welding data 저장")
    @AutoSource
    void save_welding_data(final WeldingData weldingData) {
        // given

        // when
        final var result = weldingDataRepository.save(weldingData);

        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(result).isNotNull();
            softAssertions.assertThat(result.id()).isNotNull();
            softAssertions.assertThat(result.id()).isEqualTo(weldingData.id());
        });
    }

}