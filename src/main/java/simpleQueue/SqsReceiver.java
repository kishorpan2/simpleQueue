package simpleQueue;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;

import java.util.List;

public class SqsReceiver {
    public static void main(String[] args) {

        String queueUrl = System.getenv("QUEUE_1");
        String queueAppleUrl = System.getenv("QUEUE_2");
        String queueAndroidUrl = System.getenv("QUEUE_3");
        String[] arr = {queueUrl, queueAppleUrl, queueAndroidUrl};
        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();



            List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();

            for (Message m : messages) {
                sqs.deleteMessage(queueUrl, m.getReceiptHandle());
            }
        }
    }

