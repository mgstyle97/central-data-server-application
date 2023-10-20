package io.migni.central.data.server.application.domain.welding;

import autoparams.AutoSource;
import io.migni.central.data.server.application.domain.welding.web.SaveWeldingDataRequest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

@Transactional
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeldingDataIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String url = "http://localhost:";

    @ParameterizedTest(name = "테스트: Welding Data 저장")
    @AutoSource
    void save_welding_data(
        @Min(1) final Long requestSequence,
        final SaveWeldingDataRequest request
    ) {
        // given
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Global-Request-Sequence", String.valueOf(requestSequence));

        final HttpEntity<SaveWeldingDataRequest> requestEntity = new HttpEntity<>(request, headers);

        // when
        final var result = restTemplate.postForEntity(
            url + port + "/sensing",
            requestEntity,
            UUID.class
        );

        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(result).isNotNull();
            softAssertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            softAssertions.assertThat(result.getBody()).isNotNull();
        });
    }

    @ParameterizedTest(name = "테스트: Welding Data bulk insert")
    @AutoSource
    void save_bulk_welding_data(
        @Min(1) final Long requestSequence,
        final List<SaveWeldingDataRequest> requestList
    ) {
        // given
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Global-Request-Sequence", String.valueOf(requestSequence));

        final HttpEntity<List<SaveWeldingDataRequest>> requestEntity = new HttpEntity<>(requestList, headers);

        // when
        final var result = restTemplate.postForEntity(
            url + port + "/sensing/batch",
            requestEntity,
            Integer.class
        );

        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(result).isNotNull();
            softAssertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            softAssertions.assertThat(result.getBody()).isNotNull();
            softAssertions.assertThat(result.getBody()).isEqualTo(requestList.size());
        });
    }

}
