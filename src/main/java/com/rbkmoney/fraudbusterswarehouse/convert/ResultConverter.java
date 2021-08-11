package com.rbkmoney.fraudbusterswarehouse.convert;

import com.rbkmoney.fraudbusters.warehouse.Result;
import com.rbkmoney.fraudbusters.warehouse.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ResultConverter implements Converter<List<Map<String, String>>, Result> {

    @Override
    public Result convert(List<Map<String, String>> queryResult) {
        Result result = new Result();
        List<Row> rows = queryResult.stream()
                .map(rowValues -> {
                    Row row = new Row();
                    row.setValues(rowValues);
                    return row;
                })
                .collect(Collectors.toList());
        result.setValues(rows);
        return result;
    }
}
