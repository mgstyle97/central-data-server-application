package io.migni.central.data.server.application.welding;

import autoparams.AutoSource;
import io.migni.central.data.server.application.domain.welding.SaveWeldingDataRequest;
import io.migni.central.data.server.application.domain.welding.WeldingDataRepository;
import io.migni.central.data.server.application.domain.welding.WeldingDataServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.Min;

@ExtendWith(MockitoExtension.class)
class WeldingDataServiceImplTest {

    @InjectMocks
    private WeldingDataServiceImpl weldingDataService;

    @Mock
    private WeldingDataRepository weldingDataRepository;

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

}