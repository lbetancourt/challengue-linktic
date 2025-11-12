package com.linktic.stock.infrastructure.controller.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
public class MessageResponse {
    String message;
    Integer code;
}
