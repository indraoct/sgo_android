package com.indraoctamaindra.sgoticket;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ContactActivity extends Activity {

    String destination, date_departure,date_arrival,flight_type,adult,child,infant,origin, product_code,jsonstring,session_id;
    EditText firstname,lastname,email,phone;
    Spinner title;
    Button btnSubmit;
    LinearLayout linear1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        //Get Intent Extra
        Intent intent = getIntent();
        destination = intent.getStringExtra("destination");
        date_departure = intent.getStringExtra("departure_date");
        date_arrival = intent.getStringExtra("arrival_date");
        flight_type = intent.getStringExtra("flight_type");
        adult = intent.getStringExtra("adult");
        child = intent.getStringExtra("child");
        infant = intent.getStringExtra("infant");
        origin = intent.getStringExtra("origin");
        product_code = intent.getStringExtra("product_code");
        jsonstring = intent.getStringExtra("JsonString");
        session_id = intent.getStringExtra("session_id");

        //init component
        initComponent();

    }

    /*
     * validasi email
     */
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /*
     * Init Component
     */
    public void initComponent(){

        firstname = (EditText) findViewById(R.id.first_name);
        lastname = (EditText) findViewById(R.id.last_name);
        title = (Spinner) findViewById(R.id.title);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);

        linear1 = (LinearLayout) findViewById(R.id.linear1);

        //bring to front
        linear1.bringToFront();

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String,String> MappingContact = new HashMap<>();
                JSONObject jsonContact;

                if(firstname.getText().toString().equals("")){

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ContactActivity.this);
                    builder1.setMessage("Firstname harus Diisi");
                    builder1.setTitle("Mandatory");
                    builder1.setIcon(R.drawable.stop);
                    builder1.setPositiveButton("Tutup",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }else if(lastname.getText().toString().equals("")){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ContactActivity.this);
                    builder1.setMessage("Lastname harus Diisi");
                    builder1.setTitle("Mandatory");
                    builder1.setIcon(R.drawable.stop);
                    builder1.setPositiveButton("Tutup",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }else if(title.getSelectedItem().toString().equals("")){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ContactActivity.this);
                    builder1.setMessage("Title harus Diisi");
                    builder1.setTitle("Mandatory");
                    builder1.setIcon(R.drawable.stop);
                    builder1.setPositiveButton("Tutup",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }else if(email.getText().toString().equals("")){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ContactActivity.this);
                    builder1.setMessage("Email harus Diisi");
                    builder1.setTitle("Mandatory");
                    builder1.setIcon(R.drawable.stop);
                    builder1.setPositiveButton("Tutup",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }else if(phone.getText().toString().equals("")) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ContactActivity.this);
                    builder1.setMessage("Phone harus Diisi");
                    builder1.setTitle("Mandatory");
                    builder1.setIcon(R.drawable.stop);
                    builder1.setPositiveButton("Tutup",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }else if(isValidEmail(email.getText().toString()) == false){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ContactActivity.this);
                    builder1.setMessage("Format Email Salah");
                    builder1.setTitle("Email");
                    builder1.setIcon(R.drawable.stop);
                    builder1.setPositiveButton("Tutup",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();


                }else {


                    try {

                        MappingContact.put("first_name", firstname.getText().toString());
                        MappingContact.put("last_name", lastname.getText().toString());
                        MappingContact.put("title", title.getSelectedItem().toString());
                        MappingContact.put("email", email.getText().toString());
                        MappingContact.put("home_phone", phone.getText().toString());
                        MappingContact.put("other_phone", phone.getText().toString());


                        jsonContact = new JSONObject(MappingContact);

                        //TEST 3 Agustus 2014
                        Intent i = new Intent(ContactActivity.this, PassengerActivity.class); //Intent i = new Intent(ContactActivity.this, PassengersActivity.class); //   //Intent i = new Intent(ContactActivity.this, Passenger2Activity.class);
                        i.putExtra("session_id", session_id);
                        i.putExtra("destination", destination);
                        i.putExtra("departure_date", date_departure);
                        i.putExtra("arrival_date", date_arrival);
                        i.putExtra("flight_type", flight_type);
                        i.putExtra("adult", adult);
                        i.putExtra("child", child);
                        i.putExtra("infant", infant);
                        i.putExtra("origin", origin);
                        i.putExtra("product_code", product_code);
                        i.putExtra("JsonStringAvailability", jsonstring);
                        i.putExtra("JsonStringContact", jsonContact.toString());

                        startActivity(i);

                    } catch (Exception e) {


                        AlertDialog.Builder builder1 = new AlertDialog.Builder(ContactActivity.this);
                        builder1.setMessage(e.getMessage().toString());
                        builder1.setTitle("Error");
                        builder1.setIcon(R.drawable.stop);
                        builder1.setPositiveButton("Tutup",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                }

            }
        });

    }


}
