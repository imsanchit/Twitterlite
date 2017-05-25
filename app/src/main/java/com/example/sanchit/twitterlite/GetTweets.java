package com.example.sanchit.twitterlite;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.os.AsyncTask;
        import android.util.Log;

        import org.json.JSONException;
        import org.json.JSONObject;
        import org.scribe.model.OAuthRequest;
        import org.scribe.model.Token;
        import org.scribe.model.Verb;
        import org.scribe.oauth.OAuthService;

        import java.io.BufferedReader;
        import java.io.DataOutputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;

public class GetTweets {

    ProgressDialog progressDialog;

    public GetTweets(Context context){

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Uploading....");
        progressDialog.setMessage("Please Wait");



    }


    public void fetchuserdatainbackground(Context context, OAuthService s, GetResult getUploadResult){
        progressDialog.show();
        new fetchuserdataasynctask(context,s,getUploadResult).execute();
    }



    public class fetchuserdataasynctask extends AsyncTask<Void,Void,String> {

        Context context;
        OAuthService s;
        GetResult getUploadResult;

        public fetchuserdataasynctask(Context context,OAuthService s,GetResult getUploadResult){

            this.s = s;
            this.context = context;
            this.getUploadResult = getUploadResult;
        }

        @Override
        protected String doInBackground(Void... voids) {

            SharedPreferences settings = context.getSharedPreferences("hello_world_app", 0);
            Token newAccessToken = new Token(settings.getString("accessToken", "275893118-VIdZtKgsKz9tXZrbAvoMMfCqGiAgAFzSjfYgeWIR"), settings.getString("accessSecret", "O3Xz93WHLS0fit1wXZM0OFAIuH56hWTKeHxNUJpRdWDSA"));
            final OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=msdhoni");
            s.signRequest(newAccessToken, request);
            org.scribe.model.Response response = request.send();
            //      final Activity ac = getActivity();
            String body = response.getBody();
            System.out.println("Access is " + body);
            // userLocalStore.setResponse2(body);
            return body;


        }





        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(null);
            progressDialog.dismiss();

            System.out.println("Response filed is" + response);

            getUploadResult.done(response);


        }
    }



}
