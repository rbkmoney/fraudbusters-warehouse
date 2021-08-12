package com.rbkmoney.fraudbusterswarehouse.servlet;

import com.rbkmoney.fraudbusters.warehouse.QueryServiceSrv;
import com.rbkmoney.woody.thrift.impl.http.THServiceBuilder;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/query/v1/")
@RequiredArgsConstructor
public class QueryServiceServlet extends GenericServlet {

    private final QueryServiceSrv.Iface queryHandler;
    private Servlet thriftServlet;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        thriftServlet = new THServiceBuilder()
                .build(QueryServiceSrv.Iface.class, queryHandler);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        thriftServlet.service(req, res);
    }
}
