package com.home.example.router;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.springframework.stereotype.Component;
import com.home.example.pojo.Book;

@Component
public class CSVRouteBuilder extends RouteBuilder {

  BindyCsvDataFormat bindy = new BindyCsvDataFormat(Book.class);

  @Override
  public void configure() throws Exception {

    // from("file://{{dir.in}}?noop=true").filter(simple("${file:name.ext} == 'csv'")).id("readingCsvFiles")
    // .log("Converting ${in.headers.CamelFileName}").split(body().tokenize("\n")).streaming().to("direct:sending").end()
    // .log("Split done - CSV imported").end();


    // from("file://{{dir.in}}?preMove={{dir.inProgress}}&move={{dir.out}}&readLock=changed&readLockCheckInterval=4000")
    // .filter(simple("${file:name.ext} == 'csv'")).id("readingCsvFiles").log("Converting ${in.headers.CamelFileName}").split(body().tokenize("\n"))
    // .streaming().to("direct:sending").end().log("Split done - CSV imported").end();

    // camel-csv & dataformat
    // from("file://{{dir.in}}?noop=true").filter(simple("${file:name.ext} == 'csv'")).id("readingCsvFiles")
    // .log("Converting ${in.headers.CamelFileName}").marshal(dataFormat().csv().delimiter(",").end());

    // camel-csv
    // from("file://{{dir.in}}?noop=true").filter(simple("${file:name.ext} == 'csv'")).id("readingCsvFiles")
    // .log("Converting ${in.headers.CamelFileName}").split(body()).streaming().log("body split ${body}").end().end();

    from("file://{{dir.in}}?noop=true").filter(simple("${file:name.ext} == 'csv'")).id("readingCsvFiles")
        .log("Converting ${in.headers.CamelFileName}").unmarshal().csv().split(body()).to("direct:sendingArrayList").end();

    from("direct:sendingArrayList").process(exchange -> {
      final List<String> csv = exchange.getIn().getBody(ArrayList.class);
      Book newBook = new Book(Integer.valueOf(csv.get(0)), csv.get(2), csv.get(1));
      exchange.getIn().setBody(newBook);
    }).log("body: ${body}").log("Sending to ...").end();

    from("direct:sending").unmarshal(bindy).log("body ${body}").log("Sending to ...").end();


  }

}
