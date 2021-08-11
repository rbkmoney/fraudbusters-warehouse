package com.rbkmoney.fraudbusterswarehouse.repository;

import com.rbkmoney.fraudbusterswarehouse.AbstractIntegrationTest;
import com.rbkmoney.fraudbusterswarehouse.query.TestQuery;
import com.rbkmoney.testcontainers.annotations.clickhouse.ClickhouseTestcontainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ClickhouseTestcontainer(migrations = {
        "sql/db_init.sql",
        "sql/data/inserts_event_sink.sql"})
class QueryRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private QueryRepository queryRepository;

    @Test
    void query() {
        List<Map<String, String>> result = queryRepository.query(TestQuery.QUERY_METRIC_RECURRENT);

        Map<String, String> stringObjectMap = result.get(0);
        assertEquals("ad8b7bfd-0760-4781-a400-51903ee8e504", stringObjectMap.get("shopId"));
        assertEquals("166.66666666666666", stringObjectMap.get("metric"));
    }
}
