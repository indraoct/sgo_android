package com.indraoctama.sgoticket;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.indraoctama.library.ParamConst;
import com.indraoctama.library.TinyDB;
import com.indraoctama.library.UrlHit;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;


public class BookingConfirmActivity extends Activity {

    RelativeLayout loadingpanel;
    TextView msg_response;
    Context context;
    String session_id,product_code,total_amount;
    String getUrlBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);

        msg_response = (TextView) findViewById(R.id.msg_response);
        loadingpanel = (RelativeLayout) findViewById(R.id.loadingPanel);


        context = getApplication().getBaseContext();
        final TinyDB tinydb = new TinyDB(context);

        session_id = tinydb.getString("session_id");
        product_code = tinydb.getString("product_code");
        total_amount = tinydb.getString("total_amount");

        RequestParams params = new RequestParams();

        params.put("session_id",session_id);
        params.put("product_code",product_code);
        params.put("total",total_amount);

        getUrlBooking = ParamConst.getBookingPaymentUrl;


        try{

            UrlHit.post(getBaseContext(),getUrlBooking,params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int i, Header[] headers, String s, Throwable throwable) {

                    loadingpanel.setVisibility(View.GONE);
                    msg_response.setText(s);

                }

                @Override
                public void onSuccess(int i, Header[] headers, String s) {
                    loadingpanel.setVisibility(View.GONE);

                    msg_response.setText(s);
                }
            });

        }catch(Exception e){

            Toast.makeText(getBaseContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();


        }



    }

}
