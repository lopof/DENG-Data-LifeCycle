
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FirstCamelRoute {

    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file:/tmp/inbox")
                        .to("file:/tmp/delivery");

            }
        });
        context.start();
        Thread.sleep(500000);
        context.stop();
    }
}


