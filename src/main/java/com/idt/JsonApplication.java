package com.idt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

/**
 * To parse one json into JsonObject just provide as first arg the json
 * as optional second arg provide id of the element to print only the requested object.
 *
 * <p>
 * Created by eric on 26/11/16.
 */
public class JsonApplication {

    public static void main(String[] args) {

        String json = args[0];
        String id = args[1];

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
