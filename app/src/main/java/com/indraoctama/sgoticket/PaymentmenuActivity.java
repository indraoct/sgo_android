package com.indraoctama.sgoticket;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.indraoctama.library.TinyDB;

import org.json.JSONObject;

public class PaymentmenuActivity extends Activity {

    LinearLayout ibank,sms;
    TextView booking_code,kontak,flight_info,penumpang,total,total_admin,basic_amount;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentmenu);
        __init();
    }

    public void __init(){

        /*
         * deklarasi
         */
        context = getApplication().getBaseContext();
        final TinyDB tinydb = new TinyDB(context);

        /*
         * DUMMY DATA (SEMENTARA)
         */
//        tinydb.putString("total_amount", "1000000");
//        tinydb.putString("bank_biller","[{\"bank_code\":\"008\",\"product_code\":\"MANDIRIIB\",\"bank_name\":\"BANK MANDIRI\",\"product_name\":\"MANDIRI IB\",\"product_h2h\":\"N\",\"product_type\":\"IB\"},{\"bank_code\":\"008\",\"product_code\":\"MANDIRISMS\",\"bank_name\":\"BANK MANDIRI\",\"product_name\":\"MANDIRI SMS\",\"product_h2h\":\"Y\",\"product_type\":\"SMS\"}]");
//        tinydb.putString("data_booking","{\"booking_code\":\"W6YKNS\",\"contact_name\":\"MR jfjccucuc cjvjciciiv\",\"flight_info\":\"QZ 7556 (CGK 21/11/2015 05:50 - JOG 21/11/2015 06:55)\",\"passenger_name\":\"MR vjvjcu cj jvin, MR j jcuvivuv ucjvufucu\"}");
        /*
         * Component
         */

        booking_code = (TextView) findViewById(R.id.booking_code);
        kontak = (TextView) findViewById(R.id.kontak);
        flight_info = (TextView) findViewById(R.id.flight_info);
        penumpang = (TextView) findViewById(R.id.penumpang);
        total = (TextView) findViewById(R.id.total);
        total_admin = (TextView) findViewById(R.id.total_admin);
        basic_amount = (TextView) findViewById(R.id.basic_amount);

        /*
         * Balikan Data
         */
        String data_booking = tinydb.getString("data_booking");
        String data_amount = tinydb.getString("total_amount");
        String data_admin_amount = tinydb.getString("admin_amount");
        String data_basic_amount = tinydb.getString("basic_amount");

        try{

            JSONObject obj = new JSONObject(data_booking);
            String booking_code_text = obj.getString("booking_code");
            String kontak_text = obj.getString("contact_name");
            String flight_info_text = obj.getString("flight_info");
            String penumpang_text = obj.getString("passenger_name");

            String total_amount = data_amount;
            String admin_amount  = data_admin_amount;
            String basic_amt     = data_basic_amount;


            booking_code.setText(booking_code_text);
            kontak.setText(kontak_text);
            flight_info.setText(flight_info_text);
            penumpang.setText(penumpang_text);
            total.setText(total_amount);
            total_admin.setText(admin_amount);
            basic_amount.setText(basic_amt);

        }catch (Exception e){
            e.printStackTrace();
        }

        ibank = (LinearLayout) findViewById(R.id.ibank);
        sms = (LinearLayout) findViewById(R.id.sms);

        ibank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PaymentmenuActivity.this,InternetBankingActivity.class);
                startActivity(intent);

            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(PaymentmenuActivity.this,SmsbankingActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(PaymentmenuActivity.this);
            builder1.setMessage("Apakah anda akan melanjutkan transaksi?");
            builder1.setTitle("Confirm");
            builder1.setIcon(R.drawable.confirm);
            builder1.setCancelable(true);
            builder1.setPositiveButton("Ya",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //Tutup dialog
                            dialog.cancel();

                        }
                    });
            builder1.setNegativeButton("Kembali Ke Awal", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //Kembali
                    Intent intent = new Intent(PaymentmenuActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            });

            AlertDialog alert11 = builder1.create();
            alert11.show();

    }




}
