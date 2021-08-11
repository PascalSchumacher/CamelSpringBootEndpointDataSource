package org.example;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
@CamelSpringBootTest
class ExampleTest {

    @Resource(name = "dataSource")
    DataSource dataSource;
    @Resource(name = "secondDataSource")
    DataSource secondDataSource;
    @EndpointInject(SqlRouteWithDataSourceEndpointOption.IN_URL)
    ProducerTemplate producerTemplate;
    @EndpointInject(SqlRouteWithDataSourceEndpointOption.OUT_URL)
    MockEndpoint mockOut;

    @Test
    void test() throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(secondDataSource);
        jdbcTemplate.execute("create table test(message varchar(255))");

        mockOut.expectedMessageCount(1);

        producerTemplate.sendBody("");

        mockOut.assertIsSatisfied();
    }
}
