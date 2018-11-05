package com.example.android.miwok;

public class Word {
    private static final int NO_IMAGE = -1;

    private String defaultTranslation;
    private String miwokTranslation;
    private int imageResourceId = NO_IMAGE;
    private int mPronunciationAudioId;

    public Word(String defaultWord, String miwokWord, int pronunciationAudioId){
        defaultTranslation = defaultWord;
        miwokTranslation = miwokWord;
        mPronunciationAudioId = pronunciationAudioId;
    }

    public Word(String defaultWord, String miwokWord, int imageResource, int pronunciationAudioId){
        defaultTranslation = defaultWord;
        miwokTranslation = miwokWord;
        imageResourceId = imageResource;
        mPronunciationAudioId = pronunciationAudioId;
    }

    public String getDefaultTranslation(){
        return defaultTranslation;
    }

    public String getMiwokTranslation(){
        return miwokTranslation;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public boolean hasImage(){
        return imageResourceId != NO_IMAGE;
    }

    public int getPronunciation() {
        return mPronunciationAudioId;
    }
}
