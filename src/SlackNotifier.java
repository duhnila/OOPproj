import com.slack.api.SlackConfig;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;

import java.io.IOException;
import java.util.ServiceLoader;


public class SlackNotifier {

    public static void notifyOwner(String login, String id, boolean isReg) throws Exception {
        // Initialize Slack client
        Slack slack = Slack.getInstance();
        String message;
        String token = System.getenv("xoxb-4830904989731-4846654335828-Fp4cIztoYwekiZW5BKn36TJz");
        MethodsClient client = slack.methods(token);
        if (isReg) {
            message = String.format("User %s[%s] has registered", login, id);
        }
        else {
            message = String.format("User %s[%s] authorized", login, id);
        }

        // Set up message
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel("C04QR7YQ4JZ")
                .text(message)
                .build();

        // Send message
        ChatPostMessageResponse response = client.chatPostMessage(request);
        if (!response.isOk()) {
            throw new Exception("Failed to send Slack notification: " + response.getError());
        }
    }

    /*public static void notifyAdmin(String login, String id, boolean isReg) throws SlackApiException, IOException, NoClassDefFoundError {
        String channelId = "C04QR7YQ4JZ";
        String message;
        String token = System.getenv("xoxb-4830904989731-4846654335828-Fp4cIztoYwekiZW5BKn36TJz");
        //String token = "xapp-1-A04R4MVC1CG-4831155386018-fbd26b992e9ed829cf99a4e3a66f92e1411de03e369efd058ac3e7d50ee1ce68";
        if (isReg) {
            message = String.format("User %s[%s] has registered", login, id);
        }
        else {
            message = String.format("User %s[%s] authorized", login, id);
        }
        Slack slack = Slack.getInstance();
        MethodsClient client = slack.methods(token);

        ChatPostMessageRequest request = ChatPostMessageRequest.builder().channel(channelId).text(message).build();
        ChatPostMessageResponse response = client.chatPostMessage(request);
        if (response.isOk()) {
            System.out.println("Message sent to channel " + channelId);
        } else {
            System.out.println("Failed to send message: " + response.getError());
        }
    }*/
}
