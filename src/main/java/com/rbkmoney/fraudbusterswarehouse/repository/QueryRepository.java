package com.rbkmoney.fraudbusterswarehouse.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QueryRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Map<String, String>> query(String statement) {
        List<Map<String, Object>> result = jdbcTemplate.queryForList(statement);
        return result.stream()
                .map(map -> map.entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, e -> String.valueOf(e.getValue()))))
                .collect(Collectors.toList());
    }

}
