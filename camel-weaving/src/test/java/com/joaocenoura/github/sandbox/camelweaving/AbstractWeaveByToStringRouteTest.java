/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joaocenoura.github.sandbox.camelweaving;

import org.apache.camel.builder.AdviceWithRouteBuilder;

/**
 * This is the base test. Child classes just have to override createRouteBuilder
 * method. The test simply sends a body "dummyPayload" to "direct:start"
 * endpoint and performs some assertions.
 *
 * @author João Rodrigues
 */
public abstract class AbstractWeaveByToStringRouteTest extends AbstractRouteTest {

    @Override
    protected void doPostSetup() throws Exception {
        context.getRouteDefinitions().get(0).adviceWith(
                context,
                new AdviceWithRouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        // mock all endpoints
                        mockEndpoints();

                        // add these two mocks: before and after checkpoint
                        weaveByToString(".*checkpoint.*").before().to("mock:checkpoint.before");
                        weaveByToString(".*checkpoint.*").after().to("mock:checkpoint.after");
                    }
                });
    }
}
