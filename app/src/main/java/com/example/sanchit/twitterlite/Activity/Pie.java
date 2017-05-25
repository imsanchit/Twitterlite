package com.example.sanchit.twitterlite.Activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.example.sanchit.twitterlite.NaiveBayes;
import com.example.sanchit.twitterlite.NaiveBayesExample;
import com.example.sanchit.twitterlite.NaiveBayesKnowledgeBase;
import com.example.sanchit.twitterlite.R;
import com.example.sanchit.twitterlite.RequestHandler.CustomRequest;
import com.example.sanchit.twitterlite.RequestHandler.CustomRequest1;
import com.example.sanchit.twitterlite.SharedPrefrence.UserLocalStore;
import com.example.sanchit.twitterlite.Utils.Util;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import twitter4j.User;


public class Pie extends AppCompatActivity {

    public static String TAG = "PieChart";
    int entertainment_count=0,sports_count=0,politics_count=0, tech_count=0, business_count=0,total;

    private int[] yData = {25, 10, 66, 44, 46};
    private String[] xData = {"Entertainment" , "Sports", "Politics", "Technology" , "Business" };
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);




        Toast.makeText(Pie.this, "Training happening", Toast.LENGTH_SHORT).show();




        final android.app.AlertDialog dialog = new SpotsDialog(this, R.style.Custom);
        dialog.show();

