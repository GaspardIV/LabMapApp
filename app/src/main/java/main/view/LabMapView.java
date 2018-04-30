package main.view;

import android.content.Context;

public interface LabMapView {
    void changeFloor();

    void setScaleXY(float mScaleFactor);

    void startAnimations(float rotationX, float rotation, int durationX, int durationY);

    Context getContext();

    void updateRoomView(int roomId, int freeStations, int occupiedStations);
}
