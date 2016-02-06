package com.indraoctamaindra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.indraoctamaindra.sgoticket.PassengerAdapter;
import com.indraoctamaindra.sgoticket.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassengersActivity extends Activity {

    String destination, date_departure,date_arrival,flight_type,origin, product_code,jsoncontact,jsonava,session_id,adult_string,child_string,infant_string;
    Integer adult,child,infant,total_passenger;
    JSONObject json_contact;
    private ListView mPassengerListView;
    private List<String> mItems;
    private PassengerAdapter mListAdapter;
    private Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passengers);

        //set element
        setElement();

        try{

            json_contact = new JSONObject(jsoncontact);


        }catch(Exception e){
            Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }


    }

    private void setElement(){

        Intent intent = getIntent();
        destination = intent.getStringExtra("destination");
        date_departure = intent.getStringExtra("departure_date");
        date_arrival = intent.getStringExtra("arrival_date");
        flight_type = intent.getStringExtra("flight_type");
        adult_string = intent.getStringExtra("adult");
        child_string = intent.getStringExtra("child");
        infant_string = intent.getStringExtra("infant");

        adult = Integer.parseInt(adult_string);
        child = Integer.parseInt(child_string);
        infant = Integer.parseInt(infant_string);

        total_passenger = adult + child + infant;

        origin = intent.getStringExtra("origin");
        product_code = intent.getStringExtra("product_code");
        jsoncontact = intent.getStringExtra("JsonStringContact");
        jsonava = intent.getStringExtra("JsonStringAvailability");
        session_id = intent.getStringExtra("session_id");

        mPassengerListView = (ListView) findViewById(R.id.passengerList);
        btnSubmit = (Button) findViewById(R.id.btnSumbitPass);

        List<Map<String,String>> arrSchedule = new ArrayList<Map<String, String>>();


        for(int adt = 0; adt<adult;adt++){

            Map<String, String> helper = new HashMap<String, String>();

            helper.put("paxtype","ADT");

            arrSchedule.add(helper);


        }

        for(int chd = 0; chd<child;chd++){

            Map<String, String> helper1 = new HashMap<String, String>();

            helper1.put("paxtype","CHD");

            arrSchedule.add(helper1);

        }

        for(int inf = 0; inf<infant;inf++){

            Map<String, String> helper2 = new HashMap<String, String>();

            helper2.put("paxtype","INF");

            arrSchedule.add(helper2);

        }

        //Parsing Data List Ke List View
        mListAdapter = new PassengerAdapter(PassengersActivity.this,arrSchedule);
        mPassengerListView.setAdapter(mListAdapter);



        btnSubmit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view;
                ArrayList<Map<String,String>> allValues = new ArrayList<Map<String,String>>();
                EditText title,first_name,last_name,birthdate,pax_type,qaff;
                ArrayList<String> valueList = new ArrayList<String>();

                for(int j = 0; j < mListAdapter.getCount(); j++){


                     view = mPassengerListView.getChildAt(j);


                     Map<String,String> helper = new HashMap<String, String>();

                     if(view != null) {
                         first_name = (EditText) view.findViewById(R.id.first_name);
                         last_name = (EditText) view.findViewById(R.id.last_name);
                         birthdate = (EditText) view.findViewById(R.id.birthdate);
                         pax_type = (EditText) view.findViewById(R.id.pax_type);
                         qaff = (EditText) view.findViewById(R.id.qaff);

                         if (pax_type.getText().toString() == "ADT") {
                             title = (EditText) view.findViewById(R.id.title);
                             helper.put("title", title.getText().toString());
                         } else {
                             helper.put("title", "");
                         }

                         helper.put("first_name", first_name.getText().toString());
                         helper.put("last_name", last_name.getText().toString());
                         helper.put("birthdate", birthdate.getText().toString());
                         helper.put("qaff", qaff.getText().toString());

                         StringBuilder sb = new StringBuilder();
                         sb.append(j);
                         helper.put("passenger_number", sb.toString());
                         helper.put("pax_type", pax_type.getText().toString());


                         allValues.add(helper);
                     }
                }

                Toast.makeText(getBaseContext(),"",Toast.LENGTH_LONG).show();
            }
        });


    }


}