//        String url = "http://139.59.14.66:5000/tweets/imsanchit1995";

        UserLocalStore userLocalStore = new UserLocalStore(Pie.this);
        String a = userLocalStore.getScreen();
        String url = "http://139.59.14.66:5000/tweets/"+a;//imVkohli";

        Map<String, String> params = new HashMap<String, String>();


        System.out.println("str0" + params.toString());

        CustomRequest1 jsObjRequest = new CustomRequest1(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                String body = response.toString();
                //System.out.println("response is : "+response);

                try {


                    JSONArray obj = (JSONArray) response.get("tweets");
                  //  System.out.println("tweets is : "+obj);
                    total = obj.length();



                    Map<String, URL> trainingFiles = new HashMap<>();
                    trainingFiles.put("Entertainment", NaiveBayesExample.class.getResource("entertainment.txt"));
                    trainingFiles.put("Sports", NaiveBayesExample.class.getResource("sports.txt"));
                    trainingFiles.put("politics", NaiveBayesExample.class.getResource("politics.txt"));
                    trainingFiles.put("business", NaiveBayesExample.class.getResource("business.txt"));
                    trainingFiles.put("tech", NaiveBayesExample.class.getResource("tech.txt"));
                    Map<String, String[]> trainingExamples = new HashMap<>();


                    try {
                        trainingExamples.put("Entertainment", readLines("entertainment.txt",getApplicationContext()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        trainingExamples.put("Sports", readLines("sports.txt",getApplicationContext()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        trainingExamples.put("politics", readLines("politics.txt",getApplicationContext()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        trainingExamples.put("business", readLines("business.txt",getApplicationContext()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        trainingExamples.put("tech", readLines("tech.txt",getApplicationContext()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }




                    NaiveBayes nb = new NaiveBayes();
                    nb.setChisquareCriticalValue(6.63);
                    nb.train(trainingExamples);


                    Toast.makeText(Pie.this, "Training done", Toast.LENGTH_SHORT).show();


                    NaiveBayesKnowledgeBase knowledgeBase = nb.getKnowledgeBase();

                    nb = null;
                    trainingExamples = null;

                    nb = new NaiveBayes(knowledgeBase);


                    try {
                        for (int j = 0; j < obj.length(); j++) {
                            String json = obj.get(j).toString();

                    //        System.out.println("Titles are : "+json);


//                            String exampleEn = "long match cricket football";
                            String outputEn = nb.predict(json);

                      //      System.out.println("Output is : "+outputEn);



                        if(outputEn.equals("Entertainment")){
                                entertainment_count++;
                        }else if (outputEn.equals("Sports")){
                                sports_count++;
                        }else if (outputEn.equals("politics")){
                                politics_count++;
                        }else if (outputEn.equals("business")){
                                business_count++;
                        }else{
                                tech_count++;
                        }
                        System.out.println("The sentense %s was classified as %s" + json + outputEn);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    System.out.println("entertainmentcount : " + entertainment_count);
                    System.out.println("sportscount : " + sports_count);
                    System.out.println("politics_count : " + politics_count);
                    System.out.println("tech_count : " + tech_count);
                    System.out.println("business_count : " + business_count);
                    System.out.println("total : " + total);


                    yData[0] = (entertainment_count*100)/total;
                    yData[1] = (sports_count*100)/total;
                    yData[2] = (politics_count*100)/total;
                    yData[3] = (tech_count*100)/total;
                    yData[4] = (business_count*100)/total;
                   /*yData[0] = 200/100;
                          yData[1] = 100/100;
                        yData[2] = 25/100;
                      yData[3] = 25/100;
                    yData[4] = 25/100;*/

                  UserLocalStore userLocalStore = new UserLocalStore(Pie.this);
                  userLocalStore = new UserLocalStore(Pie.this);

                    userLocalStore.setEntertariment(yData[0]);
                    userLocalStore.setSports(yData[1]);
                    userLocalStore.setPolitics(yData[2]);
                    userLocalStore.setTechnology(yData[3]);
                    userLocalStore.setBusiness(yData[4]);


                    dialog.hide();
                    pieChart = (PieChart) findViewById(R.id.iPieChart);

                    pieChart.setRotationEnabled(true);

                    pieChart.setHoleRadius(25f);
                    pieChart.setTransparentCircleAlpha(0);
                    pieChart.setCenterText("Your Intrest");
                    pieChart.setCenterTextSize(10);

                    addDataSet();

                    pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                        @Override
                        public void onValueSelected(Entry e, Highlight h) {


                            int pos1 = e.toString().indexOf("(sum): ");
                            String sales = e.toString().substring(pos1 + 7);

                            sales = sales.replace(".0","");
                            sales = sales.replace(" x: 0 y: ","");


                            for(int i = 0; i < yData.length; i++){

                                if(yData[i] == Integer.parseInt(sales)){
                                    pos1 = i;
                                    break;
                                }
                            }
                            System.out.println("position is"+pos1);
                            String employee = xData[pos1];
                            Toast.makeText(Pie.this, "Category " + employee + "\n" + " Interested : " + sales + "%", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected() {

                        }
                    });

                }
                catch (Exception e){
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    new Util().showerrormessage(Pie.this, "Time Out Error.....Try Later!!!");

                } else if (error instanceof AuthFailureError) {

                    new Util().showerrormessage(Pie.this, "Authentication Error.....Try Later!!!");
                } else if (error instanceof ServerError) {

                    new Util().showerrormessage(Pie.this, "Server Error.....Try Later!!!");
                } else if (error instanceof NetworkError) {

                    new Util().showerrormessage(Pie.this, "Network Error.....Try Later!!!");
                } else if (error instanceof ParseError) {

                    Log.d("Response: ", error.toString());
                    System.out.println("Response error: " + error.toString());
                    new Util().showerrormessage(Pie.this, "Unknown Error.....Try Later!!!");
                }
            }
        });
        RequestQueue queue = Volley.newRequestQueue(Pie.this);
        queue.add(jsObjRequest);

    }

    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Categories");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }



    public static String[] readLines(String url, Context applicationContext) throws IOException {

        AssetManager assetManager = applicationContext.getAssets();
        InputStream inputStream = assetManager.open(url);
        Reader fileReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        List<String> lines;
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            lines = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
                //		System.out.println(line);
            }
        }

        return lines.toArray(new String[lines.size()]);
    }

}