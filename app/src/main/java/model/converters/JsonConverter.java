package model.converters;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by otto on 26.04.18.
 */

public class JsonConverter {
    public static JsonObject asJsonObject(String s) {
        return parse(s).getAsJsonObject();
    }

    public static JsonArray asJsonArray(String s) {
        return parse(s).getAsJsonArray();
    }

    private static JsonElement parse(String s) {
        return new JsonParser().parse(s);
    }
}
