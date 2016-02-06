package com.indraoctamaindra.sgoticket;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;


public class Passenger2Activity extends Activity {

    LinearLayout linear2;
    EditText title_text1,title1,first_name1,last_name1,birthdate1,pax_type1;
    EditText title_text2,title2,first_name2,last_name2,birthdate2,pax_type2;
    EditText title_text3,title3,first_name3,last_name3,birthdate3,pax_type3;
    EditText title_text4,title4,first_name4,last_name4,birthdate4,pax_type4;
    EditText title_tex5,title5,first_name5,last_name5,birthdate5,pax_type5;
    EditText title_tex6,title6,first_name6,last_name6,birthdate6,pax_type6;
    EditText title_tex7,title7,first_name7,last_name7,birthdate7,pax_type7;
    EditText title_tex8,title8,first_name8,last_name8,birthdate8,pax_type8;
    EditText title_tex9,title9,first_name9,last_name9,birthdate9,pax_type9;
    EditText title_tex10,title10,first_name10,last_name10,birthdate10,pax_type10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger2);

        //init component
        __init();

    }


    public void __init(){

        linear2 = (LinearLayout) findViewById(R.id.linear2);
        linear2.bringToFront();

        title1 = (EditText) findViewById(R.id.title1);
        first_name1 = (EditText) findViewById(R.id.first_name1);
        last_name1 = (EditText) findViewById(R.id.last_name1);
        birthdate1 = (EditText) findViewById(R.id.birthdate1);





    }

}
