package com.example.sanchit.twitterlite.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanchit.twitterlite.Activity.News;
import com.example.sanchit.twitterlite.Activity.Videos;
import com.example.sanchit.twitterlite.R;
import com.example.sanchit.twitterlite.SharedPrefrence.UserLocalStore;

public class Fragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

    TextView textView = (TextView)view.findViewById(R.id.textView2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
   //             Toast.makeText(getActivity(), "news", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), News.class);
                startActivity(intent);
            }
        });

        TextView textView1 = (TextView)view.findViewById(R.id.textView);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
     //           Toast.makeText(getActivity(), "videos", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Videos.class);
                startActivity(intent);
            }
        });

        /*        LinearLayout app_layer = (LinearLayout) view.findViewById (R.id.ll1);
        app_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("LL1 clicked");
                Toast.makeText(getActivity(), "news", Toast.LENGTH_SHORT).show();
            }
        });*/

        return view;
    }
}