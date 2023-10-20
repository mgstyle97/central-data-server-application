package io.migni.central.data.server.application.domain.welding.web;

import java.time.LocalDateTime;

public record SaveWeldingDataRequest(
    LocalDateTime requestTime,
    String machineName,
    String itemNo,
    LocalDateTime workingTime,
    Double thicknessOne,
    Double thicknessTwo,
    Double weldForce,
    Double weldCurrent,
    Double weldVoltage,
    Double weldTime,
    String scaledWeldForce,
    String scaledWeldCurrent,
    String scaledWeldVoltage,
    String scaledWeldTime
) {
}
