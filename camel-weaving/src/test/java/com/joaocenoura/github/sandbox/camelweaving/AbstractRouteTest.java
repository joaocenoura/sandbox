/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joaocenoura.github.sandbox.camelweaving;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * This is the base test. Child classes just have to override createRouteBuilder
 * method. The test simply sends a body "dummyPayload" to "direct:start"
 * endpoint and performs some assertions.
 *
 * @author João Rodrigues <joao-l-rodrigues@telecom.pt>
 */
public abstract class AbstractRouteTest extends CamelTestSupport {

    @Test
    public void test() throws InterruptedException {
        /* The expected route should be:
         direct:start               (1 message expected)
         mock:checkpoint.before     (1 message expected)
         mock:checkpoint            (1 message expected)
         mock:checkpoint.after      (1 message expected)
         mock:end                   (1 message expected)
         */

        // assert route endpoints
        getMockEndpoint("mock:direct:start").expectedMessageCount(1);
        getMockEndpoint("mock:checkpoint").expectedMessageCount(1);
        getMockEndpoint("mock:deadend").expectedMessageCount(0);
        getMockEndpoint("mock:end").expectedMessageCount(1);

        //assert weaved endpoints
        getMockEndpoint("mock:checkpoint.before").expectedMessageCount(1);
        getMockEndpoint("mock:checkpoint.after").expectedMessageCount(1);

        ProducerTemplate template = context.createProducerTemplate();
        template.sendBody("direct:start", "dummyPayload");

        assertMockEndpointsSatisfied();
    }
}
