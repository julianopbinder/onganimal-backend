package br.com.onganimal.app;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class WebTestUtils {

    /**
     * Prevents instantiation
     */
    private WebTestUtils() {}

    /**
     * Transforms an object into JSON and returns the JSON as a byte array.
     * @param object    The object that is transformed into JSON.
     * @return          The JSON representation of an object as a byte array.
     * @throws IOException
     */
    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}