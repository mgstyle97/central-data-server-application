package io.migni.central.data.server.application.domain.welding.web;

import autoparams.AutoSource;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.migni.central.data.server.application.domain.welding.application.WeldingDataService;
import io.migni.central.data.server.application.domain.welding.web.SaveWeldingDataRequest;
import io.migni.central.data.server.application.domain.welding.web.WeldingDataController;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.validation.constraints.Min;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeldingDataController.class)
class WeldingDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WeldingDataService weldingDataService;

    @MockBean
    private EntityManager em;

    @ParameterizedTest(name = "테스트: Welding Data 저장")
    @AutoSource
    void save_welding_data(
        @Min(1) final Long requestSequence,
        final SaveWeldingDataRequest request
    ) throws Exception {
        // given
        given(weldingDataService.save(any(), any())).willReturn(UUID.randomUUID());

        final String content = objectMapper.writeValueAsString(request);

        // when
        final ResultActions resultActions = mockMvc.perform(
            post("/sensing")
                .header("Global-Request-Sequence", requestSequence)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        ).andDo(print());

        // then
        resultActions.andExpect(status().isCreated());
    }

    @ParameterizedTest(name = "테스트: Welding Data bulk insert")
    @AutoSource
    void save_bulk_welding_data(
        @Min(1) final Long requestSequence,
        final List<SaveWeldingDataRequest> requestList
    ) throws Exception {
        // given
        given(weldingDataService.saveBulk(any(), any())).willReturn(requestList.size());

        final String content = objectMapper.writeValueAsString(requestList);

        // when
        final ResultActions resultActions = mockMvc.perform(
            post("/sensing/batch")
                .header("Global-Request-Sequence", requestSequence)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        ).andDo(print());

        // then
        resultActions.andExpect(status().isCreated());
    }

}