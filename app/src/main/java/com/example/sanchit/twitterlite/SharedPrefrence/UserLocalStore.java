package com.example.sanchit.twitterlite.SharedPrefrence;

        import android.content.Context;
        import android.content.SharedPreferences;

        import java.util.ArrayList;
        import java.util.HashSet;
        import java.util.Set;

public class UserLocalStore {

    public static final String SP_Name = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context)
    {
        userLocalDatabase = context.getSharedPreferences(SP_Name, 0);
    }

    public void userData(String token,String secrettoken,String username,String screenname)
    {
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putString("oauth_token",token);
        speditor.putString("oauth_token_secret",secrettoken);
        speditor.putString("username",username);
        speditor.putString("screenname",screenname);
        speditor.commit();
    }

    public String getUsername(){
        String name = userLocalDatabase.getString("username", "");
        System.out.println("status"+ name);
        return name;
    }



    public void setScreen(String response1){
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putString("screen",response1);
        speditor.commit();
    }

    public String getScreen(){

        String name = userLocalDatabase.getString("screen", "");
        return name;
    }


    public void setUserloggedIn(boolean loggedIn){
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putBoolean("loggedIn",loggedIn);
        speditor.commit();

    }



    public boolean getuserloggedIn(){

        if(userLocalDatabase.getBoolean("loggedIn",false) == true)
            return true;
        else
            return false;
    }



    public void clearUserdata(){

        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.clear();
        speditor.commit();

    }



    public void setResponse1(String response1){
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putString("response1",response1);
        speditor.commit();
    }

    public String getResponse1(){

        String name = userLocalDatabase.getString("response1", "");
        return name;
    }

    public void setEntertariment(int count)
    {
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putInt("ent", count);
        speditor.commit();
    }
    public int getEntertariment(){
        int a = userLocalDatabase.getInt("ent",0);
        return a;
    }


    public void setSports(int count)
    {
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putInt("sports", count);
        speditor.commit();
    }
    public int getSports(){
        int a = userLocalDatabase.getInt("sports",0);
        return a;
    }


    public void setPolitics(int count)
    {
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putInt("pol", count);
        speditor.commit();
    }
    public int getPolitics(){
        int a = userLocalDatabase.getInt("pol",0);
        return a;
    }

    public void setTechnology(int count)
    {
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putInt("tech", count);
        speditor.commit();
    }
    public int getTechnology(){
        int a = userLocalDatabase.getInt("tech",0);
        return a;
    }


    public void setBusiness(int count)
    {
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putInt("bus", count);
        speditor.commit();
    }
    public int getBusiness(){
        int a = userLocalDatabase.getInt("bus",0);
        return a;
    }



}