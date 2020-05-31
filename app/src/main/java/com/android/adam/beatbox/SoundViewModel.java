package com.android.adam.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Adam on 08/08/2017.
 */

public class SoundViewModel extends BaseObservable {

    public Sound getSound() {
        return mSound;
    }

    public void setSound(Sound sound) {
        mSound = sound;
        notifyChange();
    }

    private Sound mSound;
    private BeatBox mBeatBox;


    public SoundViewModel(BeatBox beatbox){
        mBeatBox = beatbox;
    }

    @Bindable
    public String getTitle(){
        return mSound.getName();
    }


    public void onButtonClicked() {
        mBeatBox.play(mSound);
    }
}
