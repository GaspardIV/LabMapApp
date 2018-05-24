package main.presenter;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import main.model.DownloadRequest;
import main.model.entities.RoomEntity;
import main.view.LabMapView;

public class LabMapPresenter implements Presenter {

    private static final String UPDATE_INFO_PREFIX = "Last update: ";
    private static final String UPDATE_FAILED = "failed! ";
    private static final String URL_TM385898_LABMAP_API_ROOMS = "http://students.mimuw.edu.pl/~tm385898/labmap/api/rooms/";
    private LabMapView view;

    public LabMapPresenter(LabMapView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        refreshInfo();
    }

    public void refreshInfo() {
        view.startRotatingRefreshFab();
        RequestQueue queue = Volley.newRequestQueue(view.getContext());

        DownloadRequest request = new DownloadRequest(
                URL_TM385898_LABMAP_API_ROOMS,
                new Response.Listener<List<RoomEntity>>() {
                    @Override
                    public void onResponse(List<RoomEntity> response) {
                        for (RoomEntity room : response) {
                            view.updateRoomView(RoomFinder.findRoomId(view.getContext(), room), room.getFreeStations(), room.getOccupiedStations());
                        }
                        view.changeInfoTextView(UPDATE_INFO_PREFIX + getCurrentTime());
                        view.stopRotatingRefreshFab();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        view.changeInfoTextView(UPDATE_INFO_PREFIX + UPDATE_FAILED + getCurrentTime());
                        view.stopRotatingRefreshFab();
                    }

                }
        );

        queue.add(request);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public MyGestureListener getNewGestureListener() {
        return new MyGestureListener();
    }

    public ScaleListener getNewScaleListener() {
        return new ScaleListener();
    }

    private String getCurrentTime() {
        String currentDateTimeString;
        currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
        return currentDateTimeString;
    }

    public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private final int STANDARD_DURATION = 200;
        private float rotation = 0;
        private float rotationX = 0;

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            rotation += distanceX / 5;
            rotationX += distanceY / 5;
            view.startAnimations(rotationX, rotation, STANDARD_DURATION, STANDARD_DURATION);
            return true;
        }

        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }


        @Override
        public boolean onDoubleTap(MotionEvent e) {
            view.changeFloor();
            return true;
        }
    }

    public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private float mScaleFactor = 1.0f;

        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            view.setScaleXY(mScaleFactor);
            return true;
        }
    }

}
