package com.example.sanchit.twitterlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sanchit.twitterlite.R;

import java.util.ArrayList;


public class customNewsAdapter extends ArrayAdapter<String>{
    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();

    public customNewsAdapter(Context context, ArrayList<String> name, ArrayList<String> author) {

        super(context, R.layout.custom_news ,name);
        list1 = name;
        list2 = author;

        System.out.println("List 1 is "+list1);
        System.out.println("List 2 is "+list2);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_news, parent, false);

        TextView textView1 = (TextView)customView.findViewById(R.id.textView1);
        TextView textView2 = (TextView)customView.findViewById(R.id.textView2);


        textView1.setText(list1.get(position));
        textView2.setText(list2.get(position));
//        new DownloadImageTask(imageView)
  //              .execute(list3.get(position));

        return customView;
    }
}