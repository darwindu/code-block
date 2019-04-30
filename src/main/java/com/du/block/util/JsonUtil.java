package com.du.block.util;

import java.io.IOException;

import com.du.block.exception.SdkException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * data type cast by jackson method.
 *
 * @author darwindu
 */
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final ObjectWriter OBJECT_WRITER;
    private static final ObjectReader OBJECT_READER;

    static {
        // sort by letter
        OBJECT_MAPPER.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        // when map is serialization, sort by key
        OBJECT_MAPPER.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        OBJECT_WRITER = OBJECT_MAPPER.writer().withDefaultPrettyPrinter();
        OBJECT_READER = OBJECT_MAPPER.reader();
    }


    /**
     * Json String to Object.
     * @param clazz Class
     * @param jsonStr Json String
     * @return
     */
    public static Object jsonStrToObj(Class clazz, String jsonStr) {

        try {
            return OBJECT_READER.readValue(
                OBJECT_MAPPER.getFactory().createParser(jsonStr),
                clazz);
        } catch (IOException e) {
            throw new SdkException(e);
        }
    }

    /**
     * Object to Json String.
     *
     * @param obj Object
     * @return String
     */
    public static String objToJsonStr(Object obj) {

        try {
            return OBJECT_WRITER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SdkException(e);
        }
    }
}
