package com.example.sanchit.twitterlite.Fragment;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.sanchit.twitterlite.Activity.Home;
import com.example.sanchit.twitterlite.Adapter.PlayVideo;
import com.example.sanchit.twitterlite.R;
import com.example.sanchit.twitterlite.SharedPrefrence.UserLocalStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class Fragment3 extends Fragment {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    UserLocalStore userLocalStore;
    Spinner s1,s2,s3,s4,s5,s6,s7;
    String data1,data2,data3,data4,data5,data6,data7,data8,data9,data10,data11,data12;
    EditText ed1,ed2;
    Button btnTimePicker1,btnTimePicker2,btnTimePicker3;
    EditText txtTime1,txtTime2,txtTime3;
    private int mHour, mMinute;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(getContext());

        final EditText editText = (EditText)view.findViewById(R.id.editText);

        Button btnLoginTwitter = (Button) view.findViewById(R.id.button);


        btnLoginTwitter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getContext(), HashtagResult.class);
                intent.putExtra("name1",editText.getText().toString());
                getContext().startActivity(intent);


                startActivity(intent);
            }
        });



        return view;
    }
}