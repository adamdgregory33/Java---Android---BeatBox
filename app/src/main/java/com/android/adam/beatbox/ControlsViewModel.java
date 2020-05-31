package com.android.adam.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.SeekBar;

/**
 * Created by Adam on 08/08/2017.
 */

public class ControlsViewModel extends BaseObservable {
    private BeatBox mBeatBox;

    public int getMinSeekBar() {
        return mMinSeekBar;
    }

    public void setMinSeekBar(int minSeekBar) {
        mMinSeekBar = minSeekBar;
    }

    public int getMaxSeekBar() {
        return mMaxSeekBar;
    }

    public void setMaxSeekBar(int maxSeekBar) {
        mMaxSeekBar = maxSeekBar;
    }

    private int mMinSeekBar = 50;
    private int mMaxSeekBar  = 200;
    private int mRangeSeekBar = mMaxSeekBar - mMinSeekBar;
    private float mRangeSpeed;

    public ControlsViewModel(BeatBox beatBox, SeekBar seekBar) {
        mBeatBox = beatBox;
        mMinSeekBar = 0;
        mMaxSeekBar = seekBar.getMax();
        mRangeSeekBar = mMaxSeekBar - mMinSeekBar;//needed for some calcs
        mRangeSpeed = mBeatBox.MAXSPEED - mBeatBox.MINSPEED;//also needed for some calcs
    }

    public int getSeekBarValue() {

        int seekBarValue = Math.round(((mBeatBox.getSpeed() - mBeatBox.MINSPEED) / mRangeSpeed) * mRangeSeekBar + mMinSeekBar);

        if (seekBarValue > mMaxSeekBar) {
            return mMaxSeekBar;
        } else if (seekBarValue < mMinSeekBar) {
            return mMinSeekBar;
        } else {
            return seekBarValue;
        }
    }


    public void changeSpeed(SeekBar seekBar, int progress, boolean fromUser) {
        setSpeed(progress);
    }
    public float getSpeed(){
        return mBeatBox.getSpeed();
    }

    public void setSpeed(int seekBarValue) {
        float speed = ((float) seekBarValue - mMinSeekBar) / mRangeSeekBar * mRangeSpeed + mBeatBox.MINSPEED;
        mBeatBox.setSpeed(speed);
        notifyChange();
    }


}
