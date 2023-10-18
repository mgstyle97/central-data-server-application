package io.migni.central.data.server.application.domain.welding;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional(readOnly = true)
@Service
public class WeldingDataServiceImpl implements WeldingDataService {

    private final WeldingDataRepository weldingDataRepository;

    public WeldingDataServiceImpl(final WeldingDataRepository weldingDataRepository) {
        this.weldingDataRepository = weldingDataRepository;
    }

    @Override
    @Transactional
    public UUID save(
        final Long requestSequence,
        final SaveWeldingDataRequest request
    ) {
        final WeldingData weldingData = new WeldingData(
            requestSequence,
            request.machineName(),
            request.itemNo(),
            request.workingTime(),
            request.thicknessOne(),
            request.thicknessTwo(),
            request.weldForce(),
            request.weldCurrent(),
            request.weldVoltage(),
            request.weldTime(),
            request.scaledWeldForce(),
            request.scaledWeldCurrent(),
            request.scaledWeldVoltage(),
            request.scaledWeldTime()
        );

        this.weldingDataRepository.save(weldingData);

        return weldingData.id();
    }

}
