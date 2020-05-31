package com.android.adam.beatbox;

/**
 * Created by Adam on 08/08/2017.
 */

public class Sound {
    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }

    private Integer mSoundId;

    public String getAssetPath() {
        return mAssetPath;
    }

    public void setAssetPath(String assetPath) {
        mAssetPath = assetPath;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    private String mAssetPath;
    private String mName;
    public Sound(String assetPath){

        mAssetPath = assetPath;

        String[] components = assetPath.split("/");
        String filename = components[components.length - 1];
        mName = filename.replace(".wave","");
    }


}


