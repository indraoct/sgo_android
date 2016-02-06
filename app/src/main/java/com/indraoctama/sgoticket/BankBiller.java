package com.indraoctama.sgoticket;

/**
 * Created by Indra Octama on 11/16/2015.
 * Set value
 */
public class BankBiller {

    private String bank_code = "";
    private String product_code = "";
    private String bank_name = "";
    private String product_name = "";
    private String product_h2h = "";
    private String product_type = "";

    public BankBiller(){

    }

    //SET
    public void set_bank_code(String _bank_code){
        this.bank_code = _bank_code;
    }

    public void set_product_code(String _product_code){
        this.product_code = _product_code;
    }

    public void set_bank_name(String _bank_name){
        this.bank_name = _bank_name;
    }

    public void set_product_name(String _product_name){
        this.product_name = _product_name;
    }

    public void set_product_h2h(String _product_h2h){
        this.product_name = _product_h2h;
    }

    public void set_product_type(String _product_type){
        this.product_type = _product_type;
    }



    //GET
    public String get_bank_code(){
        return this.bank_code;
    }

    public String get_product_code(){
        return this.product_code;
    }

    public String get_bank_name(){
        return this.bank_name;
    }

    public String get_product_name(){
        return this.product_name;
    }

    public String get_product_h2h(){
        return this.product_h2h;
    }

    public String get_product_type(){
        return this.product_code;
    }




}
