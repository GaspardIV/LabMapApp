package main.model.entities;

import java.util.ArrayList;

/**
 * Created by otto on 20.04.18.
 */

@SuppressWarnings("unused")
public class RoomEntity {
    public static final String ROOM_STR_ID_PREFIX = "room_";

    private String roomStrId;
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private ArrayList<ComputerEntity> computers;
    private int freeStations;
    private int occupiedStations;

    /**
     * Calculates right values for freeStations and occupiedStations.
     */
    public void calculateFreeAndOccupied() {
        freeStations = 0;
        occupiedStations = 0;
        for (ComputerEntity comp : computers) {
            if (comp.isOccupied()) occupiedStations++;
            else freeStations++;
        }
    }

    /* GETTERS AND SETTERS */
    /* GETTERS AND SETTERS */
    /* GETTERS AND SETTERS */
    /* GETTERS AND SETTERS */
    /* GETTERS AND SETTERS */

// --Commented out by Inspection START (24.05.18 13:41):
//    /**
//     * Setter.
//     *
//     * @param computers new value for computers
//     */
//    public void setComputers(ArrayList<ComputerEntity> computers) {
//        this.computers = computers;
//    }
// --Commented out by Inspection STOP (24.05.18 13:41)

    /**
     * Getter.
     *
     * @return roomStrId
     */
    public String getRoomStrId() {
        return roomStrId;
    }

    /**
     * Setter.
     *
     * @param roomStrId - new value of room String Id repr.
     */
    public void setRoomStrId(String roomStrId) {
        this.roomStrId = roomStrId;
    }

    /**
     * Getter.
     * To get the right value, you have to use calculateFreeAndOccupied
     * after each update of computers.
     *
     * @return number of occupied stations
     */
    public int getFreeStations() {
        return freeStations;
    }

    /**
     * Getter.
     * To get the right value, you have to use calculateFreeAndOccupied
     * after each update of computers.
     *
     * @return number of occupied stations
     */
    public int getOccupiedStations() {
        return occupiedStations;
    }

}
