package com.iceye.assignment.service;

import com.iceye.assignment.model.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AssignmentService {

    private static final String JSON_URL = "https://jsonplaceholder.typicode.com/posts";

    @Autowired
    private ImageService imageService;

    /**
     * Retrieves the JSON content from {@link #JSON_URL}
     *
     * @return List of json items
     */
    public JsonObject[] getJson(){
        RestTemplate restTemplate = new RestTemplate();
        JsonObject[] jsonObjects = restTemplate.getForObject(JSON_URL, JsonObject[].class);
        for (JsonObject jsonObject : jsonObjects) {
            jsonObject.setTitle(reverse(jsonObject.getTitle()));
            jsonObject.setBody(reverse(jsonObject.getBody()));
        }
        return jsonObjects;
    }

    public void ingest(String text){
        imageService.convertTextToImage(text);
    }

    /**
     *
     * Reverses a given text
     *
     * @param str
     * @return reversed String
     */
    private String reverse(String str){
        return new StringBuilder(str).reverse().toString();
    }
}
