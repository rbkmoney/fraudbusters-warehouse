package com.rbkmoney.fraudbusterswarehouse.service;

import java.util.List;
import java.util.Map;

public interface QueryService {

    List<Map<String, String>> query(String statement);

}
