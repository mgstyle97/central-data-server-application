package io.migni.central.data.server.application.domain.welding.application;

import io.migni.central.data.server.application.domain.welding.web.SaveWeldingDataRequest;

import java.util.List;
import java.util.UUID;

public interface WeldingDataService {

    UUID save(Long requestSequence, SaveWeldingDataRequest request);
    int saveBulk(Long requestSequence, List<SaveWeldingDataRequest> requestList);

}
