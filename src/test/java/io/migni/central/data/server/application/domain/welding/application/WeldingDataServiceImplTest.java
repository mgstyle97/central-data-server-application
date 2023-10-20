package io.migni.central.data.server.application.domain.welding.application;

import autoparams.AutoSource;
import io.migni.central.data.server.application.domain.welding.application.WeldingDataServiceImpl;
import io.migni.central.data.server.application.domain.welding.persistence.WeldingDataJdbcRepository;
import io.migni.central.data.server.application.domain.welding.persistence.WeldingDataRepository;
import io.migni.central.data.server.application.domain.welding.web.SaveWeldingDataRequest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.Min;
import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class WeldingDataServiceImplTest {

    @InjectMocks
    private WeldingDataServiceImpl weldingDataService;

    @Mock
    private WeldingDataRepository weldingDataRepository;

    @Mock
    private WeldingDataJdbcRepository weldingDataJdbcRepository;

    @ParameterizedTest(name = "테스트: Welding Data 저장")
    @AutoSource
    void save_welding_data(
        @Min(1) final Long requestSequence,
        final SaveWeldingDataRequest request
    ) {
        // given

        // when
        final var result = weldingDataService.save(requestSequence, request);

        // then
    }

    @ParameterizedTest(name = "테스트: Welding Data bulk insert")
    @AutoSource
    void save_bulk_welding_data(
        @Min(1) final Long requestSequence,
        final List<SaveWeldingDataRequest> requestList
    ) {
        // given
        given(weldingDataJdbcRepository.saveBulk(anyList())).willReturn(requestList.size());

        // when
        final var result = weldingDataService.saveBulk(requestSequence, requestList);

        // then
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(result).isNotNull();
            softAssertions.assertThat(result).isEqualTo(requestList.size());
        });
    }

}