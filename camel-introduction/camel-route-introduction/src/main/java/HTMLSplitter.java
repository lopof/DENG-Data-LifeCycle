import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HTMLSplitter implements Processor {
    public void process(Exchange exchange) {
        Message in = exchange.getIn();
        String htmlContent = in.getBody(String.class);
        Document doc = Jsoup.parse(htmlContent, "UTF-8");
        Elements htmlFragments = doc.select("tr[class=job]");
        in.setBody(htmlFragments);
        exchange.setOut(in);
    }
}