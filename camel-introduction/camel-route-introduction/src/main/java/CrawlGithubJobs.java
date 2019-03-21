import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CrawlGithubJobs {


    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("quartz://secondTimer?trigger.repeatInterval=10000")
                .to("http4://jobs.github.com/positions?utf8=%E2%9C%93&description=Data+Engineer&location=San+Francisco")
                        .process(new HTMLSplitter()).split().body().process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("${body}");
                    }
                })
                        .log("${in.body}");
            }
        });
        context.start();
        Thread.sleep(50000);
        context.stop();
    }


}
