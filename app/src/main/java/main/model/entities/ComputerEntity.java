package main.model.entities;

/**
 * Created by otto on 26.04.18.
 */

public class ComputerEntity {
    private String name;
    //    private String state;
    private UserEntity user;

    /***********************
     * GETTERS AND SETTERS */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
