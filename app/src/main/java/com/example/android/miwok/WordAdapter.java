package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

MediaPlayer player;

    //    AudioManager mAudioManager;
    public WordAdapter(Activity context, ArrayList<Word> androidFlavors) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, androidFlavors);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView mewotextView = (TextView) listItemView.findViewById(R.id.mewak_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        mewotextView.setText(currentWord.getmMiwakTranslaction());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        defaultTextView.setText(currentWord.getmDefaultTranslation());

        // Find the ImageView int the list_item.xml layout with the ID version_number
        ImageView imgID = (ImageView) listItemView.findViewById(R.id.photu);
//          Get the version number from the current Word object and
//          set this text on the number Imageview
        if (currentWord.isImage()) {
            // adds
            imgID.setImageResource(currentWord.getImgResourceId());
            imgID.setVisibility(View.VISIBLE);
        } else {
            // removes
            imgID.setVisibility(View.GONE);
        }
//        ImageButton button = (ImageButton) listItemView.findViewById(R.id.play);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (player == null) {
//                    // using a factory methode
//                    player = MediaPlayer.create(getContext(), currentWord.getmAudioId());
//                    // when media player finishes a song
//                    player.setOnCompletionListener(mediaPlayer -> {
//
//                        freePlayer();
//
//                    });
//                } else {
//                    player.release();
//                    player = null;
//                    player = MediaPlayer.create(getContext(), currentWord.getmAudioId());
//                }
//
//                player.start();
//            }
//        });

         //Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

//    public void freePlayer() {
//
//        if (player != null) {
//
//            player.release();
//            player = null;
//            Toast.makeText(getContext(), "Media Palyer Released", Toast.LENGTH_SHORT).show();
//        }
//    }
}




