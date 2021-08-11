package com.rbkmoney.fraudbusterswarehouse.service.impl;

import com.rbkmoney.fraudbusterswarehouse.service.QueryPreparationService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class QueryPreparationServiceImpl implements QueryPreparationService {

    @Override
    public String prepare(String statement, Map<String, String> params) {
        String finalQueryStatement = statement;
        for (Map.Entry<String, String> param : params.entrySet()) {
            finalQueryStatement = finalQueryStatement.replaceAll(param.getKey(), param.getValue());
        }
        return finalQueryStatement;
    }
}
