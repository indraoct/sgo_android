package com.indraoctama.library;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by Indra Octama on 8/30/2015.
 */
public class Currency {

    public String idr(Double amount){
        DecimalFormat df = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setCurrencySymbol("");
        dfs.setMonetaryDecimalSeparator(',');
        dfs.setGroupingSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        String result = "IDR " + df.format(amount);

        return result;
    }
}
