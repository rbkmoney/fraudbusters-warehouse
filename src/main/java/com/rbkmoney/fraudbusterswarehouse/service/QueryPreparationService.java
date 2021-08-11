package com.rbkmoney.fraudbusterswarehouse.service;

import java.util.Map;

public interface QueryPreparationService {

    String prepare(String statement, Map<String, String> params);

}
