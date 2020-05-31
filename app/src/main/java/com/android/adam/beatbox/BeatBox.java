package com.android.adam.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Adam on 08/08/2017.
 */

public class BeatBox {

    public static final float MINSPEED = 0.5f;
    public static final float MAXSPEED = 2.0f;

    public float getSpeed() {
        return mSpeed;
    }

    public void setSpeed(float speed) {
        mSpeed = speed;
    }

    private float mSpeed;

    private static final String TAG = "com.android.adam.beatbox.BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<Sound>();

    private static final int MAX_SOUNDS = 5;
    private SoundPool mSoundPool;

    public BeatBox(Context context) {

        mAssets = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        mSpeed= 1;

        loadSounds();
    }

    private void loadSounds() {

        String[] soundNames;

        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);

            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException ioe) {

            Log.e(TAG, "COULD NOT LIST ASSETS", ioe);
            return;
        }

        for (String filename : soundNames) {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe) {
                Log.e(TAG, "Coudl not load sound" + filename, ioe);
            }
        }
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    private void load(Sound sound) throws IOException {
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundId(soundId);


    }

    public void play(Sound sound) {
        Integer soundId = sound.getSoundId();
        if (soundId == null) {
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, mSpeed);
    }

    public void release() {
        mSoundPool.release();
    }

}