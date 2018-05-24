package main.model.converters;


//import com.google.gson.JsonArray;
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

// --Commented out by Inspection START (24.05.18 13:41):
//    public static JsonArray asJsonArray(String s) {
//        return parse(s).getAsJsonArray();
//    }
// --Commented out by Inspection STOP (24.05.18 13:41)

    private static JsonElement parse(String s) {
        return new JsonParser().parse(s);
    }
}
