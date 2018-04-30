package presenter;


import android.view.GestureDetector;
import android.view.ScaleGestureDetector;

public interface Presenter {
    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();

    GestureDetector.OnGestureListener getNewGestureListener();

    ScaleGestureDetector.OnScaleGestureListener getNewScaleListener();

    void refreshInfo();
}