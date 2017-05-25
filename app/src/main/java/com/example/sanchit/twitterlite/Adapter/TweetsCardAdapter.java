package com.example.sanchit.twitterlite.Adapter;

        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.ImageLoader;
        import com.example.sanchit.twitterlite.DownloadImageTask;
        import com.example.sanchit.twitterlite.R;

        import org.json.JSONArray;

public class TweetsCardAdapter extends RecyclerView.Adapter<TweetsCardAdapter.ViewHolder> {

    JSONArray array;
    public static String[] ar1, ar2, ar3, ar4, ar5, ar6, ar7, ar8;
    int count;
    Context ctx;
    ImageView imageView1;
    String link,name;

    public TweetsCardAdapter(Context context, String[] a1, String[] a2, String[] a3, String[] a4, String[] a5, String[] a6, String[] a7, String[] a8, int count) {
        ar1 = a1;
        ar2 = a2;
        ar3 = a3;
        ar4 = a4;
        ar5 = a5;
        ar6 = a6;
        ar7 = a7;
        ar8 = a8;
        this.count = count;
        ctx = context;

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView1, textView2, textView3;
        public TextView textView4;

        public ViewHolder(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.textView1);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);
            textView3 = (TextView) itemView.findViewById(R.id.retweet_count);
            textView4 = (TextView) itemView.findViewById(R.id.favourite_count);
            imageView1 = (ImageView)itemView.findViewById(R.id.profile);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str;
                    if(name.equalsIgnoreCase("Virat"))
                        str= "https://twitter.com/imsanchit1995/status/"+link;
                    else if(name.equalsIgnoreCase("dhoni"))
                        str= "https://twitter.com/msdhoni/status/"+link;
                    else if(name.equalsIgnoreCase("modi"))
                        str= "https://twitter.com/narendramodi/status/"+link;
                    else
                        str= "https://twitter.com/BarackObama/status/"+link;
                    Uri uri = Uri.parse(str); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(intent);}
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tweets_card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.textView1.setText(ar1[position]);
        viewHolder.textView2.setText(ar2[position]);
        viewHolder.textView3.setText(ar3[position]);
        viewHolder.textView4.setText(ar4[position]);
        link = ar7[position];
        name = ar8[position];
        ar5[position] = ar5[position].replace("_normal","");

        new DownloadImageTask(imageView1)
                .execute(ar5[position]);
    }

    @Override
    public int getItemCount() {
        return count;
    }
}