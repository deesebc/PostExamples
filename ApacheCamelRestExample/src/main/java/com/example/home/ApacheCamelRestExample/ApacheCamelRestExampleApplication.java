package com.example.home.ApacheCamelRestExample;

import org.apache.camel.opentracing.starter.CamelOpenTracing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import io.jaegertracing.Configuration;
//import io.jaegertracing.internal.JaegerTracer;

@SpringBootApplication
@CamelOpenTracing
public class ApacheCamelRestExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApacheCamelRestExampleApplication.class, args);
    }
    //    @Bean
    //    public static JaegerTracer getTracer() {
    //        Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);
    //        Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv().withLogSpans(true);
    //        Configuration config = new Configuration("jaeger tutorial 02").withSampler(samplerConfig).withReporter(reporterConfig);
    //        return config.getTracer();
    //    }

}
