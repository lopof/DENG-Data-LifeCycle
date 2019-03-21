package ch.bfh.deng.data.etl.sampleroutes;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class SecondCamelRoute {

    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file:/tmp/inbox")
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                System.out.println(" Moved file: "
                                        + exchange.getIn().getHeader("CamelFileAbsolutePath"));
                            }
                        })
                        .to("file:/tmp/outbox");

            }
        });
        context.start();
        Thread.sleep(50000);
        context.stop();
    }
}


