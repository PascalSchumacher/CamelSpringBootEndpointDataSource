package org.example;

import javax.sql.DataSource;

import org.apache.camel.Component;
import org.apache.camel.component.sql.SqlComponent;
import org.apache.camel.spi.ComponentCustomizer;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component
public class SqlComponentCustomerizer implements ComponentCustomizer {

    @Autowired
    DataSource dataSource;

    @Override
    public void configure(String name, Component component) {
        if (component instanceof SqlComponent) {
            ((SqlComponent) component).setDataSource(dataSource);
        }
    }
}