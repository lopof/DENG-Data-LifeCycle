package ch.bfh.deng.data.etl.simpleroutebuilder;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Main class to wrap execution of {@link SimpleRouteBuilder}.
 */
public class SimpleRouteMain {

    /**
     * Main for starting the simple route and keeping it alive 500 seconds.
     * @param args none
     * @throws Exception any Camel exception
     */
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new SimpleRouteBuilder());
        context.start();
        Thread.sleep(500000);
        context.stop();
    }
}


