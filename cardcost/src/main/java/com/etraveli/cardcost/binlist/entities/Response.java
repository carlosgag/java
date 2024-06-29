package com.etraveli.cardcost.binlist.entities;

import lombok.Builder;

@Builder
public record Response(Number number, String scheme, String type, String brand, Boolean prepaid, Country country,
                       Bank bank) {
}
