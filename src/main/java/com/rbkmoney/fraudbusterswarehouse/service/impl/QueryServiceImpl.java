package com.rbkmoney.fraudbusterswarehouse.service.impl;

import com.rbkmoney.fraudbusterswarehouse.repository.QueryRepository;
import com.rbkmoney.fraudbusterswarehouse.service.QueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueryServiceImpl implements QueryService {

    private final QueryRepository repository;

    @Override
    public List<Map<String, String>> query(String statement) {
        log.info("QueryService execute statement: {}", statement);
        List<Map<String, String>> result = repository.query(statement);
        log.info("QueryService result: {}", result);
        return result;
    }
}
