package com.etraveli.cardcost.core;

import lombok.Builder;
import org.springframework.http.HttpStatusCode;

@Builder
public record ErrorMessage(HttpStatusCode status, String msg, String description) {
}
