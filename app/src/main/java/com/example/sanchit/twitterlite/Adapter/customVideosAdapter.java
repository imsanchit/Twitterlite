package com.example.sanchit.twitterlite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanchit.twitterlite.PlayerConfig;
import com.example.sanchit.twitterlite.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;


public class customVideosAdapter extends ArrayAdapter<String>{
    ArrayList<String> list1 = new ArrayList<>();
    //public static final String VIDEO_ID = "YOUR VIDEO ID";
    Button button;

    public customVideosAdapter(Context context, ArrayList<String> name) {

        super(context, R.layout.custom_videos ,name);
        list1 = name;
        System.out.println("List 1 is "+list1);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_videos, parent, false);

        button = (Button) customView.findViewById(R.id.button);

                //QDRig7DtUX8
        TextView textView = (TextView) customView.findViewById(R.id.textView);
        textView.setText("https://www.youtube.com"+list1.get(position));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = list1.get(position);
                a = a.substring(9);

                Intent i = new Intent(getContext(), PlayVideo.class);
                i.putExtra("name",a);
                getContext().startActivity(i);

            }
        });

//        textView1.setText(list1.get(position));

        return customView;
    }
}