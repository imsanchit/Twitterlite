package com.example.sanchit.twitterlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sanchit.twitterlite.R;

import java.util.ArrayList;


public class HashtagAdapter extends ArrayAdapter<String>{
    ArrayList<String> list1 = new ArrayList<>();

    public HashtagAdapter(Context context, ArrayList<String> name) {

        super(context, R.layout.custom_hashtag ,name);
        list1 = name;

        System.out.println("List 1 is "+list1);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_hashtag, parent, false);

        TextView textView1 = (TextView)customView.findViewById(R.id.textView1);


        textView1.setText(list1.get(position));
//        new DownloadImageTask(imageView)
  //              .execute(list3.get(position));

        return customView;
    }
}