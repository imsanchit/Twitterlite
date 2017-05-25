package com.example.sanchit.twitterlite.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

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
import com.example.sanchit.twitterlite.Adapter.HashtagAdapter;
import com.example.sanchit.twitterlite.Adapter.customNewsAdapter;
import com.example.sanchit.twitterlite.R;
import com.example.sanchit.twitterlite.RequestHandler.CustomRequest1;
import com.example.sanchit.twitterlite.SharedPrefrence.UserLocalStore;
import com.example.sanchit.twitterlite.Utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class HashtagResult extends AppCompatActivity {

    ArrayList<String> list1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashtag_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i= getIntent();
        String a  = i.getStringExtra("name1");

/*        String url1 = "http://139.59.14.66:5000/hashtag/#"+a;



        Map<String, String> params = new HashMap<String, String>();


        final android.app.AlertDialog dialog = new SpotsDialog(this, R.style.Custom);
        dialog.show();


        CustomRequest1 jsObjRequest = new CustomRequest1(Request.Method.GET, url1, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                //String body = response.toString();
                //System.out.println("response is : "+response);

                try {

                    JSONArray obj = (JSONArray) response.get("hashtag");


//                    JSONArray obj = (JSONArray) response.get("tweets");
  //                  total = obj.length();



                    for (int i = 0; i < 12; i++) {
                        JSONObject item = obj.getJSONObject(i);
                        //    System.out.println("Item is : "+item);
                        try {
                            String tweet1 = item.get("headline").toString();
                            String tweet2 = item.get("description").toString();


                            System.out.println("Headline is : " + tweet1);


                            list1.add(tweet1);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                                    ListAdapter adpt = new HashtagAdapter(HashtagResult.this,list1);
                                                    ListView li = (ListView)findViewById(R.id.listView);
                                                li.setAdapter(adpt);


                } catch (Exception e) {
                }
                dialog.hide();


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    new Util().showerrormessage(HashtagResult.this, "Time Out Error.....Try Later!!!");

                } else if (error instanceof AuthFailureError) {

                    new Util().showerrormessage(HashtagResult.this, "Authentication Error.....Try Later!!!");
                } else if (error instanceof ServerError) {

                    new Util().showerrormessage(HashtagResult.this, "Server Error.....Try Later!!!");
                } else if (error instanceof NetworkError) {

                    new Util().showerrormessage(HashtagResult.this, "Network Error.....Try Later!!!");
                } else if (error instanceof ParseError) {

                    Log.d("Response: ", error.toString());
                    System.out.println("Response error: " + error.toString());
                    new Util().showerrormessage(HashtagResult.this, "Unknown Error.....Try Later!!!");
                }
            }
        });
        RequestQueue queue = Volley.newRequestQueue(HashtagResult.this);
        queue.add(jsObjRequest);
*/
    }
}