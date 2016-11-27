package com.idt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

/**
 * Choose which json you want 0 = BUSINESS_JSON OR 1 = SHOPS_JSON
 * as optional second arg provide id of the element to print only the requested object.
a * <p>
 * <p>
 * Created by eric on 26/11/16.
 */
public class JsonApplication {

    private static final String BUSINESS_JSON = "{\"businesses\": [{\"id\": \"yelp-tropisueno\",\"name\" : \"Tropisueno\",\"display_phone\": \"+1-415-908-3801\",\"image_url\": \"http://s3-media2.ak.yelpcdn.com/bphoto/7DIHu8a0AHhw-BffrDIxPA/ms.jpg\",\"description\": \"\"}]}";

    private static final String SHOPS_JSON = "{\"shops\": [{\"id\": \"sample-shop\",\"name\" : \"Mingalaba\",\"display_phone\": \"+1-408-366-6600\",\"image_url\": \"http://s3-media2.ak.yelpcdn.com/bphoto/7DIHu8a0AHhw-BffrDIxPA/ms.jpg\",\"description\": \"Best Asian lovingly prepare by Jack \"Papa\" Rasmussen\"}]}";

    public static void main(String[] args) {

        String json = null;
        String id = null;

        if (args.length == 0) {
            System.err.println("No args provided exiting!...");
            System.exit(1);
        } else if (args.length > 1){
            id = args[1];
        }

        if (args[0].equals("0") || args[0].equals("1")) {
            json = args[0].equals("0") ? BUSINESS_JSON : SHOPS_JSON;
        }

        try {

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode jsonNode = mapper.readValue(json, ObjectNode.class);
            JsonNode foundElement = getElementById(jsonNode, id);
            printJsonNodeValue(id, jsonNode, foundElement);

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

    private static void printJsonNodeValue(String id, ObjectNode jsonNode, JsonNode foundElement) {

        if (id != null) {

            if (foundElement != null) {
                System.out.println(foundElement.toString());
            }

        } else {
            System.out.println(jsonNode.toString());
        }
    }
}
