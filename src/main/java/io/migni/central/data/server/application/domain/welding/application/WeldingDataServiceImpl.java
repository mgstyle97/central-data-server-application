package io.migni.central.data.server.application.domain.welding.application;

import io.migni.central.data.server.application.domain.welding.persistence.WeldingData;
import io.migni.central.data.server.application.domain.welding.persistence.WeldingDataJdbcRepository;
import io.migni.central.data.server.application.domain.welding.persistence.WeldingDataRepository;
import io.migni.central.data.server.application.domain.welding.web.SaveWeldingDataRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional(readOnly = true)
@Service
public class WeldingDataServiceImpl implements WeldingDataService {

    private final WeldingDataRepository weldingDataRepository;
    private final WeldingDataJdbcRepository weldingDataJdbcRepository;

    public WeldingDataServiceImpl(
        final WeldingDataRepository weldingDataRepository,
        final WeldingDataJdbcRepository weldingDataJdbcRepository
    ) {
        this.weldingDataRepository = weldingDataRepository;
        this.weldingDataJdbcRepository = weldingDataJdbcRepository;
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

    @Override
    @Transactional
    public int saveBulk(
        final Long requestSequence,
        final List<SaveWeldingDataRequest> requestList
    ) {
        final List<WeldingData> weldingDataList = requestList.stream()
            .map(request -> new WeldingData(
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
            )).toList();

        final int result = this.weldingDataJdbcRepository.saveBulk(weldingDataList);

        return result;
    }

}
