package com.rbkmoney.fraudbusterswarehouse.servlet.handler;

import com.rbkmoney.fraudbusters.warehouse.Query;
import com.rbkmoney.fraudbusters.warehouse.QueryServiceSrv;
import com.rbkmoney.fraudbusters.warehouse.Result;
import com.rbkmoney.fraudbusterswarehouse.convert.ResultConverter;
import com.rbkmoney.fraudbusterswarehouse.service.QueryPreparationService;
import com.rbkmoney.fraudbusterswarehouse.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QueryHandler implements QueryServiceSrv.Iface {

    private final QueryService queryService;
    private final QueryPreparationService queryPreparationService;
    private final ResultConverter resultConverter;

    @Override
    public Result execute(Query query) {
        String queryStatement = query.getStatement();
        if (query.isSetParams()) {
            queryStatement = queryPreparationService.prepare(query.getStatement(), query.getParams());
        }
        List<Map<String, String>> queryResult = queryService.query(queryStatement);
        return resultConverter.convert(queryResult);
    }
}
