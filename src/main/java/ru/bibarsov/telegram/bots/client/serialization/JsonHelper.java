package ru.bibarsov.telegram.bots.client.serialization;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.io.UncheckedIOException;

@ParametersAreNonnullByDefault
public class JsonHelper {

    private final ObjectMapper deserializerOM;
    private final ObjectMapper serializerOM;

    public JsonHelper() {
        this.deserializerOM = new ObjectMapper();
        this.deserializerOM.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        this.deserializerOM.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.serializerOM = new ObjectMapper();
    }

    public JsonHelper(ObjectMapper deserializerOM, ObjectMapper serializerOM) {
        this.deserializerOM = deserializerOM;
        this.serializerOM = serializerOM;
    }

    public <T> T deserialize(String json, Class<T> valueType) {
        try {
            return this.deserializerOM.readValue(json, valueType);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public String serialize(Object o) {
        return this.serializerOM.valueToTree(o).toString();
    }
}
