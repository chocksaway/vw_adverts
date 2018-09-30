package com.chocksaway.exception;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

/**
 * Author milesd on 16/09/2018.
 */
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    @GetMapping
    public ObjectNode getJson() {
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();

        objectNode.put("date", this.timestamp.toString());
        objectNode.put("message", this.message);
        objectNode.put("details", this.details);

        return objectNode;
    }

}