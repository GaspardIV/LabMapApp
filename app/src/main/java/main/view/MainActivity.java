package main.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.gaspard.labmapapp.R;

import java.util.Locale;

import main.presenter.LabMapPresenter;
import main.presenter.Presenter;
import main.view.fragments.Fragment2000;
import main.view.fragments.Fragment3000;


public class MainActivity extends FragmentActivity implements LabMapView {

    private final Presenter presenter = new LabMapPresenter(this);
    private GestureDetectorCompat mDetector;
    private ScaleGestureDetector mScaleGestureDetector;
    private int actualFloor;
    private Fragment2000 firstFragment;
    private Fragment3000 secondFragment;
    private View stage;
    private TextView infoTextView;
    private FloatingActionButton fabRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stage = findViewById(R.id.fragment_container);
        infoTextView = findViewById(R.id.text_info);
        fabRefresh = findViewById(R.id.fab_refresh);

        initGesturesDetectors();
        initFragments(savedInstanceState);
        presenter.onCreate();

        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }

    private void initGesturesDetectors() {
        mDetector = new GestureDetectorCompat(this, presenter.getNewGestureListener());
        mScaleGestureDetector = new ScaleGestureDetector(this, presenter.getNewScaleListener());
    }

    private void initFragments(Bundle savedInstanceState) {
        firstFragment = new Fragment2000();
        secondFragment = new Fragment3000();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).add(R.id.fragment_container, secondFragment).show(firstFragment).hide(secondFragment).commit();
            actualFloor = 0;
        }
    }

    public void changeFloor() {
        if (actualFloor != 0) {
            actualFloor = 0;
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top).hide(secondFragment).show(firstFragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom).hide(firstFragment).show(secondFragment).commit();
            actualFloor = 1;
        }
    }

    @Override
    public void setScaleXY(float mScaleFactor) {
        stage.setScaleX(mScaleFactor);
        stage.setScaleY(mScaleFactor);
        stage.invalidate();
    }

    public void startAnimations(float rotationX, float rotation, int durationX, int durationY) {
        ObjectAnimator animationRotationX = ObjectAnimator.ofFloat(stage, "rotationX", rotationX);
        ObjectAnimator animationRotation = ObjectAnimator.ofFloat(stage, "rotation", rotation);
        animationRotation.setDuration(durationX);
        animationRotation.start();
        animationRotationX.setDuration(durationY);
        animationRotationX.start();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void updateRoomView(int roomId, int freeStations, int occupiedStations) {
        View roomView = stage.findViewById(roomId);
        ((TextView) roomView.findViewById(R.id.text_free)).setText(String.format(Locale.ENGLISH, "%02d", freeStations));
        ((TextView) roomView.findViewById(R.id.text_occupied)).setText(String.format(Locale.ENGLISH, "%02d", occupiedStations));
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        this.mScaleGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void onFabClick(@SuppressWarnings("unused") View view) {
        presenter.refreshInfo();
    }

    @Override
    public void changeInfoTextView(String text) {
        infoTextView.setText(text);
    }

    @Override
    public void stopRotatingRefreshFab() {
        fabRefresh.clearAnimation();
        fabRefresh.setEnabled(true);
    }

    @Override
    public void startRotatingRefreshFab() {
        fabRefresh.setEnabled(false);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(900);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        fabRefresh.startAnimation(rotateAnimation);
    }
}
