package com.idt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

/**
 * To parse one json into JsonObject just provide as first arg the json
 * as second arg provide id of the element that you want
 * <p>
 * Created by eric on 26/11/16.
 */
public class JsonApplication {

    public static void main(String[] args) {
//        String json = "{\n" +
//                "\"businesses\": [\n" +
//                "{\n" +
//                "\"id\": \"yelp-tropisueno\",\n" +
//                "\"name\" : \"Tropisueno\",\n" +
//                "\"display_phone\": \"+1-415-908-3801\",\n" +
//                "\"image_url\": \"http://s3-media2.ak.yelpcdn.com/bphoto/7DIHu8a0AHhw-BffrDIxPA/ms.jpg\",\n" +
//                "\"description\": \"\"\n" +
//                "}\n" +
//                "]\n" +
//                "}";
//        String id = "yelp-tropisueno";

        String json = args[0];
        String id = args[1];

        try {

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode jsonNode = mapper.readValue(json, ObjectNode.class);
            JsonNode foundElement = getElementById(jsonNode, id);

            if (id != null) {
                if (foundElement != null) {
                    System.out.println(foundElement.toString());
                }

            } else {
                System.out.println(jsonNode.toString());
            }
        } catch (IOException e) {
            System.err.println("Error parsing json");
        }
    }

    private static JsonNode getElementById(ObjectNode jsonNode, String id) {

        for (JsonNode node : jsonNode.elements().next()) {

            if (node.get("id").asText().equals(id)) {
                return node;
            }

        }

        return null;

    }
}
