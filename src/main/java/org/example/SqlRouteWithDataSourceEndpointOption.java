package org.example;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SqlRouteWithDataSourceEndpointOption extends EndpointRouteBuilder {

    public static final String IN_URL = "seda:in";
    public static final String OUT_URL = "mock:out";

    @Override
    public void configure() {
        from(IN_URL)
            .to(sql("SELECT message FROM test").outputType("selectOne").dataSource("secondDataSource"))
            .to(OUT_URL);
    }
}
