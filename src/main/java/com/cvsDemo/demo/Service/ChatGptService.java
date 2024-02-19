package com.cvsDemo.demo.Service;

import com.cvsDemo.demo.Constants.ChatGPTConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ChatGptService {

    public ChatGptService() {
        this.webClient = WebClient.builder().baseUrl("https://api.openai.com/v1").build();
    }
    private static final Logger logger = LoggerFactory.getLogger(ChatGptService.class);
    private final WebClient webClient;

    @Value("${chatgpt.api.key}")
    private String apiKey;

    @Value("${chatgpt.api.uri}")
    private String uri;

    @Value("${chatgpt.api.model}")
    private String model;

    @Value("${chatgpt.api.systemRole}")
    private String systemRole;

    @Value("${chatgpt.api.userRole}")
    private String userRole;

    //Need to add try catch with custom exeception
    public String generateResponse(String drug1, String drug2) {

        try {
            String userPrompt = ChatGPTConstants.CHATGPT_API_PROMPT_USER1 + drug1 + " " + drug2 + ChatGPTConstants.CHATGPT_API_PROMPT_USER2;

            WebClient.ResponseSpec response = webClient.post()
                    .uri(uri)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer ".concat(apiKey))
                    .body(BodyInserters.fromValue(createRequest(model, systemRole, ChatGPTConstants.CHATGPT_API_PROMPT_SYSTEM_CONTEXT, userRole, userPrompt)))
                    .retrieve();

            String responseBody = response.bodyToMono(String.class).block();

            if (responseBody != null && responseBody.length() > 0) {
                return responseBody;
            } else {
                System.out.println("ChatGpt is unable to return response");
            }
        } catch (NullPointerException e){
            logger.error("Unable to generate response");
        }
        return drug1;
    }

    private static String createRequest(String model, String systemRole, String systemContent, String userRole, String userContent){
        return "{\n" +
                "    \"model\": \"" + model + "\",\n" +
                "    \"messages\": [\n" +
                "        {\"role\": \"" + systemRole + "\", \"content\": \"" + systemContent + "\"},\n" +
                "        {\"role\": \"" + userRole + "\", \"content\": \"" + userContent + "\"}\n" +
                "    ]\n" +
                "}";
    }
}
