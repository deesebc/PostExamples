package com.example.home;

import static io.restassured.RestAssured.given;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.kafka.InjectKafkaCompanion;
import io.quarkus.test.kafka.KafkaCompanionResource;
import io.smallrye.reactive.messaging.kafka.companion.ConsumerTask;
import io.smallrye.reactive.messaging.kafka.companion.KafkaCompanion;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(KafkaCompanionResource.class)
public class TestRest {

  @InjectKafkaCompanion
  KafkaCompanion companion;

  @Test
  public void sentToKafka() {
    ConsumerTask<Integer, String> consume =
        companion.consume(Integer.class, String.class).withGroupId("test").withAutoCommit().fromTopics("my-topic", 1);

    given().body("{\"hello\":\"world\"}").when().post("/sent/kafka").then().statusCode(202);

    ConsumerRecord<Integer, String> records = consume.awaitCompletion().getFirstRecord();
    Assertions.assertEquals("{\"hello\":\"world\"}", records.value());
  }

}
