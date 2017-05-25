package com.example.sanchit.twitterlite;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Color;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.BaseAdapter;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;


        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Map;

public class AllocateListAdapter extends BaseAdapter {


    private ArrayList<String> list;
    LayoutInflater inflater;
    private Context activity;
    int currentpos = 0;


    public AllocateListAdapter(Context a, ArrayList<String> list) {
        activity = a;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.allocatelist_custom, null);


        TextView dustbin=(TextView) vi.findViewById(R.id.text1);
        dustbin.setText(list.get(position));



        return vi;
    }


}