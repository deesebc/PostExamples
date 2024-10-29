package com.example;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.bson.Document;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TestContainerMongoDBTest {

  protected static final String DBNAME = "myDatabase";
  static String URI;
  private static Integer PORT;
  static MongoDBContainer mongoDBContainer;

  @SuppressWarnings("resource")
  public static void startMongoDB() {
    log.info("startMongoDB");
    mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:7.0.14")).withEnv("MONGO_INITDB_DATABASE", DBNAME);
    mongoDBContainer.start();
    URI = mongoDBContainer.getConnectionString() + "/" + DBNAME;
    log.info("MongoDB URI: " + URI);
    PORT = mongoDBContainer.getFirstMappedPort();
    log.info("MongoDB PORT: " + PORT);
  }

  @AfterClass
  public static void destroy() {
    log.info("cleaning");
    if (mongoDBContainer != null) {
      mongoDBContainer.stop();
    }
    log.info("cleaning success");
  }

  @BeforeClass
  public static void setup() {
    log.info("setup");
    startMongoDB();
  }

  @Test
  public void insertAndCount() {
    try(MongoClient mongoClient = MongoClients.create(URI)){
      MongoDatabase database = mongoClient.getDatabase(DBNAME);
      MongoCollection<Document> collection = database.getCollection("testCollection");
      org.bson.Document doc1 = new org.bson.Document("title", "Inception").append("imdb", new org.bson.Document("rating", 8.8));
      collection.insertOne(doc1);
      assertThat(collection.countDocuments(), equalTo(1L));
    }
  }


}
