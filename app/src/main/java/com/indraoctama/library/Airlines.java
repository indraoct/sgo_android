package com.indraoctama.library;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 7/13/2015.
 */
public class Airlines {


    public List CodeAirlines(){

        List codeAirlines = new ArrayList <String>();

            codeAirlines.add("AR");
            codeAirlines.add("CK");
            codeAirlines.add("EA");
            codeAirlines.add("GS");
            codeAirlines.add("KT");
            codeAirlines.add("LI");
            codeAirlines.add("TG");
            codeAirlines.add("TS");
            codeAirlines.add("SJ");
            codeAirlines.add("VF");

        return codeAirlines;

    }

    public List NameAirlines(){

        List nameAirlines = new ArrayList <String>();

        nameAirlines.add("Air Asia");
        nameAirlines.add("Citilink");
        nameAirlines.add("Express");
        nameAirlines.add("Garuda");
        nameAirlines.add("Kalstar");
        nameAirlines.add("Lion");
        nameAirlines.add("Trigana");
        nameAirlines.add("Transnusa");
        nameAirlines.add("Sriwijaya");
        nameAirlines.add("Jestar");

        return nameAirlines;

    }

}
