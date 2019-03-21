package ch.bfh.deng.data.etl.simpleroutebuilder;

import org.apache.camel.builder.RouteBuilder;

/**
 * Simple route defined for testing.
 */
public class SimpleRouteBuilder extends RouteBuilder {

    /**
     * Defines the route.
     *
     * @throws Exception if the route initialization fails
     */
    public void configure() throws Exception {
        from("file:/tmp/inbox")
        .to("file:/tmp/outbox");
    }
}
