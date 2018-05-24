package main.presenter;


import android.view.GestureDetector;
import android.view.ScaleGestureDetector;

public interface Presenter {
    void onCreate();

    // --Commented out by Inspection (24.05.18 13:41):void onPause();

    // --Commented out by Inspection (24.05.18 13:41):void onResume();

    // --Commented out by Inspection (24.05.18 13:41):void onDestroy();

    GestureDetector.OnGestureListener getNewGestureListener();

    ScaleGestureDetector.OnScaleGestureListener getNewScaleListener();

    void refreshInfo();
}