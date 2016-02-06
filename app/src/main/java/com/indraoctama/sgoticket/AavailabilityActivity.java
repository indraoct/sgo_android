package com.indraoctama.sgoticket;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.indraoctama.library.ParamConst;
import com.indraoctama.library.UrlHit;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.indraoctama.library.DefinedDialog;


public class AavailabilityActivity extends Activity {

    String destination, date_departure,date_arrival,flight_type,adult,child,infant,origin, product_code,session_id;
    String urlGetAvailability = ParamConst.getAvailabilityUrl; //URL HIT get availability
    String urlCommitTransaction = ParamConst.getCommitTransactionUrl; // URL HIT commitTransaction


    private ListView mOnewayListView;
    private List<String> mItems;
    private OnewaylistAdapter mListAdapter;
    private static final int MIN = 0, MAX = 10000;
    private TextView pergi,datang;
    private String ResultJsonAvailability;
    ProgressDialog out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);

        mOnewayListView = (ListView) findViewById(R.id.scheduleList);
        pergi = (TextView) findViewById(R.id.pergi);
        datang = (TextView) findViewById(R.id.datang);

        //loading progress
        out = DefinedDialog.CreateProgressDialog(AavailabilityActivity.this,null,"Loading ...");


        //Action Get Hit Server;
        GetHitServer();



    }

    public void GetHitServer(){

        //Progress Dialog
        out.show();


        //Get Intent Put Extra

         Intent intent = getIntent();
         destination = intent.getStringExtra("destination");
         date_departure = intent.getStringExtra("date_departure");
         date_arrival = intent.getStringExtra("date_arrival");
         flight_type = intent.getStringExtra("flight_type");
         adult = intent.getStringExtra("adult");
         child = intent.getStringExtra("child");
         infant = intent.getStringExtra("infant");
         origin = intent.getStringExtra("origin");
         product_code = intent.getStringExtra("product_code");


            //Request Params
            RequestParams params = new RequestParams();
            params.put("destination",destination);
            params.put("date_departure",date_departure);
            params.put("date_arrival",date_arrival);
            params.put("flight_type",flight_type);
            params.put("adult",adult);
            params.put("child",child);
            params.put("infant",infant);
            params.put("origin",origin);
            params.put("product_code",product_code);


            pergi.setText(origin+" ");
            datang.setText(" "+destination);



            try {

                UrlHit.post(getBaseContext(), urlGetAvailability, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {

                        out.dismiss();

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(AavailabilityActivity.this);
                        builder1.setMessage(s);
                        builder1.setTitle("Error");
                        builder1.setIcon(R.drawable.stop);
                        builder1.setPositiveButton("Kembali",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();

                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {

                        out.dismiss();

                        List<Map<String,String>> arrSchedule = new ArrayList<Map<String, String>>();

                    try {
                        final JSONObject jObj = new JSONObject(s);
                        ResultJsonAvailability = s;

                                /*
                                 * IF SUCCESS
                                 */
                                if (jObj.getString("error_code").equals("0000")) {


                                    if(!jObj.getString("oneway").equals("0")) {

                                        JSONArray jArr = jObj.getJSONArray("oneway");
                                        Integer[] ini = null;
                                        session_id = jObj.getString("session_id");

                                        for (int j = 0; j < jArr.length(); j++) {

                                            //helper MAP
                                            Map<String, String> helper = new HashMap<String, String>();

                                            helper.put("journey_id", jArr.getJSONObject(j).getString("journey_id"));
                                            helper.put("flight_number", jArr.getJSONObject(j).getString("flight_number"));
                                            helper.put("departure_time", jArr.getJSONObject(j).getString("departure_time"));
                                            helper.put("arrival_time", jArr.getJSONObject(j).getString("arrival_time"));
                                            helper.put("origin", jArr.getJSONObject(j).getString("origin"));
                                            helper.put("destination", jArr.getJSONObject(j).getString("destination"));
                                            helper.put("amount", jArr.getJSONObject(j).getString("amount"));
                                            helper.put("fare_id", jArr.getJSONObject(j).getString("fare_id"));
                                            helper.put("class_code", jArr.getJSONObject(j).getString("class_code"));
                                            helper.put("product_code", product_code);
                                            helper.put("departure_time_time", jArr.getJSONObject(j).getString("departure_time_time"));
                                            helper.put("departure_time_date", jArr.getJSONObject(j).getString("departure_time_date"));
                                            helper.put("arrival_time_time", jArr.getJSONObject(j).getString("arrival_time_time"));
                                            helper.put("arrival_time_date", jArr.getJSONObject(j).getString("arrival_time_date"));
                                            helper.put("transit_via", jArr.getJSONObject(j).getString("transit_via"));
                                            helper.put("transit_info", jArr.getJSONObject(j).getString("transit_info"));

                                            arrSchedule.add(helper);
                                        }


                                        //Parsing Data List Ke List View
                                        mListAdapter = new OnewaylistAdapter(AavailabilityActivity.this, arrSchedule);
                                        mOnewayListView.setAdapter(mListAdapter);

                                        mOnewayListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                                            /*
                                                         * View journey_id , fare_id, class_code
                                                         */

                                                TextView fJour = (TextView) view.findViewById(R.id.journey_id);
                                                TextView fFare = (TextView) view.findViewById(R.id.fare_id);
                                                TextView fClass = (TextView) view.findViewById(R.id.class_code);

                                                TextView fFlightNumber = (TextView) view.findViewById(R.id.flight_no_bottom);

                                                        /*
                                                         * String journey_id , fare_id, class_code
                                                         */
                                                String journey_id = fJour.getText().toString();
                                                String fare_id = fFare.getText().toString();
                                                String class_code = fClass.getText().toString();
                                                String flight_no = fFlightNumber.getText().toString();

                                                                        /*
                                                                         * PEMBENTUKAN JSON OBJECT
                                                                         */

                                                Map<String, String> arr_journey = new HashMap<String, String>();

                                                arr_journey.put("journey_id", journey_id);
                                                arr_journey.put("fare_id", fare_id);
                                                arr_journey.put("class_code", class_code);

                                                //json object journey
                                                final JSONObject journey = new JSONObject(arr_journey);

                                                //array journeys
                                                ArrayList<JSONObject> arr_journeys = new ArrayList<JSONObject>();
                                                arr_journeys.add(journey);

                                                //json object journeys
                                                final JSONArray journeys = new JSONArray(arr_journeys);


                                                final AlertDialog.Builder builderConfirm = new AlertDialog.Builder(AavailabilityActivity.this);
                                                builderConfirm.setMessage("Apakah anda setuju memilih No Penerbangan : " + flight_no + " ?");
                                                builderConfirm.setTitle("Konfirmasi Jadwal");
                                                builderConfirm.setCancelable(true);
                                                builderConfirm.setIcon(R.drawable.confirm);
                                                builderConfirm.setPositiveButton("Setuju",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {


                                                                if (flight_type.equals("RT")) {

                                                                    //Intent Activity Return Commit

                                                                    Intent intent = new Intent(AavailabilityActivity.this, AvailabilityreturnActivity.class);
                                                                    intent.putExtra("destination", destination);
                                                                    intent.putExtra("departure_date", date_departure);
                                                                    intent.putExtra("arrival_date", date_arrival);
                                                                    intent.putExtra("flight_type", flight_type);
                                                                    intent.putExtra("adult", adult);
                                                                    intent.putExtra("child", child);
                                                                    intent.putExtra("infant", infant);
                                                                    intent.putExtra("origin", origin);
                                                                    intent.putExtra("product_code", product_code);
                                                                    intent.putExtra("journey_oneway", journey.toString());
                                                                    intent.putExtra("session_id", session_id);
                                                                    intent.putExtra("JsonString", ResultJsonAvailability);

                                                                    startActivity(intent);


                                                                } else {

                                                                    try {

                                                                        //loading visible
                                                                        out.show();

                                                                        //Request ParamsCommit
                                                                        RequestParams paramsCommit = new RequestParams();
                                                                        paramsCommit.put("destination", destination);
                                                                        paramsCommit.put("departure_date", date_departure);
                                                                        paramsCommit.put("arrival_date", date_departure);
                                                                        paramsCommit.put("flight_type", flight_type);
                                                                        paramsCommit.put("adult", adult);
                                                                        paramsCommit.put("child", child);
                                                                        paramsCommit.put("infant", infant);
                                                                        paramsCommit.put("origin", origin);
                                                                        paramsCommit.put("product_code", product_code);
                                                                        paramsCommit.put("session_id", session_id);
                                                                        paramsCommit.put("journeys", journeys);
                                                                        paramsCommit.put("using_form", "1");

                                                                        //HIT URL commit transaction
                                                                        UrlHit.post(getBaseContext(), urlCommitTransaction, paramsCommit, new TextHttpResponseHandler() {
                                                                            @Override
                                                                            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {

                                                                                out.dismiss();

                                                                                AlertDialog.Builder builder1 = new AlertDialog.Builder(AavailabilityActivity.this);
                                                                                builder1.setMessage(s);
                                                                                builder1.setTitle("Error");
                                                                                builder1.setIcon(R.drawable.stop);
                                                                                builder1.setPositiveButton("Kembali",
                                                                                        new DialogInterface.OnClickListener() {
                                                                                            public void onClick(DialogInterface dialog, int id) {
                                                                                                finish();
                                                                                            }
                                                                                        });

                                                                                AlertDialog alert11 = builder1.create();
                                                                                alert11.show();
                                                                            }

                                                                            @Override
                                                                            public void onSuccess(int i, Header[] headers, String s) {

                                                                                out.dismiss();

                                                                                try {
                                                                                    final JSONObject jbj = new JSONObject(s);

                                                                                    String error_code = jbj.getString("error_code");

                                                                                    if (error_code.equals("0000")) {

                                                                                        //Intent Activity Contact and Passengers

                                                                                        Intent intent = new Intent(AavailabilityActivity.this, ContactActivity.class);
                                                                                        intent.putExtra("session_id", session_id);
                                                                                        intent.putExtra("destination", destination);
                                                                                        intent.putExtra("departure_date", date_departure);
                                                                                        intent.putExtra("arrival_date", date_arrival);
                                                                                        intent.putExtra("flight_type", flight_type);
                                                                                        intent.putExtra("adult", adult);
                                                                                        intent.putExtra("child", child);
                                                                                        intent.putExtra("infant", infant);
                                                                                        intent.putExtra("origin", origin);
                                                                                        intent.putExtra("product_code", product_code);
                                                                                        intent.putExtra("JsonString", ResultJsonAvailability);

                                                                                        startActivity(intent);


                                                                                    } else {
                                                                                        Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
                                                                                    }

                                                                                } catch (Exception e) {
                                                                                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                                                                }

                                                                            }
                                                                        });


                                                                    } catch (Exception e) {
                                                                        Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                                                    }


                                                                }


                                                            }
                                                        });
                                                builderConfirm.setNegativeButton("Tidak",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {
                                                                dialog.dismiss();
                                                            }
                                                        });

                                                AlertDialog alertConfirm = builderConfirm.create();
                                                alertConfirm.show();


                                            }
                                        });

                                    }else{

                                        out.dismiss();

                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(AavailabilityActivity.this);
                                        builder1.setMessage("Jadwal Kosong");
                                        builder1.setTitle("Error");
                                        builder1.setIcon(R.drawable.stop);
                                        builder1.setPositiveButton("Kembali",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        finish();
                                                    }
                                                });

                                        AlertDialog alert11 = builder1.create();
                                        alert11.show();

                                    }

                                }else{


                                    out.dismiss();

                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AavailabilityActivity.this);
                                    builder1.setMessage("Terdapat Kesalahan : ("+jObj.getString("error_code")+") "+jObj.getString("error_msg"));
                                    builder1.setTitle("Error");
                                    builder1.setIcon(R.drawable.stop);
                                    builder1.setPositiveButton("Kembali",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    finish();
                                                }
                                            });

                                    AlertDialog alert11 = builder1.create();
                                    alert11.show();

                                }

                        }catch(Exception e){


                        out.dismiss();

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(AavailabilityActivity.this);
                        builder1.setMessage("Terdapat Kesalahan : ("+e.getMessage().toString());
                        builder1.setTitle("Error");
                        builder1.setIcon(R.drawable.stop);
                        builder1.setPositiveButton("Kembali",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();

                        }

                    }
                });


            } catch(Exception e){

                out.dismiss();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(AavailabilityActivity.this);
                builder1.setMessage("Terdapat Kesalahan : ("+e.getMessage().toString());
                builder1.setTitle("Error");
                builder1.setIcon(R.drawable.stop);
                builder1.setPositiveButton("Kembali",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }


    }

}
