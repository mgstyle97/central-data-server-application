package io.migni.central.data.server.application.domain.welding;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "welding_data")
public class WeldingData {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long requestSequence;
    private String machineName;
    private String itemNo;
    private LocalDateTime workingTime;
    private Double thicknessOne;
    private Double thicknessTwo;
    private Double weldForce;
    private Double weldCurrent;
    private Double weldVoltage;
    private Double weldTime;
    private String scaledWeldForce;
    private String scaledWeldCurrent;
    private String scaledWeldVoltage;
    private String scaledWeldTime;

    protected WeldingData() {
    }

    public WeldingData(
        final Long requestSequence,
        final String machineName,
        final String itemNo,
        final LocalDateTime workingTime,
        final Double thicknessOne,
        final Double thicknessTwo,
        final Double weldForce,
        final Double weldCurrent,
        final Double weldVoltage,
        final Double weldTime,
        final String scaledWeldForce,
        final String scaledWeldCurrent,
        final String scaledWeldVoltage,
        final String scaledWeldTime
    ) {
        this.requestSequence = requestSequence;
        this.machineName = machineName;
        this.itemNo = itemNo;
        this.workingTime = workingTime;
        this.thicknessOne = thicknessOne;
        this.thicknessTwo = thicknessTwo;
        this.weldForce = weldForce;
        this.weldCurrent = weldCurrent;
        this.weldVoltage = weldVoltage;
        this.weldTime = weldTime;
        this.scaledWeldForce = scaledWeldForce;
        this.scaledWeldCurrent = scaledWeldCurrent;
        this.scaledWeldVoltage = scaledWeldVoltage;
        this.scaledWeldTime = scaledWeldTime;
    }

    public UUID id() {
        return this.id;
    }

}
