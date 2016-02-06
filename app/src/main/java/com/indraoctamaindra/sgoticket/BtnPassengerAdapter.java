package com.indraoctamaindra.sgoticket;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 8/3/2015.
 */
public class BtnPassengerAdapter extends BaseAdapter {

    private Activity mContext;
    private List<Map<String,String>> mList;
    private LayoutInflater mLayoutInflater = null;

    public BtnPassengerAdapter(Activity context,   List<Map<String,String>> list) {
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
        BtnPassengerViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.list_btnpassenggers, null);
            viewHolder = new BtnPassengerViewHolder(v);
            v.setTag(viewHolder);

        }else{

            viewHolder = (BtnPassengerViewHolder) v.getTag();

        }

        Map<String,String> helper = new HashMap<>();

        viewHolder.btn.setText(mList.get(position).get("btnText"));
        viewHolder.hdtext.setText(mList.get(position).get("hdtext"));


        return v;
    }
}

class BtnPassengerViewHolder{

    public TextView btn;
    public TextView hdtext;


    public BtnPassengerViewHolder(View base){


        btn = (TextView) base.findViewById(R.id.textviewpass);
        hdtext = (TextView) base.findViewById(R.id.hdtext);


    }

}

