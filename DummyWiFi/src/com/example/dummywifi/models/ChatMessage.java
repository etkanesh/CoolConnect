package com.example.dummywifi.models;

import java.io.Serializable;

/**
 * Created by alex on 10/26/14.
 */
public class ChatMessage implements Serializable{
    private static final long serialVersionUID = -5967900946938355970L;

    private final String text;

    public ChatMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatMessage)) return false;

        ChatMessage message = (ChatMessage) o;

        if (!text.equals(message.text)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
