package io.migni.central.data.server.application.domain.welding;

import java.util.UUID;

public interface WeldingDataService {

    UUID save(Long requestSequence, SaveWeldingDataRequest request);

}
