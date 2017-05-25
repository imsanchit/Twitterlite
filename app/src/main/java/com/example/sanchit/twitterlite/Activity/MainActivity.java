package com.example.sanchit.twitterlite.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sanchit.twitterlite.R;
import com.example.sanchit.twitterlite.SharedPrefrence.UserLocalStore;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class MainActivity extends AppCompatActivity {

    static String TWITTER_CONSUMER_KEY = "5d1xJi2gUP2L6B1JbKArPhQRk";
    static String TWITTER_CONSUMER_SECRET = "4NMN2g1uPBFs4qEGKZRMhTUQXRZ4yDi6B7NtsynjdWPmKBBqOL";

    static String PREFERENCE_NAME = "twitter_oauth";

    UserLocalStore userLocalStore;
    static final String TWITTER_CALLBACK_URL = "oauth://t4jsample";

    Button btnLoginTwitter;

    private static Twitter twitter;
    private static RequestToken requestToken;
    private static AccessToken accessToken;

    // Twitter oauth urls
    static final String URL_TWITTER_AUTH = "auth_url";
    static final String URL_TWITTER_OAUTH_VERIFIER = "oauth_verifier";
    static final String URL_TWITTER_OAUTH_TOKEN = "oauth_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userLocalStore = new UserLocalStore(MainActivity.this);

        final EditText editText = (EditText)findViewById(R.id.editText);


        btnLoginTwitter = (Button) findViewById(R.id.button);


        btnLoginTwitter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String a = editText.getText().toString();
                userLocalStore.setScreen(a);

                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
            }
        });




  /*      btnLoginTwitter = (Button) findViewById(R.id.btnLoginTwitter);


        btnLoginTwitter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Call login twitter function
                loginToTwitter();
            }
        });
/*
        if (!new UserLocalStore(this).getuserloggedIn()) {



            Uri uri = getIntent().getData();
            if (uri != null && uri.toString().startsWith(TWITTER_CALLBACK_URL)) {
                // oAuth verifier
                String verifier = uri.getQueryParameter(URL_TWITTER_OAUTH_VERIFIER);

                try {
                    // Get the access token
                    System.out.println("Access" + requestToken);
                    MainActivity.this.accessToken = twitter.getOAuthAccessToken(requestToken,verifier);
                    System.out.println("Access" + accessToken);

                    long userID = accessToken.getUserId();
                    User user = twitter.showUser(userID);

                    UserLocalStore userLocalStore = new UserLocalStore(this);
                    userLocalStore.setUserloggedIn(true);
                    userLocalStore.userData(accessToken.getToken(),accessToken.getTokenSecret(),user.getName(),user.getScreenName());

                    startActivity(new Intent(this,Home.class));

                } catch (Exception e) {
                    // Check log for login errors
                    System.out.println("Access is " + e.getMessage());
                }
            }
        }*/

    }

    private void loginToTwitter() {
        // Check if already logged in
        if (!new UserLocalStore(getApplicationContext()).getuserloggedIn()) {
            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
            builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);
            Configuration configuration = builder.build();

            TwitterFactory factory = new TwitterFactory(configuration);
            twitter = factory.getInstance();

            Thread thread = new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        requestToken = twitter
                                .getOAuthRequestToken(TWITTER_CALLBACK_URL);
                        MainActivity.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                                .parse(requestToken.getAuthenticationURL())));


                    } catch (TwitterException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        } else {
            // user already logged into twitter
            Toast.makeText(getApplicationContext(),
                    "Already Logged into twitter", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onResume() {

        UserLocalStore userLocalStore;
        userLocalStore = new UserLocalStore(this);
        super.onResume();
        if (userLocalStore.getuserloggedIn())
        {

            //    startActivity(new Intent(this,Admin.class));


        }

    }
}