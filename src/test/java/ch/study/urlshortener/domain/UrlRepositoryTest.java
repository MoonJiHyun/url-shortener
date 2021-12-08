package ch.study.urlshortener.domain;


import static org.assertj.core.api.BDDAssertions.then;

import ch.study.urlshortener.config.DynamoDbConfig;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {DynamoDbConfig.class})
public class UrlRepositoryTest {

  @Autowired
  private UrlRepository urlRepository;

  @Autowired
  private AmazonDynamoDB amazonDynamoDb;

  @Autowired
  private DynamoDBMapper dynamoDbMapper;

  @BeforeEach
  void createTable() {
    CreateTableRequest createTableRequest = dynamoDbMapper.generateCreateTableRequest(Url.class)
        .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

    createTableRequest.getGlobalSecondaryIndexes().forEach(idx -> idx
        .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L))
        .withProjection(new Projection().withProjectionType("ALL"))
    );
    TableUtils.createTableIfNotExists(amazonDynamoDb, createTableRequest);
  }

  @AfterEach
  void deleteTable() {
    DeleteTableRequest deleteTableRequest = dynamoDbMapper.generateDeleteTableRequest(Url.class);
    TableUtils.deleteTableIfExists(amazonDynamoDb, deleteTableRequest);
  }

  @Test
  void createUrl() {
    Url createdUrl = urlRepository.save(
        Url.builder()
          .shortenUrl("localhost/12345678")
          .originUrl("www.naver.com")
          .build()
    );

    then(createdUrl)
        .hasFieldOrPropertyWithValue("shortenUrl", "localhost/12345678")
        .hasFieldOrPropertyWithValue("originUrl", "www.naver.com");
  }
}
