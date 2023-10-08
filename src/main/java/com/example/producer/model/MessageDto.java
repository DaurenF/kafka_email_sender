package com.example.producer.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JacksonXmlRootElement(localName = "message")
public class MessageDto {
    @NotNull(message = "Message must not be null")
    @JacksonXmlProperty(localName = "message")
    private String message;
    @NotNull(message = "Sender must not be null")
    @JacksonXmlProperty(localName = "sender")
    private String sender;
}
