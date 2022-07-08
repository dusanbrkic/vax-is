package com.sluzbenik.rest.service;

import org.springframework.scheduling.annotation.Async;

public interface EmailService {

    @Async
    void sendMessage(String to, String subject, String text);
}
