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

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager myAudioManager;
    private MediaPlayer.OnCompletionListener mOnCompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    // Checking the AudioFocus Changes
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    // if the audio needs to be stoped in between playing

                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                        focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);

                    }
                    // resume playing after the audio is changes
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        mMediaPlayer.start();

                    }
                    // releasing MediaPlayer after the loss of AudioFocus
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                       releaseMediaPlayer();
                    }

                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        //Declearing The AudioManager instance to get the AUDIO_SERVICE
        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        // String [] words = {"One","two","Three","Four","Five","Six","Seven","Eight","Nine","Ten"};
       final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("One", "Lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("Two", "Ottiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("Three", "Tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("Four", "Oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("Five", "Massoka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("Six", "Temmoka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("Eight", "Kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("Nine", "Wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("Ten", "Na'accha", R.drawable.number_ten, R.raw.number_ten));
        // Create {An Arraylist} whose data source is a list of string, The
        // adaptor knows how to create a Layout for each item in the List
        //Using The
        WordAdapter Adapter = new WordAdapter(this, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();
                // Checking return for requestAudioFocus
                int result = myAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN);
                // id we get Succesful AudioFocus
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this,word.getmAudioId());
                    mMediaPlayer.start();
                }
                //Toast.makeText(PhrasesActivity.this, "hlo", Toast.LENGTH_SHORT).show();

                mMediaPlayer.setOnCompletionListener(mOnCompletion);

            }
        });

    }
    // to stop playing is the app gets stoped

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();
            Toast.makeText(NumbersActivity.this, "Media Palyer Released", Toast.LENGTH_SHORT).show();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            // abandoning the AudioFocus after the MediaPlayer is released
            myAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}



