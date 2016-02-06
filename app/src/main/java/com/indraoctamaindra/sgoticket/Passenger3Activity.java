package com.indraoctamaindra.sgoticket;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.indraoctamaindra.library.DefinedDialog;
import com.indraoctamaindra.library.ParamConst;
import com.indraoctamaindra.library.TinyDB;
import com.indraoctamaindra.library.UrlHit;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Passenger3Activity extends Activity {

    String destination, date_departure,date_arrival,flight_type,origin, product_code,jsoncontact,jsonava,session_id,adult_string,child_string,infant_string;
    Integer adult,child,infant,total_passenger;
    JSONObject json_contact;
    LinearLayout buttonlayout;
    private ListView mBtnPassengerListView;
    private BtnPassengerAdapter mBtnListAdapter;
    private Button btnSubmit;
    private String urlUpdatePassenger = ParamConst.getUpdatePassengerUrl;
    Context context;
    ProgressDialog out;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger3);

        __init();

    }


    public void __init(){

        context = getApplication().getBaseContext();

        final TinyDB tinydb = new TinyDB(context);
        out = DefinedDialog.CreateProgressDialog(Passenger3Activity.this, null, "Loading ...");

        out.dismiss();


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

        try {
            json_contact = new JSONObject(jsoncontact);
        }catch (Exception e){
            e.printStackTrace();
        }
        jsonava = intent.getStringExtra("JsonStringAvailability");
        session_id = intent.getStringExtra("session_id");


        mBtnPassengerListView = (ListView) findViewById(R.id.btnPassengerList);

        btnSubmit = (Button) findViewById(R.id.btnSumbitPass3);

        List<Map<String,String>> arrButton = new ArrayList<Map<String, String>>();

        //////////////////////////////////////////INITIAL TinyDB/////////////////////////////////////////////////

        ArrayList<String> arrGender = new ArrayList<String>();
        ArrayList<String> arrTitle = new ArrayList<String>();
        ArrayList<String> arrFirstname = new ArrayList<String>();
        ArrayList<String> arrLastname = new ArrayList<String>();
        ArrayList<String> arrBirthdate = new ArrayList<String>();
        ArrayList<String> arrQaff = new ArrayList<String>();
        ArrayList<String> arrPassNumber = new ArrayList<String>();
        ArrayList<String> arrPassportNumber = new ArrayList<String>();
        ArrayList<String> arrPassportExpired = new ArrayList<String>();
        final ArrayList<String> arrPaxType = new ArrayList<String>();


        //////////////////////////////////////////INITIAL TinyDB/////////////////////////////////////////////////


        Integer adding = 0;

        for(int adt = 0; adt<adult;adt++){

            Map<String, String> helper = new HashMap<String, String>();

            helper.put("btnText","Isi Penumpang Dewasa");
            helper.put("hdtext","ADT");

            arrButton.add(helper);


            arrGender.add(adding,"");
            arrTitle.add(adding, "");
            arrFirstname.add(adding, "");
            arrLastname.add(adding, "");
            arrBirthdate.add(adding, "");
            arrQaff.add(adding,"");
            arrPassNumber.add(adding,Integer.toString(adding));
            arrPaxType.add(adding, "");
            arrPassportNumber.add(adding,"");
            arrPassportExpired.add(adding,"");


            adding++;


        }

        if(child != 0) {
            for (int chd = 0; chd < child; chd++) {

                Map<String, String> helper1 = new HashMap<String, String>();

                helper1.put("btnText", "Isi Penumpang Anak");
                helper1.put("hdtext", "CHD");

                arrButton.add(helper1);


                arrGender.add(adding, "");
                arrTitle.add(adding, "");
                arrFirstname.add(adding, "");
                arrLastname.add(adding, "");
                arrBirthdate.add(adding, "");
                arrQaff.add(adding, "");
                arrPassNumber.add(adding, Integer.toString(adding));
                arrPaxType.add(adding, "");
                arrPassportNumber.add(adding,"");
                arrPassportExpired.add(adding,"");

                adding++;
            }
        }


        if(infant != 0) {
            for (int inf = 0; inf < infant; inf++) {

                Map<String, String> helper2 = new HashMap<String, String>();

                helper2.put("btnText", "Isi Penumpang Bayi");
                helper2.put("hdtext", "INF");

                arrButton.add(helper2);

                arrGender.add(adding, "");
                arrTitle.add(adding, "");
                arrFirstname.add(adding, "");
                arrLastname.add(adding, "");
                arrBirthdate.add(adding, "");
                arrQaff.add(adding, "");
                arrPassNumber.add(adding, Integer.toString(adding));
                arrPaxType.add(adding, "");
                arrPassportNumber.add(adding,"");
                arrPassportExpired.add(adding,"");

                adding++;

            }
        }

        //Pembentukan nilai tinyDB
        tinydb.putListString("gender",arrGender);
        tinydb.putListString("title",arrTitle);
        tinydb.putListString("first_name",arrFirstname);
        tinydb.putListString("last_name",arrLastname);
        tinydb.putListString("birthdate",arrBirthdate);
        tinydb.putListString("qaff",arrQaff);
        tinydb.putListString("passenger_number",arrPassNumber);
        tinydb.putListString("pax_type",arrPaxType);
        tinydb.putListString("passport_number",arrPassportNumber);
        tinydb.putListString("passport_expired",arrPassportExpired);

        //Session ID , Product Code simpan ke tiny DB
        tinydb.putString("session_id",session_id);
        tinydb.putString("product_code",product_code);


        mBtnListAdapter = new BtnPassengerAdapter(Passenger3Activity.this,arrButton);
        mBtnPassengerListView.setAdapter(mBtnListAdapter);
        mBtnPassengerListView.setItemsCanFocus(false);



        mBtnPassengerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                TextView fPaxtype = (TextView) view.findViewById(R.id.hdtext);

                String pax_types = fPaxtype.getText().toString();

                Intent i = new Intent(Passenger3Activity.this,PopupPassengerActivity.class);
                i.putExtra("pax_type",pax_types);
                i.putExtra("nomer",Integer.toString(position));

                startActivity(i);

            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             try {

                 ArrayList<Map<String, String>> arrPassenger = new ArrayList<Map<String, String>>();
                 ArrayList<String> arrGender = tinydb.getListString("gender");
                 ArrayList<String> arrTitle = tinydb.getListString("title");
                 ArrayList<String> arrFirstName = tinydb.getListString("first_name");
                 ArrayList<String> arrLastName = tinydb.getListString("last_name");
                 ArrayList<String> arrBirthDate = tinydb.getListString("birthdate");
                 ArrayList<String> arrQaff = tinydb.getListString("qaff");
                 ArrayList<String> arrPassNumber = tinydb.getListString("passenger_number");
                 ArrayList<String> arrPaxtype = tinydb.getListString("pax_type");
                 ArrayList<String> arrPassportNumber = tinydb.getListString("passport_number");
                 ArrayList<String> arrPassportExpired = tinydb.getListString("passport_expired");

                 for (Integer j = 0; j < total_passenger; j++) {

                     Map<String, String> helper = new HashMap<String, String>();

                     helper.put("gender", arrGender.get(j).toString());
                     helper.put("title", arrTitle.get(j).toString());
                     helper.put("first_name", arrFirstName.get(j).toString());
                     helper.put("last_name", arrLastName.get(j).toString());
                     helper.put("birthdate", arrBirthDate.get(j).toString());
                     helper.put("qaff", arrQaff.get(j).toString());
                     helper.put("passenger_number", arrPassNumber.get(j).toString());
                     helper.put("pax_type", arrPaxtype.get(j).toString());
                     helper.put("passport_number", arrPassportNumber.get(j).toString());
                     helper.put("passport_expired", arrPassportExpired.get(j).toString());

                     arrPassenger.add(helper);

                 }

                 JSONArray jsonPassengger;


                 try {

                     out.show();

                     jsonPassengger = new JSONArray(arrPassenger);

                     RequestParams params = new RequestParams();
                     params.put("session_id", session_id);
                     params.put("contact", json_contact);
                     params.put("passengers", jsonPassengger);
                     params.put("product_code", product_code);

                     UrlHit.post(getBaseContext(), urlUpdatePassenger, params, new TextHttpResponseHandler() {
                         @Override
                         public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                             out.dismiss();

                             AlertDialog.Builder builder1 = new AlertDialog.Builder(Passenger3Activity.this);
                             builder1.setMessage(s);
                             builder1.setTitle("Error");
                             builder1.setIcon(R.drawable.stop);
                             builder1.setPositiveButton("Kembali",
                                     new DialogInterface.OnClickListener() {
                                         public void onClick(DialogInterface dialog, int id) {
                                             //Back To Main Activity
                                             Intent intent = new Intent(Passenger3Activity.this,MainActivity.class);
                                             startActivity(intent);
                                         }
                                     });

                             AlertDialog alert11 = builder1.create();
                             alert11.show();
                         }

                         @Override
                         public void onSuccess(int i, Header[] headers, String s) {
                             out.dismiss();

                             try {

                                 JSONObject jObj = new JSONObject(s);

                                 //JSON DATA
                                 if(jObj.getString("error_code").equals("0000")) {
                                     JSONObject objData        = jObj.getJSONObject("data_info");
                                     JSONObject objDataBooking = jObj.getJSONObject("data_booking");
                                     String  order_id          = objDataBooking.getString("transaction_code");
                                     String tx_id              = objDataBooking.getString("tx_id");


                                     //total amount session
                                     tinydb.putString("total_amount", objData.getString("total").toString());
                                     //variable
                                     JSONArray bankObj = jObj.getJSONArray("bank_biller");
                                     tinydb.putString("bank_biller",bankObj.toString());
                                     //data booking
                                     tinydb.putString("data_booking",objDataBooking.toString());
                                     //order_id
                                     tinydb.putString("order_id",order_id);
                                     //tx_id
                                     tinydb.putString("tx_id",tx_id);


                                     Intent intentinquiry = new Intent(Passenger3Activity.this, PaymentmenuActivity.class);
                                     startActivity(intentinquiry);

                                 }else{

                                     AlertDialog.Builder builder1 = new AlertDialog.Builder(Passenger3Activity.this);
                                     builder1.setMessage("Terdapat Error : "+ jObj.getString("error_msg")+" Silahkan Coba Lagi");
                                     builder1.setTitle("Error");
                                     builder1.setIcon(R.drawable.stop);
                                     builder1.setCancelable(true);
                                     builder1.setPositiveButton("Ya",
                                             new DialogInterface.OnClickListener() {
                                                 public void onClick(DialogInterface dialog, int id) {

                                                     dialog.cancel();

                                                 }
                                             });

                                     builder1.setNegativeButton("Kembali Ke Awal",
                                             new DialogInterface.OnClickListener() {
                                                 public void onClick(DialogInterface dialog, int id) {

                                                     Intent intent = new Intent(Passenger3Activity.this,MainActivity.class);
                                                     startActivity(intent);

                                                 }
                                             });

                                     AlertDialog alert11 = builder1.create();
                                     alert11.show();



                                 }

                             }catch(Exception e){

                                 AlertDialog.Builder builder1 = new AlertDialog.Builder(Passenger3Activity.this);
                                 builder1.setMessage("Terdapat Error : "+ e.getMessage()+" Silahkan Coba Lagi");
                                 builder1.setTitle("Error");
                                 builder1.setIcon(R.drawable.stop);
                                 builder1.setCancelable(true);
                                 builder1.setPositiveButton("Ya",
                                         new DialogInterface.OnClickListener() {
                                             public void onClick(DialogInterface dialog, int id) {

                                                 dialog.cancel();

                                             }
                                         });

                                 builder1.setNegativeButton("Kembali Ke Awal",
                                         new DialogInterface.OnClickListener() {
                                             public void onClick(DialogInterface dialog, int id) {

                                                 Intent intent = new Intent(Passenger3Activity.this,MainActivity.class);
                                                 startActivity(intent);

                                             }
                                         });

                                 AlertDialog alert11 = builder1.create();
                                 alert11.show();



                             }

                         }
                     });


                 } catch (Exception e) {

                     out.dismiss();

                     AlertDialog.Builder builder1 = new AlertDialog.Builder(Passenger3Activity.this);
                     builder1.setMessage(e.getMessage());
                     builder1.setTitle("Error");
                     builder1.setIcon(R.drawable.stop);
                     builder1.setPositiveButton("Tutup",
                             new DialogInterface.OnClickListener() {
                                 public void onClick(DialogInterface dialog, int id) {

                                     dialog.cancel();

                                 }
                             });

                     AlertDialog alert11 = builder1.create();
                     alert11.show();
                 }


             }catch (Exception e){

                 AlertDialog.Builder builder1 = new AlertDialog.Builder(Passenger3Activity.this);
                 builder1.setMessage("Mohon isi seluruh data penumpang! ");
                 builder1.setTitle("Error");
                 builder1.setIcon(R.drawable.stop);
                 builder1.setPositiveButton("Tutup",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {

                                 dialog.cancel();

                             }
                         });

                 AlertDialog alert11 = builder1.create();
                 alert11.show();

             }

            }
        });


    }

}
