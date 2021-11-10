package com.example.android.miwok;

public class Word {
    private String mDefaultTranslation;

    private String mMiwakTranslaction;

    private int mAudioId;


    private int mImgResourceId = NO_IMAGE_PRESENT;

    private static  final int NO_IMAGE_PRESENT= -1;

    public Word(String DefaultTranslation, String MiwakTranslaction, int audio){
            mDefaultTranslation=DefaultTranslation;
            mMiwakTranslaction = MiwakTranslaction;
            mAudioId= audio;
    }

    public Word(String DefaultTranslation, String MiwakTranslaction, int imgId, int audio ){
        mDefaultTranslation=DefaultTranslation;
        mMiwakTranslaction = MiwakTranslaction;
        mImgResourceId =imgId;
       mAudioId = audio;
    }


    public  int getImgResourceId(){

        return mImgResourceId;
    }
    public String getmDefaultTranslation (){
        return mDefaultTranslation;

    }

    public int getmAudioId(){
        return mAudioId;

    }

    public String getmMiwakTranslaction(){
        return mMiwakTranslaction;
    }

    // to check if im age is associated
    // with the view or not

    public  boolean isImage(){
        return  mImgResourceId!= NO_IMAGE_PRESENT;

    }



}

