package com.example.sanchit.twitterlite.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sanchit.twitterlite.AllocateListAdapter;
import com.example.sanchit.twitterlite.R;
import com.example.sanchit.twitterlite.SharedPrefrence.UserLocalStore;

import android.widget.ListView;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.oauth.OAuthService;
import com.example.sanchit.twitterlite.Adapter.customTabAdapter;

public class Home extends AppCompatActivity {

    ListView li;
    AllocateListAdapter allocateListAdapter;
    int entertainment_count,sports_count,politics_count, tech_count, business_count;

    TabLayout tabLayout;
    ViewPager viewPager;
    customTabAdapter customTabAdapter;
    UserLocalStore userLocalStore;
    OAuthService s = new ServiceBuilder()
            .provider(TwitterApi.SSL.class)
            .apiKey("369918635-M0ouzDFYCSCCD3fEQtTaNTjDLxL0zUO8nJYCzoR6")
            .apiSecret("aelBtZ3XkBxALwP2rFNHsQQbrgZKdipsVdeAh15VH5Fms")
            .callback("app://twitter-dev")
            .build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);


        userLocalStore = new UserLocalStore(Home.this);


        customTabAdapter = new customTabAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(customTabAdapter);

        final TabLayout.Tab page1 = tabLayout.newTab();
        final TabLayout.Tab page2 = tabLayout.newTab();
        final TabLayout.Tab page3 = tabLayout.newTab();


        page1.setText("Tweets");
        page2.setText("Recommendations");
        page3.setText("Hashtag");
        //home.setIcon(R.drawable.home);
        //search.setIcon(R.drawable.search_black);
        tabLayout.addTab(page1,0);
        tabLayout.addTab(page2,1);
        tabLayout.addTab(page3,2);

        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicator));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();

    }
}