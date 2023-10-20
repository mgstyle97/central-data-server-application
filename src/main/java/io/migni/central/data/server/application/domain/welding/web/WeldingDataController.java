package io.migni.central.data.server.application.domain.welding.web;

import io.migni.central.data.server.application.domain.welding.application.WeldingDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/sensing")
@RestController
public class WeldingDataController {

    private final WeldingDataService weldingDataService;

    public WeldingDataController(final WeldingDataService weldingDataService) {
        this.weldingDataService = weldingDataService;
    }

    @PostMapping
    public ResponseEntity<UUID> save(
        @RequestHeader("Global-Request-Sequence") final Long requestSequence,
        @RequestBody final SaveWeldingDataRequest request
    ) {
        final UUID id = this.weldingDataService.save(requestSequence, request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(id);
    }

    @PostMapping("/batch")
    public ResponseEntity<Integer> saveBulk(
        @RequestHeader("Global-Request-Sequence") final Long requestSequence,
        @RequestBody final List<SaveWeldingDataRequest> requestList
    ) {
        final int saveCount = this.weldingDataService.saveBulk(requestSequence, requestList);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(saveCount);
    }

}
