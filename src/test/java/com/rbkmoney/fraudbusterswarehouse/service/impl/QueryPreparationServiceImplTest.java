package com.rbkmoney.fraudbusterswarehouse.service.impl;

import com.rbkmoney.fraudbusterswarehouse.AbstractIntegrationTest;
import com.rbkmoney.fraudbusterswarehouse.service.QueryPreparationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class QueryPreparationServiceImplTest extends AbstractIntegrationTest {

    @Autowired
    private QueryPreparationService queryPreparationService;

    @Test
    void prepareWithoutParams() {
        String expectedStatement = "SELECT partyId, count() as cnt " +
                "FROM analytic.events_sink_chargeback " +
                "WHERE toDateTime(eventTime) >= " +
                "toDateTime(substring($currentDateTime, 1, length($currentDateTime) - 7)) " +
                "- INTERVAL 5 MINUTE " +
                "and status = 'accepted' " +
                "and partyId=='f42723d0-2022-4b66-9f92-4549769f1a92' " +
                "GROUP BY partyId " +
                "HAVING cnt > 3";

        String actualStatement = queryPreparationService.prepare(expectedStatement, Collections.emptyMap());

        assertEquals(expectedStatement, actualStatement);
    }


    @Test
    void prepareWithParams() {
        String statement = "SELECT partyId, count() as cnt " +
                "FROM analytic.events_sink_chargeback " +
                "WHERE toDateTime(eventTime) " +
                ">= toDateTime(substring($currentDateTime, 1, length($currentDateTime) - 7)) " +
                "- INTERVAL 5 MINUTE " +
                "and status = 'accepted' " +
                "and partyId=='f42723d0-2022-4b66-9f92-4549769f1a92' " +
                "GROUP BY partyId " +
                "HAVING cnt > 3";
        Map<String, String> params = new HashMap<>();
        String value = "2021-08-11T16:19:10";
        params.put("\\$currentDateTime", value);
        String expectedStatement = "SELECT partyId, count() as cnt " +
                "FROM analytic.events_sink_chargeback " +
                "WHERE toDateTime(eventTime) " +
                ">= toDateTime(substring(" + value + ", 1, length(" + value + ") - 7)) " +
                "- INTERVAL 5 MINUTE " +
                "and status = 'accepted' " +
                "and partyId=='f42723d0-2022-4b66-9f92-4549769f1a92' " +
                "GROUP BY partyId " +
                "HAVING cnt > 3";

        String actualStatement = queryPreparationService.prepare(statement, params);

        assertEquals(expectedStatement, actualStatement);
    }
}