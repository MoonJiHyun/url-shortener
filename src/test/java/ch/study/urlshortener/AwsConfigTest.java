package ch.study.urlshortener;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AwsConfigTest {

  @Autowired
  AWSCredentials awsCredentials;

  @Autowired
  AWSCredentialsProvider provider;

  @Autowired
  EndpointConfiguration endpointConfiguration;

  private AmazonDynamoDB amazonDynamoDb;

  @BeforeEach
  void setup() {
    amazonDynamoDb = AmazonDynamoDBClientBuilder.standard()
        .withCredentials(provider)
        .withEndpointConfiguration(endpointConfiguration).build();
  }

  @Test
  @Disabled
  void putItem_ShouldBeCalledAfterTableCreation_StatusOk() {
    Map<String, AttributeValue> item = new HashMap<>();

    item.put("id", new AttributeValue().withS("uuid"));
    item.put("shortenUrl", new AttributeValue().withS("shortenUrl"));
    item.put("originUrl", new AttributeValue().withS("originUrl"));

    PutItemRequest putItemRequest = new PutItemRequest()
        .withTableName("url_mapper")
        .withItem(item);

    PutItemResult putItemResult = amazonDynamoDb.putItem(putItemRequest);
    assertEquals(putItemResult.getSdkHttpMetadata().getHttpStatusCode(), 200);
  }
}
