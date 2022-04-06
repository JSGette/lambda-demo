import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.*;
import com.hft.example.LambdaHandler;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LambdaHandlerTest {
    @Test
    public void invokeLambdaTest() {
        String bucket = new String("sum-function-bucket");
        S3EventNotificationRecord record = new S3EventNotificationRecord("eu-central-1",
                "ObjectCreated:Put",
                "aws:s3",
                "2022-04-06T00:00:00.000Z",
                "1.0",
                new RequestParametersEntity("127.0.0.1"),
                new ResponseElementsEntity("000000000000", "1111111111111"),
                new S3Entity("0000-0000-0000",
                        new S3BucketEntity(bucket,
                                new UserIdentityEntity("JSGETTETEST0000"),
                                "arn:aws:s3:eu-central-1::" + bucket),
                        new S3ObjectEntity("test.txt",
                                new Long(1),
                                "skjdskajdwlkqjelwkj",
                                "",
                                "apsldjwqlhrfksjafs"),
                        "1.0"),
                new UserIdentityEntity("AWS:JSGETTETESTTESTTEST"));
        ArrayList<S3EventNotificationRecord> records = new ArrayList<S3EventNotificationRecord>();
        records.add(record);
        S3Event event = new S3Event(records);

        Context context = new ContextMock();
        LambdaHandler handler = new LambdaHandler();
        String result = handler.handleRequest(event, context);
        System.out.println(result);
        assertTrue(result.contains("OK."));
    }
}
