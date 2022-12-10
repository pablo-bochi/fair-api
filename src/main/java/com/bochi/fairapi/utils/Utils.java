package com.bochi.fairapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utils.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    /**
     * Creates a json String from object.
     *
     * @param object the object
     * @return the json string
     */
    public static String toJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
