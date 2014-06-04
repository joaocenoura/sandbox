/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joaocenoura.github.sandbox.camelweaving;

import org.apache.camel.builder.RouteBuilder;

/**
 * By introducing the choice/when/otherwise the test fails. weaveById method
 * creates two endpoint instance for "mock:checkpoint.before" and
 * "mock:checkpoint.after".
 *
 * @author João Rodrigues
 */
public class BadRouteTest extends AbstractRouteTest {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("direct:start").id("start")
                        .setHeader("hello", constant("world"))
                        .choice()
                        .when(header("hello").isEqualTo("world"))
                        .to("mock:checkpoint").id("checkpoint")
                        .endChoice()
                        .otherwise()
                        // this shouldn't be called
                        .to("mock:deadend").id("deadend")
                        .end()
                        .to("mock:end").id("end");
            }
        };
    }

}
