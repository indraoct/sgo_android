package com.indraoctama.sgoticket;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


public class MainActivity extends TabActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        /*
        Tabhost re definisi
         */
        TabHost.TabSpec tab1 = tabHost.newTabSpec("Airlines");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Train");

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        tab1.setIndicator("Pesawat");
        tab1.setContent(new Intent(this,AirlinesActivity.class));

        tab2.setIndicator("Kereta Api");
        tab2.setContent(new Intent(this,TrainActivity.class));


        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);



    }
}