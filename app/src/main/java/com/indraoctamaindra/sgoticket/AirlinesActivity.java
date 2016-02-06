package com.indraoctamaindra.sgoticket;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import android.support.v4.app.DialogFragment;
import com.indraoctamaindra.library.Airlines;
import com.indraoctamaindra.library.Airport;


public class AirlinesActivity extends FragmentActivity {


    EditText mEdit,mEdit2;
    Switch rute;
    Spinner adult_view,child_view,infant_view;
    Button btnSubmit;
    String routeText = "OW";

    public String depart_from;
    public String arrive_to;




    public void __init(){

        /*
         *  Spinner
         */
        final Spinner airlines = (Spinner) findViewById(R.id.airlines);
        final Spinner depart_from = (Spinner) findViewById(R.id.depart_from);
        final Spinner arrive_to = (Spinner) findViewById(R.id.arrive_to);



        /*
         * Object Airport & Airlines
         */
        Airlines objAirlines = new Airlines();
        Airport objAirport = new Airport();

        /*
         *  List code ; name of airlines
         * List code ; name of airport
         */
        List codeAirlines = objAirlines.CodeAirlines();
        List nameAirlines = objAirlines.NameAirlines();
        List codeAirport = objAirport.CodeAirport();
        List nameAirport = objAirport.NameAirport();

        /*
         * Menyusun Spinner Array
         */

        String[] spinnerAirlinesArray = new String[codeAirlines.size()];
        final HashMap<Object, Object> spinnerMapAirlines = new HashMap<>();
        for (int i = 0; i < codeAirlines.size(); i++)
        {
            spinnerMapAirlines.put(nameAirlines.get(i), codeAirlines.get(i));
            spinnerAirlinesArray[i] = (String) nameAirlines.get(i);
        }

        String[] spinnerArray = new String[codeAirport.size()];
        final HashMap<Object, Object> spinnerMap = new HashMap<>();
        for (int i = 0; i < codeAirport.size(); i++)
        {
            spinnerMap.put(nameAirport.get(i), codeAirport.get(i));
            spinnerArray[i] = (String) nameAirport.get(i);
        }

        /*
         * Adapter for data spinner airlines
         */
        ArrayAdapter<String> adapterAirlines =new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item, spinnerAirlinesArray);
        adapterAirlines.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /*
         * Adapter for data spinner airport
         */
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /*
         * Implementasi
         */
        airlines.setAdapter(adapterAirlines);
        depart_from.setAdapter(adapter);
        arrive_to.setAdapter(adapter);

        //default CGK
        depart_from.setSelection(14);
        //default JOG
        arrive_to.setSelection(22);



        //-------------------------------------------------------------------------------------------END OF Spinner

        //------------------------------------------------------------------------------------- Switch Rute

        /*
         * Switch Return / Oneway
         */

        rute = (Switch) findViewById(R.id.rute);

        rute.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    LinearLayout layout_return = (LinearLayout) (findViewById(R.id.route_return));
                    TextView textReturn = (TextView) findViewById(R.id.text_return);

