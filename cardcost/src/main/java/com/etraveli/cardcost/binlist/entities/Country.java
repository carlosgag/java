package com.etraveli.cardcost.binlist.entities;

import lombok.Builder;

@Builder
public record Country(String numeric, String alpha2, String name, String emoji, String currency, Double latitude,
                      Double longitude) {
}
