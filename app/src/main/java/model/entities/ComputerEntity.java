package model.entities;

import android.support.annotation.Nullable;

/**
 * Created by otto on 26.04.18.
 */

public class ComputerEntity {
    private String name;
//    private String state;
    private User user;

    /***********************
     * GETTERS AND SETTERS */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /* GETTERS AND SETTERS *
     ***********************/


    /**
     * Is this computer occupied by someone
     *
     * @return true if it is, false otherwise
     */
    public boolean isOccupied() {
        return user != null;
    }

}
