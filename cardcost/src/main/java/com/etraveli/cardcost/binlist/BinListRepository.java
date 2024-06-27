package com.etraveli.cardcost.binlist;

import com.etraveli.cardcost.binlist.entities.Response;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

@Repository
public class BinListRepository {

    public static final String ACCEPT_VERSION = "Accept-Version";

    public Response get(String card) {
        RestClient restClient = RestClient.create();
        return restClient.get()
                .uri("https://lookup.binlist.net/{card}", card)
                .header(ACCEPT_VERSION, "3")
                .retrieve()
                .body(Response.class);
    }
}
