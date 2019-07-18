package simpleQueue;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class SqsSend {
    public static void main(String[] args) {

        String queueUrl = System.getenv("QUEUE_1");
        String queueAppleUrl = System.getenv("QUEUE_2");
        String queueAndroidUrl = System.getenv("QUEUE_3");
        String[] arr = {queueUrl, queueAppleUrl, queueAndroidUrl};
        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

        for (int i = 0; i < 3; i++) {

            SendMessageRequest send_msg_request = new SendMessageRequest()
                    .withQueueUrl(arr[i])
                    .withMessageBody("hello world")
                    .withDelaySeconds(5);
            sqs.sendMessage(send_msg_request);

        }
            // Send multiple messages to the queue

            SendMessageBatchRequest send_batch_request = new SendMessageBatchRequest()
                    .withQueueUrl(queueUrl)
                    .withEntries(
                            new SendMessageBatchRequestEntry(
                                    "msg_1", "Hello from message 1"),
                            new SendMessageBatchRequestEntry(
                                    "msg_2", "Hello from message 2")
                                    .withDelaySeconds(10));
            sqs.sendMessageBatch(send_batch_request);
        }

    }

