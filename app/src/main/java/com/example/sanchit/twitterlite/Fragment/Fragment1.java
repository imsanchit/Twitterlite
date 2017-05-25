package com.example.sanchit.twitterlite.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanchit.twitterlite.Activity.Pie;
import com.example.sanchit.twitterlite.Adapter.TweetsCardAdapter;
import com.example.sanchit.twitterlite.R;
import com.example.sanchit.twitterlite.SharedPrefrence.UserLocalStore;
import com.example.sanchit.twitterlite.Utils.Constants;
import com.example.sanchit.twitterlite.Utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Fragment1 extends Fragment {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    UserLocalStore userLocalStore;
    TextView callDetails;
    String aa;
    Cursor mCursor;
    private final OAuthService s = new ServiceBuilder()
            .provider(TwitterApi.SSL.class)
            .apiKey(Constants.API_KEY)
            .apiSecret(Constants.API_SECRET)
            .callback(Constants.CALLBACKURL)
            .build();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment1, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

      //          Toast.makeText(getActivity(), "click",Toast.LENGTH_SHORT).show();
  //              startActivity(new Intent(getActivity(), PieChart.class));

                Intent pieChartIntent=new Intent(getActivity(),Pie.class);
                startActivity(pieChartIntent);

            }
        });


        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        userLocalStore = new UserLocalStore(getContext());
        func();

        if (new Util().check_connection(getActivity())) {

            try {
                String user_data = new getTweets1().execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        else
            func();

        return view;
    }

    public class getTweets1 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            SharedPreferences settings = getActivity().getSharedPreferences(Constants.PREFS_NAME, 0);
            String a = userLocalStore.getScreen();
//            String str = +a;//pranay1803
            Token newAccessToken = new Token(settings.getString("accessToken", "743153484254453760-16Q490ox3RaP1qtTBXDJhZrEpWirW4x"), settings.getString("accessSecret", "dXWtKYQyOuSnlfFvvYZsaV9Bb5submGJU2nqJM8CZRrOf"));
            final OAuthRequest request = new OAuthRequest(Verb.GET,"https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name="+a);
            s.signRequest(newAccessToken, request);
            org.scribe.model.Response response = request.send();
            final Activity ac = getActivity();
            String body = response.getBody();
            userLocalStore.setResponse1(body);
            func();
            return body;
        }
    }

    void func(){
        String body1 = userLocalStore.getResponse1();

        try {
            JSONArray data_list = new JSONArray(body1);
            final ArrayList<String> items1 = new ArrayList<String>();
            final ArrayList<String> items2 = new ArrayList<String>();
            final ArrayList<String> items3 = new ArrayList<String>();
            final ArrayList<String> items4 = new ArrayList<String>();
            final ArrayList<String> items5 = new ArrayList<String>();
            final ArrayList<String> items6 = new ArrayList<String>();
            final ArrayList<String> items7 = new ArrayList<String>();
            final ArrayList<String> items8 = new ArrayList<String>();


            int len = data_list.length();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < len; i++) {
                JSONObject item = data_list.getJSONObject(i);
                //    System.out.println("Item is : "+item);
                try {
                    String tweet5=null;
                    String tweet6=null;
                    String tweet1 = item.get("text").toString();
                    String tweet2 = item.get("created_at").toString();
                    String tweet3 = item.get("retweet_count").toString();
                    String tweet4 = item.get("favorite_count").toString();
                    String tweet7 = item.get("id").toString();
                    String tweet8 = "Virat";
                    JSONObject obj = (JSONObject) item.get("user");


                    try {
                        tweet5 = obj.get("profile_image_url").toString();
                        tweet6 = obj.get("profile_background_image_url").toString();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    items1.add(tweet1);
                    items2.add(tweet2);
                    items3.add(tweet3);
                    items4.add(tweet4);
                    items5.add(tweet5);
                    items6.add(tweet6);
                    items7.add(tweet7);
                    items8.add(tweet8);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String[] ar1 = items1.toArray(new String[items1.size()]);
                    String[] ar2 = items2.toArray(new String[items2.size()]);
                    String[] ar3 = items3.toArray(new String[items3.size()]);
                    String[] ar4 = items4.toArray(new String[items4.size()]);
                    String[] ar5 = items5.toArray(new String[items5.size()]);
                    String[] ar6 = items6.toArray(new String[items6.size()]);
                    String[] ar7 = items7.toArray(new String[items7.size()]);
                    String[] ar8 = items8.toArray(new String[items8.size()]);

                    TweetsCardAdapter a = new TweetsCardAdapter(getActivity().getApplicationContext(), ar1, ar2, ar3, ar4, ar5, ar6, ar7, ar8, ar1.length);
                    recyclerView.setAdapter(a);
                }
            });

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}