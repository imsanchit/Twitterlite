package com.example.sanchit.twitterlite.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

public class News extends AppCompatActivity {

    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        UserLocalStore userLocalStore = new UserLocalStore(News.this);

        int ent = userLocalStore.getEntertariment();
        int sport = userLocalStore.getSports();
        int pol = userLocalStore.getPolitics();
        int tech = userLocalStore.getTechnology();
        int bus = userLocalStore.getBusiness();
        int values[] = {ent, sport, pol, tech, bus};

        int largestA = values[0];
        int largestB = -1;
        for (int i = 0; i < 5; i++) {

            if (values[i] > largestA) {
                largestB = largestA;
                largestA = values[i];
            } else if (values[i] > largestB && values[i] != largestA) {
                largestB = values[i];
            }

        }

        String url1 = "";
        System.out.println("Highest is : " + largestA);
        if (largestA == ent)
            url1 = "http://139.59.14.66:5000/entertainmentnews";
        else if (largestA == sport)
            url1 = "http://139.59.14.66:5000/sportsnews";
        else if (largestA == pol)
            url1 = "http://139.59.14.66:5000/politicsnews";
        else if (largestA == tech)
            url1 = "http://139.59.14.66:5000/technologynews";
        else if (largestA == bus)
            url1 = "http://139.59.14.66:5000/businessnews";

        String url2 = "";
        if (largestB == ent)
            url2 = "http://139.59.14.66:5000/entertainmentnews";
        else if (largestB == sport)
            url2 = "http://139.59.14.66:5000/sportsnews";
        else if (largestB == pol)
            url2 = "http://139.59.14.66:5000/politicsnews";
        else if (largestB == tech)
            url2 = "http://139.59.14.66:5000/technologynews";
        else if (largestB == bus)
            url2 = "http://139.59.14.66:5000/businessnews";

        Map<String, String> params = new HashMap<String, String>();


        final android.app.AlertDialog dialog = new SpotsDialog(this, R.style.Custom);
        dialog.show();


        CustomRequest1 jsObjRequest = new CustomRequest1(Request.Method.GET, url1, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                //String body = response.toString();
                //System.out.println("response is : "+response);

                try {

                    JSONArray obj = (JSONArray) response.get("news");

                    for (int i = 0; i < 12; i++) {
                        JSONObject item = obj.getJSONObject(i);
                        //    System.out.println("Item is : "+item);
                        try {
                            String tweet1 = item.get("headline").toString();
                            String tweet2 = item.get("description").toString();


                            System.out.println("Headline is : " + tweet1);
                            System.out.println("Description is : " + tweet2);


                            list1.add(tweet1);
                            list2.add(tweet2);
                            //getActivity().setTitle("Electronic Resources");
                            //   ((Page2)getActivity()).setActionBarTitle("Fragment 2");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
    //                ListAdapter adpt = new customNewsAdapter(News.this,list1, list2);
      //                                ListView li = (ListView)findViewById(R.id.listView);
        //                            li.setAdapter(adpt);


                } catch (Exception e) {
                }
//                dialog.hide();


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    new Util().showerrormessage(News.this, "Time Out Error.....Try Later!!!");

                } else if (error instanceof AuthFailureError) {

                    new Util().showerrormessage(News.this, "Authentication Error.....Try Later!!!");
                } else if (error instanceof ServerError) {

                    new Util().showerrormessage(News.this, "Server Error.....Try Later!!!");
                } else if (error instanceof NetworkError) {

                    new Util().showerrormessage(News.this, "Network Error.....Try Later!!!");
                } else if (error instanceof ParseError) {

                    Log.d("Response: ", error.toString());
                    System.out.println("Response error: " + error.toString());
                    new Util().showerrormessage(News.this, "Unknown Error.....Try Later!!!");
                }
            }
        });
        RequestQueue queue = Volley.newRequestQueue(News.this);
        queue.add(jsObjRequest);


        CustomRequest1 jsObjRequest1 = new CustomRequest1(Request.Method.GET, url2, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                //String body = response.toString();
                //System.out.println("response is : "+response);

                try {

                    JSONArray obj = (JSONArray) response.get("news");

                    for (int i = 0; i < 8; i++) {
                        JSONObject item = obj.getJSONObject(i);
                        //    System.out.println("Item is : "+item);
                        try {
                            String tweet1 = item.get("headline").toString();
                            String tweet2 = item.get("description").toString();


                            System.out.println("Headline is 2 : " + tweet1);
                            System.out.println("Description is 2 : " + tweet2);


                            list1.add(tweet1);
                            list2.add(tweet2);
                            //getActivity().setTitle("Electronic Resources");
                            //   ((Page2)getActivity()).setActionBarTitle("Fragment 2");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    ListAdapter adpt = new customNewsAdapter(News.this, list1, list2);
                    ListView li = (ListView) findViewById(R.id.listView);
                    li.setAdapter(adpt);


                } catch (Exception e) {
                }
                dialog.hide();


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    new Util().showerrormessage(News.this, "Time Out Error.....Try Later!!!");

                } else if (error instanceof AuthFailureError) {

                    new Util().showerrormessage(News.this, "Authentication Error.....Try Later!!!");
                } else if (error instanceof ServerError) {

                    new Util().showerrormessage(News.this, "Server Error.....Try Later!!!");
                } else if (error instanceof NetworkError) {

                    new Util().showerrormessage(News.this, "Network Error.....Try Later!!!");
                } else if (error instanceof ParseError) {

                    Log.d("Response: ", error.toString());
                    System.out.println("Response error: " + error.toString());
                    new Util().showerrormessage(News.this, "Unknown Error.....Try Later!!!");
                }
            }
        });
        RequestQueue queue1 = Volley.newRequestQueue(News.this);
        queue1.add(jsObjRequest1);

    }
}