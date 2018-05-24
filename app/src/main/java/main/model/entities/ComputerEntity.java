package main.model.entities;

/**
 * Created by otto on 26.04.18.
 */

@SuppressWarnings("unused")
class ComputerEntity {
    private String name;
    //    private String state;
    private UserEntity user;

// --Commented out by Inspection START (24.05.18 13:42):
//    /***********************
//     * GETTERS AND SETTERS */
//    public String getName() {
//        return name;
//    }
// --Commented out by Inspection STOP (24.05.18 13:42)

// --Commented out by Inspection START (24.05.18 13:42):
//    public void setName(String name) {
//        this.name = name;
//    }
// --Commented out by Inspection STOP (24.05.18 13:42)

// --Commented out by Inspection START (24.05.18 13:42):
//    public void setUser(UserEntity user) {
//        this.user = user;
//    }
// --Commented out by Inspection STOP (24.05.18 13:42)
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
