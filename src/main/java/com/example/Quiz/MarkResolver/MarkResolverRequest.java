package com.example.Quiz.MarkResolver;

import java.util.HashMap;

public class MarkResolverRequest {
    private HashMap<Long,String> userSubmission ;

    public MarkResolverRequest(HashMap<Long, String> userSubmission) {
        this.userSubmission = userSubmission;
    }

    public MarkResolverRequest() {
    }

    public HashMap<Long, String> getUserSubmission() {
        return userSubmission;
    }
}
