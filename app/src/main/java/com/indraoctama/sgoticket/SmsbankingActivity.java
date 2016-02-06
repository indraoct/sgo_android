package com.indraoctama.sgoticket;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.indraoctama.library.Currency;
import com.indraoctama.library.DefinedDialog;
import com.indraoctama.library.ParamConst;
import com.indraoctama.library.TinyDB;
import com.indraoctama.library.UrlHit;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class SmsbankingActivity extends Activity {

    ProgressDialog out;
    Spinner bankname;
    TextView total_amount,bankchannel;
    Button btnProses;
    Context context;
    private String urlpaymentbiller = ParamConst.getPaymentBillerUrl;
    private String url_bankbillerSMS = ParamConst.UrlBankBillerSMS;
    private String url_inquiryTrx = ParamConst.UrlInquiryTrx;
    JSONObject jsonobject;
    JSONArray jsonarray;
    ProgressDialog mProgressDialog;
    ArrayList<String> banklist,bankchlist;
    ArrayList<BankBiller> bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsbanking);


        // Download JSON file AsyncTask
        new DownloadJSON().execute();


    }

    /* INDRA OCTAMA
     * Bank Biller JSON
     */

    private class DownloadJSON extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {

            //populate bank
            bank = new ArrayList<BankBiller>();
            // Create an array to populate the spinner
            banklist = new ArrayList<String>();
            // JSON file URL address
            jsonobject = JSONfunctions
                    .getJSONfromURL(url_bankbillerSMS);

            try {

                // Locate the NodeList name
                jsonarray = jsonobject.getJSONArray("bank_biller");
                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonobject = jsonarray.getJSONObject(i);

                    BankBiller bankbill = new BankBiller();

                    bankbill.set_bank_code(jsonobject.optString("bank_code"));
                    bankbill.set_bank_name(jsonobject.optString("bank_name"));
                    bankbill.set_product_code(jsonobject.optString("product_code"));
                    bankbill.set_product_h2h(jsonobject.optString("product_h2h"));
                    bankbill.set_product_type(jsonobject.optString("product_type"));
                    bank.add(bankbill);

                    // Populate spinner with bank names
                    banklist.add(jsonobject.optString("bank_name"));

                }

            }catch(Exception e){

                Log.e("Error", e.getMessage());
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args){

            out = DefinedDialog.CreateProgressDialog(SmsbankingActivity.this, null, "Loading SMS Banking...");
            out.dismiss();

            context = getApplication().getBaseContext();

            final TinyDB tinydb = new TinyDB(context);

            // Locate the spinner in activity_main.xml
            //set layout
            bankname = (Spinner) findViewById(R.id.bankname);
            bankchannel = (TextView) findViewById(R.id.bankchannel);
            total_amount = (TextView) findViewById(R.id.total_amount);
            btnProses = (Button) findViewById(R.id.btnProses);

            //set text total amount
            Double total_amount_dbl = Double.parseDouble(tinydb.getString("total_amount"));
            Currency currency = new Currency();
            String total_amount_idr = currency.idr(total_amount_dbl);

            total_amount.setText(total_amount_idr);

            // Spinner adapter
            bankname
                    .setAdapter(new ArrayAdapter<String>(SmsbankingActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            banklist));


            // Spinner on item click listener
            bankname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


                @Override
                public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

                    // Spinner adapter
                    bankchannel.setText(bank.get(position).get_product_code());

                    btnProses.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            out.show();

                            String product_code_rodex = tinydb.getString("product_code");
                            String order_id = tinydb.getString("order_id");
                            final String bank_code = bank.get(position).get_bank_code();
                            final String product_code = bank.get(position).get_product_code();
                            final String tx_id = tinydb.getString("tx_id");
                            String amount = tinydb.getString("total_amount");


                            if(bank_code.equals("")){

                                AlertDialog.Builder builder1 = new AlertDialog.Builder(SmsbankingActivity.this);
                                builder1.setMessage("Bank Code is Empty");
                                builder1.setTitle("Error");
                                builder1.setIcon(R.drawable.stop);
                                builder1.setPositiveButton("Kembali",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //close dialog
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alert11 = builder1.create();
                                alert11.show();


                            }else if(product_code.equals("")){

                                AlertDialog.Builder builder1 = new AlertDialog.Builder(SmsbankingActivity.this);
                                builder1.setMessage("Product Code is Empty");
                                builder1.setTitle("Error");
                                builder1.setIcon(R.drawable.stop);
                                builder1.setPositiveButton("Kembali",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //close dialog
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alert11 = builder1.create();
                                alert11.show();

                            }else {

                                /*
                                 * PAYMENT BILLER
                                 */
                                RequestParams params = new RequestParams();
                                params.put("product_code_rodex", product_code_rodex);
                                params.put("order_id", order_id);
                                params.put("bank_code", bank_code);
                                params.put("product_code", product_code);
                                params.put("tx_id", tx_id);
                                params.put("amount", amount);
                                params.put("product_h2h", bank.get(position).get_product_h2h());
                                params.put("product_type", bank.get(position).get_product_type());

                                UrlHit.post(getBaseContext(), urlpaymentbiller, params, new TextHttpResponseHandler() {
                                    @Override
                                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {

                                        out.dismiss();

                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(SmsbankingActivity.this);
                                        builder1.setMessage(s);
                                        builder1.setTitle("Error");
                                        builder1.setIcon(R.drawable.stop);
                                        builder1.setPositiveButton("Kembali",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        //Back To Main Activity
                                                        Intent intent = new Intent(SmsbankingActivity.this, PaymentmenuActivity.class);
                                                        startActivity(intent);
                                                    }
                                                });

                                        AlertDialog alert11 = builder1.create();
                                        alert11.show();

                                    }

                                    @Override
                                    public void onSuccess(int i, Header[] headers, String s) {

                                        try {
                                            JSONObject obj = new JSONObject(s);

                                            String error_code = obj.getString("error_code");
                                            String error_msg = obj.getString("error_msg");
                                            if (error_code.equals("0000")) {
                                                String tx_id_res = obj.getString("tx_id");
                                                String comm_espay = obj.getString("comm_espay");
                                                JSONObject comm_espay_obj = new JSONObject(comm_espay);
                                                String api_key = comm_espay_obj.getString("api_key");
                                                String comm_code = comm_espay_obj.getString("comm_code");


                                                //tx_id
                                                tinydb.putString("tx_id", tx_id_res);
                                                //comm_espay
                                                tinydb.putString("comm_espay", comm_espay);
                                                //api_key
                                                tinydb.putString("api_key", api_key);
                                                //comm_code
                                                tinydb.putString("comm_code", comm_code);
                                                //bank_code
                                                tinydb.putString("bank_code", bank_code);
                                                //product_code
                                                tinydb.putString("product_code", product_code);


                                                            /*
                                                             *  INQUIRYTRX
                                                             */
                                                            RequestParams params_inq = new RequestParams();
                                                            params_inq.put("tx_id", tinydb.getString("tx_id"));
                                                            params_inq.put("product_code", tinydb.getString("product_code"));

                                                            UrlHit.post(getBaseContext(), url_inquiryTrx, params_inq, new TextHttpResponseHandler() {
                                                                @Override
                                                                public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                                                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(SmsbankingActivity.this);
                                                                    builder1.setMessage(s);
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

                                                                @Override
                                                                public void onSuccess(int i, Header[] headers, String s) {

                                                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(SmsbankingActivity.this);
                                                                    builder1.setMessage(s);
                                                                    builder1.setTitle("Success");
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
                                                            });

                                            } else {
                                                AlertDialog.Builder builder1 = new AlertDialog.Builder(SmsbankingActivity.this);
                                                builder1.setMessage(error_msg);
                                                builder1.setTitle("Error");
                                                builder1.setIcon(R.drawable.stop);
                                                builder1.setPositiveButton("Kembali",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {

                                                                Intent intent = new Intent(SmsbankingActivity.this,MainActivity.class);
                                                                startActivity(intent);

                                                            }
                                                        });

                                                AlertDialog alert11 = builder1.create();
                                                alert11.show();
                                            }


                                        } catch (Exception e) {

                                            AlertDialog.Builder builder1 = new AlertDialog.Builder(SmsbankingActivity.this);
                                            builder1.setMessage(e.getMessage().toString());
                                            builder1.setTitle("Error");
                                            builder1.setIcon(R.drawable.stop);
                                            builder1.setPositiveButton("Kembali",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            //Back To Main Activity
                                                            Intent intent = new Intent(SmsbankingActivity.this, PaymentmenuActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    });

                                            AlertDialog alert11 = builder1.create();
                                            alert11.show();
                                        }

                                    }
                                });

                            }




                                        }
                                    });


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {



                }
            });


        }

    }


}
