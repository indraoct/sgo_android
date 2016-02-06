package com.indraoctama.sgoticket;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Indra Octama on 7/29/2015.
 */
public class PassengerAdapter extends BaseAdapter {

    private Activity mContext;
    private   List<Map<String,String>> mList;
    private LayoutInflater mLayoutInflater = null;

    public PassengerAdapter(Activity context,   List<Map<String,String>> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        PassengerViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.list_passenggers, null);
            viewHolder = new PassengerViewHolder(v);
            v.setTag(viewHolder);

        }else{
            viewHolder = (PassengerViewHolder) v.getTag();
        }

        Map<String,String> helper = new HashMap<>();

        viewHolder.Paxtype.setText(mList.get(position).get("paxtype"));
        viewHolder.Titletext.setVisibility(View.VISIBLE);
        viewHolder.Title.setVisibility(View.VISIBLE);


        //menghilangkan Title
        if(mList.get(position).get("paxtype") == "CHD"){
            viewHolder.Titletext.setVisibility(View.GONE);
            viewHolder.Title.setVisibility(View.GONE);

        }

        //menghilangkan Title
        if(mList.get(position).get("paxtype") == "INF"){
            viewHolder.Titletext.setVisibility(View.GONE);
            viewHolder.Title.setVisibility(View.GONE);
        }

        Integer Nomer = position + 1;
        viewHolder.Nomer.setText(Nomer.toString());


        return v;
    }


}

class PassengerViewHolder{

    public TextView Nomer;
    public EditText Title;
    public EditText Firstname;
    public EditText Lastname;
    public EditText Birthdate;
    public EditText Paxtype;
    public TextView Titletext;

    public PassengerViewHolder(View base){

        Nomer = (TextView) base.findViewById(R.id.nomer);
        Title = (EditText) base.findViewById(R.id.title);
        Firstname = (EditText) base.findViewById(R.id.first_name);
        Lastname = (EditText) base.findViewById(R.id.last_name);
        Birthdate = (EditText) base.findViewById(R.id.birthdate);
        Paxtype = (EditText) base.findViewById(R.id.pax_type);
        Titletext = (TextView) base.findViewById(R.id.titletext);
    }

}

