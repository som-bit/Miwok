package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private  MediaPlayer mMediaPlayer;
    private AudioManager myAudioManager;
    private MediaPlayer.OnCompletionListener mOnCompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {

                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);

                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        mMediaPlayer.start();

                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();
                    }

                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        //setting an action bar and navigatipn button
        getSupportActionBar().setTitle("Colors");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);



        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Red", "Wetetti",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("Green", "chokokki",R.drawable.color_green,R.raw.color_green));
        words.add(new Word("Brown", "Takaakki",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("Gray", "Topoppi",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("Black", "Kululli",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("White", "Kelelli",R.drawable.color_white,R.raw.color_white));
        words.add(new Word("Dusty yellow", "Topiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Word("Mustard yellow", "Chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        // Create {An Arraylist} whose data source is a list of string, The
        // adaptor knows how to create a Layout for each item in the List
        //,Using The
        WordAdapter Adapter = new WordAdapter(this,words);

        ListView listView = (ListView) findViewById(R.id.listColor);

        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();
                int result = myAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this,word.getmAudioId());
                    mMediaPlayer.start();
                }


                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releaseMediaPlayer();
                    }
                });

            }
        });

    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();
            Toast.makeText(ColorsActivity.this, "Media Palyer Released", Toast.LENGTH_SHORT).show();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            myAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}