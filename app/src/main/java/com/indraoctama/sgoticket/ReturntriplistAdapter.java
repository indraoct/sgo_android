package com.indraoctama.sgoticket;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Indra Octama on 7/21/2015.
 */
public class ReturntriplistAdapter extends BaseAdapter {

    private Activity mContext;
    private List<Map<String,String>> mList;
    private LayoutInflater mLayoutInflater = null;

    public ReturntriplistAdapter(Activity context,   List<Map<String,String>> list){
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
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ReturnListViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.list_schedule, null);
            viewHolder = new ReturnListViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ReturnListViewHolder) v.getTag();
        }

        Map<String,String> helper = new HashMap<>();

        if(mList.get(position).get("product_code").equals("AR")){

            viewHolder.img_logo_center.setImageResource(R.drawable.ar);
        }else if(mList.get(position).get("product_code").equals("CK")){

            viewHolder.img_logo_center.setImageResource(R.drawable.ck);
        }else if(mList.get(position).get("product_code").equals("EA")){

            viewHolder.img_logo_center.setImageResource(R.drawable.ea);
        }else if(mList.get(position).get("product_code").equals("GS")){

            viewHolder.img_logo_center.setImageResource(R.drawable.gs);
        }else if(mList.get(position).get("product_code").equals("KT")){

            viewHolder.img_logo_center.setImageResource(R.drawable.kt);
        }else if(mList.get(position).get("product_code").equals("LI")){

            viewHolder.img_logo_center.setImageResource(R.drawable.li);
        }else if(mList.get(position).get("product_code").equals("TG")){

            viewHolder.img_logo_center.setImageResource(R.drawable.tg);
        }else if(mList.get(position).get("product_code").equals("TS")){

            viewHolder.img_logo_center.setImageResource(R.drawable.ts);
        }else if(mList.get(position).get("product_code").equals("SJ")){

            viewHolder.img_logo_center.setImageResource(R.drawable.sj);
        }else if(mList.get(position).get("product_code").equals("VF")){

            viewHolder.img_logo_center.setImageResource(R.drawable.vf);
        }

        viewHolder.journey_id.setText(mList.get(position).get("journey_id"));
        viewHolder.fare_id.setText(mList.get(position).get("fare_id"));
        viewHolder.class_code.setText(mList.get(position).get("class_code"));
        viewHolder.origin_top.setText(mList.get(position).get("origin"));
        viewHolder.destination_top.setText(mList.get(position).get("destination"));
        viewHolder.amount_top.setText(mList.get(position).get("amount"));

        viewHolder.city_departure.setText(mList.get(position).get("origin"));
        viewHolder.city_arrival.setText(mList.get(position).get("destination"));

        viewHolder.departure_time_time.setText(mList.get(position).get("departure_time_time"));
        viewHolder.departure_time_date.setText(mList.get(position).get("departure_time_date"));

        viewHolder.arrival_time_time.setText(mList.get(position).get("arrival_time_time"));
        viewHolder.arrival_time_date.setText(mList.get(position).get("arrival_time_date"));

        viewHolder.origin_bottom.setText(mList.get(position).get("origin"));
        viewHolder.destination_bottom.setText(mList.get(position).get("destination"));

        viewHolder.flight_no_bottom.setText(mList.get(position).get("flight_number"));
        viewHolder.transit_info.setText(mList.get(position).get("transit_info"));

        if(mList.get(position).get("transit_via").equals("1")){

            viewHolder.transit_via.setVisibility(View.VISIBLE);
        }


        return v;
    }

}

class ReturnListViewHolder {
    public ImageView img_logo_center;
    public TextView journey_id;
    public TextView fare_id;
    public TextView class_code;
    public TextView origin_top;
    public TextView destination_top;
    public TextView amount_top;
    public TextView city_departure;
    public TextView city_arrival;
    public TextView departure_time_time;
    public TextView departure_time_date;
    public TextView arrival_time_time;
    public TextView arrival_time_date;
    public TextView flight_no_bottom;
    public TextView origin_bottom;
    public TextView destination_bottom;
    public TextView transit_info;
    public LinearLayout transit_via;

    public ReturnListViewHolder(View base) {
        journey_id = (TextView) base.findViewById(R.id.journey_id);
        fare_id = (TextView) base.findViewById(R.id.fare_id);
        class_code = (TextView) base.findViewById(R.id.class_code);
        origin_top = (TextView) base.findViewById(R.id.origin_top);
        destination_top = (TextView) base.findViewById(R.id.destination_top);
        img_logo_center = (ImageView) base.findViewById(R.id.img_logo_center);
        amount_top = (TextView) base.findViewById(R.id.amount_top);
        city_departure = (TextView) base.findViewById(R.id.city_departure);
        city_arrival = (TextView) base.findViewById(R.id.city_arrival);
        departure_time_date = (TextView) base.findViewById(R.id.departure_time_date);
        departure_time_time = (TextView) base.findViewById(R.id.departure_time_time);
        arrival_time_date = (TextView) base.findViewById(R.id.arrival_time_date);
        arrival_time_time = (TextView) base.findViewById(R.id.arrival_time_time);
        flight_no_bottom = (TextView) base.findViewById(R.id.flight_no_bottom);
        origin_bottom = (TextView) base.findViewById(R.id.origin_bottom);
        destination_bottom = (TextView) base.findViewById(R.id.destination_bottom);
        transit_info = (TextView) base.findViewById(R.id.transit_info);
        transit_via = (LinearLayout) base.findViewById(R.id.transit_via);

    }
}
