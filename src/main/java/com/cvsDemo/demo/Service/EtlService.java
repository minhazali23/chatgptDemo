package com.cvsDemo.demo.Service;

import com.cvsDemo.demo.DTO.DemoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EtlService {
    private static final Logger logger = LoggerFactory.getLogger(EtlService.class);
    private final ObjectMapper objectMapper;
    private static final Pattern regexResponsePattern = Pattern.compile(
            "1\\) Drug 1: (.*)\\n2\\) Drug 2: (.*)\\n3\\) Drug 1 Side Effects: (.*)\\n4\\) Drug 2 Side Effects: (.*)\\n5\\) Drug 1 Use Case: (.*)\\n6\\) Drug 2 Use Case: (.*)\\n7\\) Compatibility: (.*)", Pattern.DOTALL);

    EtlService() {
        this.objectMapper = new ObjectMapper();
    }

    public DemoDTO processResponse(String response) {
        try {
            DemoDTO extractedContent;

            String getExtractedContent = extractContentFromResponse(response);
            extractedContent = loadFieldsToDTO(getExtractedContent);
            if (extractedContent != null) {
                return extractedContent;
            }

        } catch (NullPointerException ex) {
            logger.error("Unable to process response from ChatGPT: ", ex);
        }
        return null;
    }

    public String extractContentFromResponse(String response) {
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(response);
            return rootNode.path("choices").get(0).path("message").path("content").asText();
        } catch (JsonProcessingException ex) {
            logger.error("Unable to extract response");
        }

        return rootNode.asText();
    }

    public DemoDTO loadFieldsToDTO(String response) {
        Matcher matcher = regexResponsePattern.matcher(response);
        if (matcher.find()) {
            DemoDTO regexMatchedDTO = new DemoDTO();
            regexMatchedDTO.setDrug1(matcher.group(1).trim());
            regexMatchedDTO.setDrug2(matcher.group(2).trim().toUpperCase());
            regexMatchedDTO.setDrug1SideEffects(matcher.group(3).trim());
            regexMatchedDTO.setDrug2SideEffects(matcher.group(4).trim());
            regexMatchedDTO.setDrug1UseCase(matcher.group(5).trim());
            regexMatchedDTO.setDrug2UseCase(matcher.group(6).trim());
            regexMatchedDTO.setCompatibility(matcher.group(7).trim());

            return regexMatchedDTO;
        }
        return null;
    }


}
