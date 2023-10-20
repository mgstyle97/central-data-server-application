package io.migni.central.data.server.application.domain.welding.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class WeldingDataJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public WeldingDataJdbcRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveBulk(final List<WeldingData> weldingDataList) {
        final int[][] result = this.jdbcTemplate.batchUpdate(
            "INSERT INTO welding_data" +
                "(id," +
                "request_sequence," +
                "machine_name," +
                "item_no," +
                "working_time," +
                "thickness_one," +
                "thickness_two," +
                "weld_force," +
                "weld_current," +
                "weld_voltage," +
                "weld_time," +
                "scaled_weld_force," +
                "scaled_weld_current," +
                "scaled_weld_voltage," +
                "scaled_weld_time) " +
                "VALUES (gen_random_uuid(),?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
            weldingDataList,
            weldingDataList.size(),
            getStatementSetter()
        );

        return result[0].length;
    }

    private static ParameterizedPreparedStatementSetter<WeldingData> getStatementSetter() {
        return (pstmt, weldingData) -> {
            pstmt.setLong(1, weldingData.requestSequence());
            pstmt.setString(2, weldingData.machineName());
            pstmt.setString(3, weldingData.itemNo());
            pstmt.setTimestamp(4, Timestamp.valueOf(weldingData.workingTime()));
            pstmt.setDouble(5, weldingData.thicknessOne());
            pstmt.setDouble(6, weldingData.thicknessTwo());
            pstmt.setDouble(7, weldingData.weldForce());
            pstmt.setDouble(8, weldingData.weldCurrent());
            pstmt.setDouble(9, weldingData.weldVoltage());
            pstmt.setDouble(10, weldingData.weldTime());
            pstmt.setString(11, weldingData.scaledWeldForce());
            pstmt.setString(12, weldingData.scaledWeldCurrent());
            pstmt.setString(13, weldingData.scaledWeldVoltage());
            pstmt.setString(14, weldingData.scaledWeldTime());
        };
    }

}
