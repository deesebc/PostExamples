package com.example.home.ApacheCamelMicrometer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class ApacheCamelMicrometerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ApacheCamelMicrometerApplication.class, args);
    }


    //    @Bean
    //    public CamelContextConfiguration camelContextConfiguration() {
    //
    //        return new CamelContextConfiguration() {
    //            @Override
    //            public void beforeApplicationStart(final CamelContext camelContext) {
    //                camelContext.addRoutePolicyFactory(new MicrometerRoutePolicyFactory());
    //                camelContext.setMessageHistoryFactory(new MicrometerMessageHistoryFactory());
    //            }
    //
    //            @Override
    //            public void afterApplicationStart(final CamelContext camelContext) {
    //
    //            }
    //        };
    //    }

}
