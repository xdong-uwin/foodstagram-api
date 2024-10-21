package org.scraper.foodstagram.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MailUtil {

    private static final String API_KEY = "9fb7ba70907771f47d6c519b9355c2a5-d010bdaf-3af72564";

    public static JsonNode sendVerificationLink(String receiver, String link) throws UnirestException {
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/mail.evanue.tech/messages")
                .basicAuth("api", API_KEY)
                .queryString("from", "Foodstagram <postmaster@mail.evanue.tech>")
                .queryString("to", receiver)
                .queryString("subject", "Verify your email")
                .queryString("text", link)
                .asJson();
        return request.getBody();
    }
}
