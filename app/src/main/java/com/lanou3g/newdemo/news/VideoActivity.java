package com.lanou3g.newdemo.news;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.base.BaseAty;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * <p>
 * Created by 刘城羊 on 16/7/10.
 */
public class VideoActivity  extends AppCompatActivity implements UniversalVideoView.VideoViewCallback{

    private View mVideoLyout;
    private UniversalMediaController mMediaCotroller;
    private UniversalVideoView mVideoView;
    private View mBtnLayout;
    private TextView mStart;

    private static final String TAG = "MainActivity";
    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
    private static final String VIDEO_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    private int mSeekpostion;
    private int cacheHeight;
    private boolean isFullscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initView();
        initDate();
    }

    private void initDate() {
        mVideoView.setMediaController(mMediaCotroller);
        setVideoAreaSize();
        mVideoView.setVideoViewCallback(this);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSeekpostion > 0) {
                    mVideoView.seekTo(mSeekpostion);
                }
                mVideoView.start();
                mMediaCotroller.setTitle("Big buck bunny");
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG, "onCompletion");
            }
        });
    }

    private void setVideoAreaSize() {
        mVideoLyout.post(new Runnable() {
            @Override
            public void run() {
                int width = mVideoLyout.getWidth();
                cacheHeight = (int) (width * 405f / 720f);
                ViewGroup.LayoutParams videoLayoutParams = mVideoLyout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cacheHeight;
                mVideoLyout.setLayoutParams(videoLayoutParams);
                mVideoView.setVideoPath(VIDEO_URL);
                mVideoView.requestFocus();
            }
        });

    }

    private void initView() {
        mVideoLyout = findViewById(R.id.video_layout);
        mMediaCotroller = (UniversalMediaController) findViewById(R.id.media_controllar);
        mVideoView = (UniversalVideoView) findViewById(R.id.video_View);
        mBtnLayout = findViewById(R.id.btn_layout);
        mStart = (TextView) findViewById(R.id.start);

    }

    @Override
    public void onScaleChange(boolean isFullscreen) {

        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = mMediaCotroller.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoView.setLayoutParams(layoutParams);
            mBtnLayout.setVisibility(View.GONE);
        } else {
            ViewGroup.LayoutParams layoutParams = mVideoLyout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = this.cacheHeight;
            mVideoView.setLayoutParams(layoutParams);
            mBtnLayout.setVisibility(View.VISIBLE);
        }
        switchTitileBar(isFullscreen);

    }

    private void switchTitileBar(boolean isFullscreen) {
        ActionBar actionBar = getSupportActionBar();
        if (isFullscreen) {
            actionBar.show();
        } else {
            actionBar.hide();
        }
    }

    @Override
    public void onBackPressed() {
        if (isFullscreen) {
            mVideoView.setFullscreen(false);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(SEEK_POSITION_KEY, mSeekpostion);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mSeekpostion = savedInstanceState.getInt(SEEK_POSITION_KEY);
    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onStart");
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onBufferingStart");
    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onBufferingEnd");
    }
}
