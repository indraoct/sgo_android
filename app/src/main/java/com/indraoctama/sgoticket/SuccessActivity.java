package com.indraoctama.sgoticket;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


public class SuccessActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(SuccessActivity.this);
        builder1.setMessage("Memesan Tiket Lagi?");
        builder1.setTitle("Confirm");
        builder1.setIcon(R.drawable.confirm);
        builder1.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Kembali
                        Intent intent = new Intent(SuccessActivity.this,MainActivity.class);
                        startActivity(intent);

                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

}
