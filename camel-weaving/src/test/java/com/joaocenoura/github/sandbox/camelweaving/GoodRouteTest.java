/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joaocenoura.github.sandbox.camelweaving;

import org.apache.camel.builder.RouteBuilder;

/**
 * This very simple route works ok. weaveById method creates only one
 * "mock:checkpoint.before" and "mock:checkpoint.after"
 *
 * @author João Rodrigues
 */
public class GoodRouteTest extends AbstractRouteTest {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("direct:start").id("start")
                        .to("mock:checkpoint").id("checkpoint")
                        .to("mock:end").id("end");
            }
        };
    }

}
