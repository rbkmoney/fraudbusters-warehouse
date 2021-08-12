package com.rbkmoney.fraudbusterswarehouse.servlet.handler;

import com.rbkmoney.fraudbusters.warehouse.Query;
import com.rbkmoney.fraudbusters.warehouse.QueryServiceSrv;
import com.rbkmoney.fraudbusters.warehouse.Result;
import com.rbkmoney.fraudbusterswarehouse.convert.ResultConverter;
import com.rbkmoney.fraudbusterswarehouse.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QueryHandler implements QueryServiceSrv.Iface {

    private final QueryService queryService;
    private final ResultConverter resultConverter;

    @Override
    public Result execute(Query query) {
        Map<String, String> params = query.isSetParams() ? query.getParams() : Collections.emptyMap();
        List<Map<String, String>> queryResult = queryService.query(query.getStatement(), params);
        return resultConverter.convert(queryResult);
    }
}
