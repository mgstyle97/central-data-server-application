package io.migni.central.data.server.application.global.web.exception;

import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final EntityManager em;

    public GlobalExceptionHandler(final EntityManager em) {
        this.em = em;
    }

    @Transactional
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Long> handleGlobalException(final RuntimeException exception) {
        final Long requestSequence = Long.valueOf(MDC.get("requestSequence"));

        this.logger.error("[Error] Sequence - {}", requestSequence);
        this.logger.error("[Error] Message: {}", exception.getMessage());
        em.createNativeQuery("INSERT INTO error_log(request_sequence, message) VALUES (?, ?)")
            .setParameter(1, requestSequence)
            .setParameter(2, exception.getClass().getName())
            .executeUpdate();

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(requestSequence);
    }

}
