import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class ContextMock implements Context {

    @Override
    public String getAwsRequestId() {
        return new String("000000000-0000-000000");
    }

    @Override
    public String getLogGroupName() {
        return new String("/aws/lambda/sum-function");
    }

    @Override
    public String getLogStreamName() {
        return new String("/2022/04/06/[$LATEST]00000000000");
    }

    @Override
    public String getFunctionName() {
        return new String("sum-function");
    }

    @Override
    public String getFunctionVersion() {
        return new String("$LATEST");
    }

    @Override
    public String getInvokedFunctionArn() {
        return new String("arn:aws:lambda:eu-central-1:000000000000:function:sum-function");
    }

    @Override
    public CognitoIdentity getIdentity() {
        return null;
    }

    @Override
    public ClientContext getClientContext() {
        return null;
    }

    @Override
    public int getRemainingTimeInMillis() {
        return 600000;
    }

    @Override
    public int getMemoryLimitInMB() {
        return 256;
    }

    @Override
    public LambdaLogger getLogger() {
        return new TestLogger();
    }
}
