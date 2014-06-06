/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joaocenoura.github.sandbox.camelweaving;

import org.apache.camel.builder.RouteBuilder;

public class GoodWeaveByToStringRouteTest extends AbstractWeaveByToStringRouteTest {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new GoodRoute();
    }

}
