package com.indraoctama.sgoticket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.indraoctama.library.TinyDB;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class PopupPassengerActivity extends FragmentActivity {

    String nomer,pax_typeString;
    TextView nomertext,titletext;
    Button btnSubmit,btnBack;
    EditText first_name,last_name,birthdate,pax_type,qaff,passport_expired,passport_number;
    Spinner title,gender;
    Context context;
    Integer MAX_DATE = 12;
    Integer MAX_DATE_CHD = 6;
    Integer MAX_DATE_INF = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_passenger);

        Intent intent = getIntent();

        nomer = intent.getStringExtra("nomer");
        pax_typeString = intent.getStringExtra("pax_type");

        __init();

    }


    public void __init(){

        context = getApplication().getBaseContext();

        final TinyDB tinydb = new TinyDB(context);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnBack = (Button) findViewById(R.id.btnback);
        title = (Spinner) findViewById(R.id.title);
        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        birthdate = (EditText) findViewById(R.id.birthdate);
        pax_type = (EditText) findViewById(R.id.pax_type);
        nomertext = (TextView) findViewById(R.id.nomer);
        titletext = (TextView) findViewById(R.id.titletext);
        gender = (Spinner) findViewById(R.id.gender);
        qaff = (EditText) findViewById(R.id.qaff);
        passport_number = (EditText) findViewById(R.id.passport_number);
        passport_expired = (EditText) findViewById(R.id.passport_expired);


        //Get Tiny DB
        ArrayList<String> arrGenderDB = tinydb.getListString("gender");
        final ArrayList<String> arrTitleDB = tinydb.getListString("title");
        ArrayList<String> arrFirstNameDB = tinydb.getListString("first_name");
        ArrayList<String> arrLastNameDB = tinydb.getListString("last_name");
        ArrayList<String> arrBirthDateDB = tinydb.getListString("birthdate");
        ArrayList<String> arrQaffDB = tinydb.getListString("qaff");
        ArrayList<String> arrPaxtypeDB = tinydb.getListString("pax_type");
        ArrayList<String> arrPassportNumberDB = tinydb.getListString("passport_number");
        ArrayList<String> arrPassportExpiredDB = tinydb.getListString("passport_expired");

       /*
        * Set value if title size != 0
        */

        if(arrTitleDB.size() != 0) {

            if (arrTitleDB.get(Integer.parseInt(nomer)).toString().equals("MR")) {
                title.setSelection(0);
            } else if (arrTitleDB.get(Integer.parseInt(nomer)).toString().equals("MRS")) {
                title.setSelection(1);
            } else {
                title.setSelected(false);
            }

            if (arrGenderDB.get(Integer.parseInt(nomer)).toString().equals("Male")) {
                gender.setSelection(0);
            } else if (arrGenderDB.get(Integer.parseInt(nomer)).toString().equals("Female")) {
                gender.setSelection(1);
            } else {
                gender.setSelected(false);
            }
            first_name.setText(arrFirstNameDB.get(Integer.parseInt(nomer)).toString());
            last_name.setText(arrLastNameDB.get(Integer.parseInt(nomer)).toString());
            birthdate.setText(arrBirthDateDB.get(Integer.parseInt(nomer)).toString());
            pax_type.setText(arrPaxtypeDB.get(Integer.parseInt(nomer)).toString());
            nomertext.setText(Integer.toString(Integer.parseInt(nomer) + 1));
            qaff.setText(arrQaffDB.get(Integer.parseInt(nomer)).toString());
            passport_number.setText(arrPassportNumberDB.get(Integer.parseInt(nomer)).toString());
            passport_expired.setText(arrPassportExpiredDB.get(Integer.parseInt(nomer)).toString());
        }

        pax_type.setText(pax_typeString);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ArrayList<String> genderDB = new ArrayList<String>();
                ArrayList<String> titleDB = new ArrayList<String>();
                ArrayList<String> firstnameDB = new ArrayList<String>();
                ArrayList<String> lastnameDB = new ArrayList<String>();
                ArrayList<String> birthdateDB = new ArrayList<String>();
                ArrayList<String> qaffDB = new ArrayList<String>();
                ArrayList<String> paxtypeDB = new ArrayList<String>();
                ArrayList<String> passportnumberDB = new ArrayList<String>();
                ArrayList<String> passportexpiredDB = new ArrayList<String>();

                genderDB = tinydb.getListString("gender");
                titleDB =  tinydb.getListString("title");
                firstnameDB = tinydb.getListString("first_name");
                lastnameDB = tinydb.getListString("last_name");
                birthdateDB = tinydb.getListString("birthdate");
                qaffDB = tinydb.getListString("qaff");
                paxtypeDB = tinydb.getListString("pax_type");
                passportnumberDB = tinydb.getListString("passport_number");
                passportexpiredDB = tinydb.getListString("passport_expired");

                ArrayList<String> arrGender = genderDB;
                ArrayList<String> arrTitle = titleDB;
                ArrayList<String> arrFirstname = firstnameDB;
                ArrayList<String> arrLastname = lastnameDB;
                ArrayList<String> arrBirthdate = birthdateDB;
                ArrayList<String> arrQaff = qaffDB;
                ArrayList<String> arrPaxType = paxtypeDB;
                ArrayList<String> arrPassportNumber = passportnumberDB;
                ArrayList<String> arrPassportExpired = passportexpiredDB;


                if(arrTitleDB.size() !=  0) {

                    arrTitle.set(Integer.parseInt(nomer), title.getSelectedItem().toString());
                    arrGender.set(Integer.parseInt(nomer), gender.getSelectedItem().toString());
                    arrFirstname.set(Integer.parseInt(nomer), first_name.getText().toString());
                    arrLastname.set(Integer.parseInt(nomer), last_name.getText().toString());
                    arrBirthdate.set(Integer.parseInt(nomer), birthdate.getText().toString());
                    arrQaff.set(Integer.parseInt(nomer), qaff.getText().toString());
                    arrPaxType.set(Integer.parseInt(nomer), pax_type.getText().toString());
                    arrPassportNumber.set(Integer.parseInt(nomer),passport_number.getText().toString());
                    arrPassportExpired.set(Integer.parseInt(nomer),passport_expired.getText().toString());

                    tinydb.putListString("gender",arrGender);
                    tinydb.putListString("title",arrTitle);
                    tinydb.putListString("first_name",arrFirstname);
                    tinydb.putListString("last_name",arrLastname);
                    tinydb.putListString("birthdate",arrBirthdate);
                    tinydb.putListString("qaff",arrQaff);
                    tinydb.putListString("pax_type",arrPaxType);
                    tinydb.putListString("passport_number",arrPassportNumber);
                    tinydb.putListString("passport_expired",arrPassportExpired);

                    finish();


                }else{

                    if(first_name.getText().toString().equals("")) {
                        Toast.makeText(getBaseContext(), "Tolong Isi Firstname!", Toast.LENGTH_LONG).show();
                    }else if(last_name.getText().toString().equals("")){
                        Toast.makeText(getBaseContext(), "Tolong Isi Lastname!", Toast.LENGTH_LONG).show();
                    }else if(birthdate.getText().toString().equals("")) {
                        Toast.makeText(getBaseContext(), "Tolong Isi Birthdate!", Toast.LENGTH_LONG).show();
                    }else if(passport_number.getText().toString().equals("")){
                        Toast.makeText(getBaseContext(), "Tolong Isi Passport Number!", Toast.LENGTH_LONG).show();
                    }else if(passport_expired.getText().toString().equals("")){
                        Toast.makeText(getBaseContext(), "Tolong Isi Passport Expired!", Toast.LENGTH_LONG).show();
                    }

                    else {


                        arrTitle.add(Integer.parseInt(nomer), title.getSelectedItem().toString());
                        arrGender.add(Integer.parseInt(nomer), gender.getSelectedItem().toString());
                        arrFirstname.add(Integer.parseInt(nomer), first_name.getText().toString());
                        arrLastname.add(Integer.parseInt(nomer), last_name.getText().toString());
                        arrBirthdate.add(Integer.parseInt(nomer), birthdate.getText().toString());
                        arrPassportNumber.add(Integer.parseInt(nomer),passport_number.getText().toString());
                        arrPassportExpired.add(Integer.parseInt(nomer),passport_expired.getText().toString());

                        if(qaff.getText().toString().equals("")){
                            arrQaff.add(Integer.parseInt(nomer), "  ");
                        }else {
                            arrQaff.add(Integer.parseInt(nomer), qaff.getText().toString());
                        }

                        arrPaxType.add(Integer.parseInt(nomer), pax_type.getText().toString());

                        tinydb.putListString("gender", arrGender);
                        tinydb.putListString("title", arrTitle);
                        tinydb.putListString("first_name", arrFirstname);
                        tinydb.putListString("last_name", arrLastname);
                        tinydb.putListString("birthdate", arrBirthdate);
                        tinydb.putListString("qaff", arrQaff);
                        tinydb.putListString("pax_type", arrPaxType);
                        tinydb.putListString("passport_number",arrPassportNumber);
                        tinydb.putListString("passport_expired",arrPassportExpired);


                        finish();
                    }


                }


            }
        });
    }


    /*
    * action select date
    */
    public void selectDate(View view) {
        DialogFragment newFragment = new SelectDateFragment();
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    public void selectDate2(View view) {
        DialogFragment newFragment = new SelectDateFragment2();
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }


    /*
    * Populate Date
    */
    public void populateSetDate(int year, int month, int day) {

        birthdate =  (EditText)findViewById(R.id.birthdate);
        birthdate.setText(year + "-" + month + "-" + day);
    }


    public void populateSetDate2(int year, int month, int day) {

        passport_expired =  (EditText)findViewById(R.id.passport_expired);
        passport_expired.setText(year + "-" + month + "-" + day);
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


            DatePickerDialog dialog = new DatePickerDialog(getActivity(),this,yy,mm,dd);

            Date today = new Date();
            Date max_date;

            if(pax_type.getText().toString().equals("ADT")) {

                max_date = new Date(today.getTime() - (MAX_DATE * 365 * 24 * 60 * 60 * 1000L));
                dialog.getDatePicker().setMaxDate(max_date.getTime());

            }else
            if(pax_type.getText().toString().equals("CHD")){

                max_date = new Date(today.getTime() - (MAX_DATE_CHD * 365 * 24 * 60 * 60 * 1000L));
                dialog.getDatePicker().setMaxDate(max_date.getTime());

            }else
            if(pax_type.getText().toString().equals("INF")){

                max_date = new Date(today.getTime() - (14 * 24 * 60 * 60 * 1000L));
                dialog.getDatePicker().setMaxDate(max_date.getTime());

            }

            return dialog;
        }

        /*
         * On Date Set
         */
        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }
    }


    @SuppressLint("ValidFragment")
    public class SelectDateFragment2 extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog dialog = new DatePickerDialog(getActivity(),this,yy,mm,dd);


            return dialog;
        }

        /*
         * On Date Set
         */
        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate2(yy, mm + 1, dd);
        }
    }

}
