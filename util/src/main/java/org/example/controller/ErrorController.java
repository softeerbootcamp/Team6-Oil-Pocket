package org.example.controller;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackField;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@RestControllerAdvice
public class ErrorController {
    @Value("${slack.token}")
    private String token;
    @Value("${slack.url}")
    private String url;

    @ExceptionHandler(Throwable.class)
    public ResponseEntity handleException(Throwable e) {
        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setFallback("Error");
        slackAttachment.setColor("danger");
        slackAttachment.setTitle("Error Detect");
        slackAttachment.setText(LocalDateTime.now() + "\n" + printStackToString(e));

        SlackMessage slackMessage = new SlackMessage();
        slackMessage.setAttachments(Collections.singletonList(slackAttachment));
        slackMessage.setText("Error");
        SlackApi api = new SlackApi(url + token);
        api.call(slackMessage);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private String printStackToString(Throwable e) {
        StringWriter error = new StringWriter();
        e.printStackTrace(new PrintWriter(error));
        return error.toString();
    }

}
