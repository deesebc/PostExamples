package com.example;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SuppressWarnings({"resource", "rawtypes"})
public class MongoDBCustomTest {

  protected static final String DBNAME = "myDatabase";
  protected static final String USER = "mongoadministrator";
  protected static final String PASS = "secret";
  static String URI;
  private static final int MONGO_PORT = 27017;
  static GenericContainer mongoDBContainer;

  public static void startMongoDB() {
    try {
      mongoDBContainer = new GenericContainer(DockerImageName.parse("mongo:7.0.14"));
      List<String> list = new ArrayList<>();
      list.add("MONGO_INITDB_ROOT_USERNAME="+USER);
      list.add("MONGO_INITDB_ROOT_PASSWORD="+PASS);
      list.add("MONGO_INITDB_DATABASE="+DBNAME);
      mongoDBContainer.setEnv(list);
      mongoDBContainer.withExposedPorts(MONGO_PORT);
      mongoDBContainer.start();
      URI = String.format("mongodb://%s:%s@%s:%d/%s?authSource=admin", USER, PASS, mongoDBContainer.getHost(), mongoDBContainer.getFirstMappedPort(), DBNAME);
      log.info("MongoDB URI: " + URI);
    }catch(Exception e) {
      log.error(e.getMessage(), e);
    }
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
    try (MongoClient mongoClient = MongoClients.create( new ConnectionString(URI))) {
      MongoDatabase database = mongoClient.getDatabase(DBNAME);
      MongoCollection<Document> collection = database.getCollection("testCollection");
      org.bson.Document doc1 = new org.bson.Document("title", "Inception").append("imdb", new org.bson.Document("rating", 8.8));
      collection.insertOne(doc1);
      assertThat(collection.countDocuments(), equalTo(1L));
    }
  }
}
