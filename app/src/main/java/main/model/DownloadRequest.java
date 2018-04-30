package main.model;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.model.converters.JsonConverter;
import main.model.entities.RoomEntity;

public class DownloadRequest extends Request<List<RoomEntity>> {
    private final Response.Listener<List<RoomEntity>> listener;
    private final Gson gson = new Gson();

    public DownloadRequest(String url, Response.Listener<List<RoomEntity>> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.listener = listener;
    }

    @Override
    protected Response<List<RoomEntity>> parseNetworkResponse(NetworkResponse response) {
        try {
            List<RoomEntity> rooms = new ArrayList<>();
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            JsonObject wholeRsp = JsonConverter.asJsonObject(json).getAsJsonObject("data");
            TypeToken token = new TypeToken<HashMap<String, RoomEntity>>() {};
            HashMap<String, RoomEntity> resultsMap = gson.fromJson(wholeRsp, token.getType());
            for (HashMap.Entry<String, RoomEntity> kv : resultsMap.entrySet()) {
                RoomEntity actRoom = kv.getValue();
                actRoom.setRoomStrId(RoomEntity.ROOM_STR_ID_PREFIX + kv.getKey());
                actRoom.calculateFreeAndOccupied();
                rooms.add(actRoom);
            }
            return Response.success(rooms, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        } catch (java.lang.ClassCastException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(List<RoomEntity> response) {
        listener.onResponse(response);
    }

}
