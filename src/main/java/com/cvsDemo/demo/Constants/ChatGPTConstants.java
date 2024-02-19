package com.cvsDemo.demo.Constants;

public class ChatGPTConstants {

    public static final String CHATGPT_API_PROMPT_SYSTEM_CONTEXT = "You will gather data for me and nothing else. You will provide me information for the drugs being requested. You will answer the question without adding your own opinion or thoughts. You will only fill in the fields that I provide with the information I am asking for:";
    public static final String CHATGPT_API_PROMPT_USER1 = "Can you give me information on these Drugs: ";
    public static final String CHATGPT_API_PROMPT_USER2 = " And fill in the fields here: " +

            "1) Drug 1: Insert answer here. Provide only the Drug Name in string format and no extra comments." +

            "2) Drug 2: Insert answer here. Provide only the Drug Name in string format and no extra comments." +

            "3) Drug 1 Side Effects: Insert answer here. Only list the side effects of this drug without giving me your input. If unable to determine the answer, then say “unknown”" +

            "4) Drug 2 Side Effects: Insert answer here. Only list the side effects of this drug without giving me your input. If unable to determine the answer, then say “unknown”" +

            "5) Drug 1 Use Case:  Insert answer here. Only list the use effects of this drug without giving me your input. If unable to determine the answer, then say “unknown”" +

            "6) Drug 2 Use Case:  Insert answer here. Only list the use effects of this drug without giving me your input. If unable to determine the answer, then say “unknown”" +

            "7) Compatibility – Insert answer here. Take your time to determine the answer. Determine if both drugs are safe to use with each other, with no negative interactions if used orally, intra, or topically, and say either 'Yes' if it is safe to consume together or 'No' if it is not safe, with no extra comments. If unable to determine the answer, then we can assume that it is safe since it is not flagged and should be 'Yes'";


}
