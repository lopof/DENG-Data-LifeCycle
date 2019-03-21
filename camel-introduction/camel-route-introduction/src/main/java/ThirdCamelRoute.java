
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ThirdCamelRoute {

    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file:/tmp/inbox")
                        .choice()
                        .when(simple("${in.header.CamelFileName} contains '4'"))
                            .to("file:/tmp/outbox")
                        .otherwise()
                            .log("Ignoring ${in.header.CamelFileName}");
            }
        });
        context.start();
        Thread.sleep(50000);
        context.stop();
    }
}


