package com.indraoctama;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.indraoctama.library.ParamConst;
import com.indraoctama.library.TinyDB;
import com.indraoctama.library.UrlHit;
import com.indraoctama.sgoticket.BookingConfirmActivity;
import com.indraoctama.sgoticket.R;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class InquiryTransaction extends Activity {

    private String urlinquirysessiontransaction = ParamConst.getInquiryTransactionUrl;
    RelativeLayout btnLayout,loadingpanel,detailLayout;
    LinearLayout return_info;
    Button btnGenerate,btnBook;
    Context context;
    String session_id,product_code;
    TextView amount,tax_total,total_amount;
    TextView cd_airport_depart,cd_airport_arrive,cd_airport_depart_return,cd_airport_arrive_return;
    TextView departure_time_time,departure_time_date,arrive_time_time,arrive_time_date,departure_time_time_return,departure_time_date_return,arrive_time_time_return,arrive_time_date_return;
    Integer total_tax;
    ImageView img_logo_pergi;
    ImageView img_logo_pulang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry_transaction);

        context = getApplication().getBaseContext();

        final TinyDB tinydb = new TinyDB(context);

        session_id = tinydb.getString("session_id");
        product_code = tinydb.getString("product_code");

      //init components
        __init();

    }


    public void __init(){

        btnLayout = (RelativeLayout) findViewById(R.id.btnLayout);
        loadingpanel = (RelativeLayout) findViewById(R.id.loadingPanel);
        btnGenerate = (Button) findViewById(R.id.btnGenerate);
        detailLayout = (RelativeLayout) findViewById(R.id.detailLayout);
        return_info = (LinearLayout) findViewById(R.id.return_info);
        btnBook = (Button) findViewById(R.id.btn_book);

        //detail amount

        amount = (TextView) findViewById(R.id.amount);
        tax_total = (TextView) findViewById(R.id.tax_total);
        total_amount = (TextView) findViewById(R.id.total_amount);
        img_logo_pergi = (ImageView) findViewById(R.id.img_logo_pergi);
        img_logo_pulang = (ImageView) findViewById(R.id.img_logo_pulang);

        //detail schedule

        cd_airport_depart = (TextView) findViewById(R.id.cd_airport_depart);
        cd_airport_arrive = (TextView) findViewById(R.id.cd_airport_arrive);
        cd_airport_depart_return = (TextView) findViewById(R.id.cd_airport_depart_return);
        cd_airport_arrive_return = (TextView) findViewById(R.id.cd_airport_arrive_return);

        departure_time_time = (TextView) findViewById(R.id.departure_time_time);
        departure_time_date = (TextView) findViewById(R.id.departure_time_date);
        arrive_time_time = (TextView) findViewById(R.id.arrive_time_time);
        arrive_time_date = (TextView) findViewById(R.id.arrive_time_date);

        departure_time_time_return = (TextView) findViewById(R.id.departure_time_time_return);
        departure_time_date_return = (TextView) findViewById(R.id.departure_time_date_return);
        arrive_time_time_return = (TextView) findViewById(R.id.arrive_time_time_return);
        arrive_time_date_return = (TextView) findViewById(R.id.arrive_time_date_return);



        btnGenerate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadingpanel.setVisibility(View.VISIBLE);
                btnLayout.setVisibility(View.GONE);

                try{

                    RequestParams params = new RequestParams();
                    params.put("session_id",session_id);
                    params.put("product_code",product_code);

                    UrlHit.post(getBaseContext(),urlinquirysessiontransaction,params,new TextHttpResponseHandler() {
                        @Override
                        public void onFailure(int i, Header[] headers, String s, Throwable throwable) {

                            loadingpanel.setVisibility(View.GONE);
                            btnLayout.setVisibility(View.GONE);
                            detailLayout.setVisibility(View.VISIBLE);

                            Toast.makeText(getBaseContext(),s,Toast.LENGTH_LONG).show();


                        }

                        @Override
                        public void onSuccess(int i, Header[] headers, String s) {

                            loadingpanel.setVisibility(View.GONE);
                            btnLayout.setVisibility(View.GONE);
                            detailLayout.setVisibility(View.VISIBLE);

                            Toast.makeText(getBaseContext(),s,Toast.LENGTH_LONG).show();

                            try{

                                JSONObject jObj = new JSONObject(s);

                                //JSON DATA
                                JSONObject objData = jObj.getJSONObject("data");
                                Integer objBasicFare = Integer.parseInt(objData.getString("basic_fare")) ;
                                Integer objTaxTotal = Integer.parseInt(objData.getString("tax_total"));
                                Integer objDiscountTotal = Integer.parseInt(objData.getString("discount_total"));
                                Integer objFeeTotal = Integer.parseInt(objData.getString("fee_total"));
                                Integer objSsrTotal = Integer.parseInt(objData.getString("ssr_total"));
                                Integer objInsuranceTotal = Integer.parseInt(objData.getString("insurance_total"));
                                final Integer objTotal = Integer.parseInt(objData.getString("total"));
                                Integer objAdminAgentTotal = Integer.parseInt(objData.getString("admin_agent_total"));

                                String objcd_airport_depart = objData.getString("origin");
                                String objcd_airport_arrive = objData.getString("destination");

                                String objcd_airport_depart_return = objData.getString("destination");
                                String objcd_airport_arrive_return = objData.getString("origin");

                                //SUM total tax
                                total_tax = objTaxTotal + objFeeTotal + objInsuranceTotal + objSsrTotal + objAdminAgentTotal - objDiscountTotal;

                                //TextView Set Text
                                tax_total.setText(Integer.toString(total_tax));
                                amount.setText(Integer.toString(objBasicFare));
                                total_amount.setText(Integer.toString(objTotal));

                                //SET Code Airport
                                cd_airport_depart.setText(objcd_airport_depart);
                                cd_airport_arrive.setText(objcd_airport_arrive);

                                cd_airport_depart_return.setText(objcd_airport_depart_return);
                                cd_airport_arrive_return.setText(objcd_airport_arrive_return);

                                //SET Image Logo

                                if(product_code.equals("AR")){

                                    img_logo_pergi.setImageResource(R.drawable.ar);
                                    img_logo_pulang.setImageResource(R.drawable.ar);
                                }else if(product_code.equals("CK")){

                                    img_logo_pergi.setImageResource(R.drawable.ck);
                                    img_logo_pulang.setImageResource(R.drawable.ck);
                                }else if(product_code.equals("EA")){

                                    img_logo_pergi.setImageResource(R.drawable.ea);
                                    img_logo_pulang.setImageResource(R.drawable.ea);
                                }else if(product_code.equals("GS")){

                                    img_logo_pergi.setImageResource(R.drawable.gs);
                                    img_logo_pulang.setImageResource(R.drawable.gs);
                                }else if(product_code.equals("KT")){

                                    img_logo_pergi.setImageResource(R.drawable.kt);
                                    img_logo_pulang.setImageResource(R.drawable.kt);
                                }else if(product_code.equals("LI")){

                                    img_logo_pergi.setImageResource(R.drawable.li);
                                    img_logo_pulang.setImageResource(R.drawable.li);
                                }else if(product_code.equals("TG")){

                                    img_logo_pergi.setImageResource(R.drawable.tg);
                                    img_logo_pulang.setImageResource(R.drawable.tg);
                                }else if(product_code.equals("TS")){

                                    img_logo_pergi.setImageResource(R.drawable.ts);
                                    img_logo_pulang.setImageResource(R.drawable.ts);
                                }else if(product_code.equals("SJ")){

                                    img_logo_pergi.setImageResource(R.drawable.sj);
                                    img_logo_pulang.setImageResource(R.drawable.sj);
                                }else if(product_code.equals("VF")){

                                    img_logo_pergi.setImageResource(R.drawable.vf);
                                    img_logo_pulang.setImageResource(R.drawable.vf);
                                }

                                //JSON Data Info

                                JSONObject objDataInfo = jObj.getJSONObject("data_info");

                                String objDeparture_time_time = objDataInfo.getString("departure_time_time");
                                String objDeparture_time_date = objDataInfo.getString("departure_time_date");
                                String objArrive_time_time = objDataInfo.getString("arrival_time_time");
                                String objArrive_time_date = objDataInfo.getString("arrival_time_date");

                                String objDeparture_time_time_return = objDataInfo.getString("departure_time_time_return");
                                String objDeparture_time_date_return = objDataInfo.getString("departure_time_date_return");
                                String objArrive_time_time_return = objDataInfo.getString("arrival_time_time_return");
                                String objArrive_time_date_return = objDataInfo.getString("arrival_time_date_return");


                                //SET Component

                                //IF RETURN
                                if(!objDeparture_time_time_return.equals("")){

                                    return_info.setVisibility(View.VISIBLE);
                                }

                                departure_time_time.setText(objDeparture_time_time);
                                departure_time_date.setText(objDeparture_time_date);
                                arrive_time_time.setText(objArrive_time_time);
                                arrive_time_date.setText(objArrive_time_date);

                                departure_time_time_return.setText(objDeparture_time_time_return);
                                departure_time_date_return.setText(objDeparture_time_date_return);
                                arrive_time_time_return.setText(objArrive_time_time_return);
                                arrive_time_date_return.setText(objArrive_time_date_return);



                                btnBook.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        TinyDB TDB = new TinyDB(context);

                                        TDB.putString("total_amount",Integer.toString(objTotal));

                                        Intent intent = new Intent(InquiryTransaction.this, BookingConfirmActivity.class);

                                        startActivity(intent);

                                    }
                                });




                            }catch(Exception e){

                                e.printStackTrace();
                            }

                        }
                    });


                }catch(Exception e){

                    Toast.makeText(getBaseContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();

                }

            }
        });




    }

}