                if(isChecked == true) {
                    layout_return.setVisibility(View.VISIBLE);
                    textReturn.setVisibility(View.VISIBLE);
                    rute.setText("Pulang Pergi");
                    routeText = "RT";

                }else{
                    layout_return.setVisibility(View.INVISIBLE);
                    textReturn.setVisibility(View.INVISIBLE);
                    rute.setText("Satu Arah");
                    routeText = "OW";
                }
            }
        });

        //Passengers-------------------------------------------------------------------------------------------
        adult_view = (Spinner) findViewById(R.id.adult);
        child_view = (Spinner) findViewById(R.id.child);
        infant_view = (Spinner) findViewById(R.id.infant);

        //Button Submit----------------------------------------------------------------------------------------

        btnSubmit = (Button) findViewById(R.id.btnSubmit);


        //Action Submit
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String destination = "";
                String date_departure = "";
                String date_arrival = "";
                String flight_type = "";
                String adult = "0";
                String child = "0";
                String infant = "0";
                String origin = "";
                String product_code = "";

                //Spinner Product Code
                String name = airlines.getSelectedItem().toString();
                Object code_airlines = spinnerMapAirlines.get(name);
                product_code = code_airlines.toString();

                //Spinner Origin
                String name1 = depart_from.getSelectedItem().toString();
                Object code_airport1 = spinnerMap.get(name1);
                origin = (String) code_airport1.toString();

                //Spinner Destination
                String name2 = arrive_to.getSelectedItem().toString();
                Object code_airport2 = spinnerMap.get(name2);
                destination = (String) code_airport2.toString();

                //Datepicker Get Value
                mEdit =  (EditText)findViewById(R.id.depart_date);
                mEdit2 =  (EditText)findViewById(R.id.arrival_date);

                //Format Date Standard
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                if(!mEdit.getText().toString().equals("")) {
                    java.util.Date date1 = new Date(mEdit.getText().toString());
                    date_departure = formatter.format(date1);
                }

                if(!mEdit2.getText().toString().equals("")) {
                    java.util.Date date2 = new Date(mEdit2.getText().toString());
                    date_arrival = formatter.format(date2);
                }

                //Flight Type
                flight_type = routeText;

                //passengers
                adult = adult_view.getSelectedItem().toString();
                child =  child_view.getSelectedItem().toString();
                infant =  infant_view.getSelectedItem().toString();

                Integer adult_int = Integer.parseInt(adult);
                Integer child_int = Integer.parseInt(child);
                Integer infant_int = Integer.parseInt(infant);


                if(flight_type.equals("RT")){
                    if(!date_arrival.equals("") && !(date_departure.equals(""))){

                        if(!(child_int > adult_int) && !(infant_int > adult_int)) {


                            //Intent & put extra
                            Intent intent = new Intent(AirlinesActivity.this, AavailabilityActivity.class);
                            intent.putExtra("destination", destination);
                            intent.putExtra("date_departure", date_departure);
                            intent.putExtra("date_arrival", date_arrival);
                            intent.putExtra("flight_type", flight_type);
                            intent.putExtra("adult", adult);
                            intent.putExtra("child", child);
                            intent.putExtra("infant", infant);
                            intent.putExtra("origin", origin);
                            intent.putExtra("product_code", product_code);

                            //start activity
                            startActivity(intent);

                        }else{

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(AirlinesActivity.this);
                            builder1.setMessage("Jumlah Penumpang Anak / Balita Tidak boleh melebihi penumpang dewasa!!");
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

                        }



                    }else{

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(AirlinesActivity.this);
                        builder1.setMessage("Tanggal Pergi/Pulang harap diisi!");
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

                    }



                }else{

                    if(!date_departure.equals("")){

                        if(!(child_int > adult_int) && !(infant_int > adult_int)) {

                                    //Intent & put extra
                                    Intent intent = new Intent(AirlinesActivity.this, AavailabilityActivity.class);
                                    intent.putExtra("destination", destination);
                                    intent.putExtra("date_departure", date_departure);
                                    intent.putExtra("date_arrival", date_arrival);
                                    intent.putExtra("flight_type", flight_type);
                                    intent.putExtra("adult", adult);
                                    intent.putExtra("child", child);
                                    intent.putExtra("infant", infant);
                                    intent.putExtra("origin", origin);
                                    intent.putExtra("product_code", product_code);

                                    //start activity
                                    startActivity(intent);

                        }else{

                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AirlinesActivity.this);
                                    builder1.setMessage("Jumlah Penumpang Anak / Balita Tidak boleh melebihi penumpang dewasa!!");
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
                        }

                    }else{

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(AirlinesActivity.this);
                        builder1.setMessage("Tanggal Pergi harap diisi!");
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

                    }
                }









            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airlines);
        //component
        __init();


    }

    //-------------------------------------------------------- Date Picker
   /*
    * action select date
    */
    public void selectDate(View view) {
        DialogFragment newFragment = new SelectDateFragment();
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    /*
    * action select date
    */
    public void selectDate2(View view) {
        DialogFragment newFragment = new SelectDateFragment2();
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    /*
     * Populate Date
     */
    public void populateSetDate(int year, int month, int day) {

        mEdit =  (EditText)findViewById(R.id.depart_date);
        mEdit.setText(month + "/" + day + "/" + year);
    }

    /*
     * Populate Date
     */
    public void populateSetDate2(int year, int month, int day) {

        mEdit2 =  (EditText)findViewById(R.id.arrival_date);
        mEdit2.setText(month + "/" + day + "/" + year);
    }

    /*
     * SelectDate Fragment
     */
    @SuppressLint("ValidFragment")
    public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);

            Date today = new Date();
            Date min_date;

            min_date = new Date(today.getTime() + (0 *  24 * 60 * 60 * 1000L));
            DatePickerDialog dialog = new DatePickerDialog(getActivity(),this,yy,mm,dd);
           dialog.getDatePicker().setMinDate(min_date.getTime());

            return dialog;
        }

        /*
         * On Date Set
         */
    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }
    }


    /*
     * SelectDate Fragment 2
     */
    @SuppressLint("ValidFragment")
    public class SelectDateFragment2 extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);

            Date today = new Date();
            Date min_date;

            min_date = new Date(today.getTime() + (1 *  24 * 60 * 60 * 1000L));

            DatePickerDialog dialog = new DatePickerDialog(getActivity(),this,yy,mm,dd);
            dialog.getDatePicker().setMinDate(min_date.getTime());

            return dialog;
        }

        /*
         * On Date Set
         */
        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate2(yy, mm+1, dd);
        }
    }



}
