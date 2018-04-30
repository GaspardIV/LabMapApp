package main.presenter;

import android.content.Context;

import java.util.HashMap;

import main.model.entities.RoomEntity;


public class RoomFinder {
    private static final String ROOM_STR_ID_PREFIX = "room_";
    private static String[] ROOMS_STR_IDS = new String[]{
            ROOM_STR_ID_PREFIX + "2041",
            ROOM_STR_ID_PREFIX + "2042",
            ROOM_STR_ID_PREFIX + "2043",
            ROOM_STR_ID_PREFIX + "2044",
            ROOM_STR_ID_PREFIX + "2045",
            ROOM_STR_ID_PREFIX + "3041",
            ROOM_STR_ID_PREFIX + "3042",
            ROOM_STR_ID_PREFIX + "3043",
            ROOM_STR_ID_PREFIX + "3044",
            ROOM_STR_ID_PREFIX + "3045",
    };

    private static final HashMap<String, Integer> ROOMS_IDS_MAP = new HashMap<>();

    public static int findRoomId(Context activity, RoomEntity roomEntity) {
        if (ROOMS_IDS_MAP.isEmpty()) {
            for (String k : ROOMS_STR_IDS) {
                ROOMS_IDS_MAP.put(k, activity.getResources().getIdentifier(k, "id", activity.getPackageName()));
            }
        }
        return ROOMS_IDS_MAP.get(roomEntity.getRoomStrId());

//        View roomView = ((Activity) activity).findViewById());

    }
}
