package com.example.dmiranda.caremetoo.listAdapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dmiranda.caremetoo.R;

import java.util.List;

import cicese.edu.caremetooAPI.Event;

/**
 * Created by dmiranda on 12/6/14.
 */
public class EventHistoryAdapter extends ArrayAdapter<Event> {
    List<Event> objects;
    Context mContext;
    public EventHistoryAdapter(Context context, int resource, List<Event> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.events_history_item, parent, false);
        TextView SudsTextView = (TextView) rowView.findViewById(R.id.event_item_suds_score);
        TextView DateTextView = (TextView) rowView.findViewById(R.id.event_item_date);
        TextView TimeTextView = (TextView) rowView.findViewById(R.id.event_item_time);
        ImageView ActivityImageView = (ImageView) rowView.findViewById(R.id.event_item_img);

        String[] strings = splitTimestamp(objects.get(position).getTIMESTAMP());
        SudsTextView.setText((CharSequence) objects.get(position).getMOOD());
        DateTextView.setText(strings[0]);
        TimeTextView.setText(strings[1]);
        ActivityImageView.setImageDrawable(activityToDrawable(objects.get(position).getACTIVITY()));

        return rowView;
    }
    private String[] splitTimestamp(String timestamp){
        return timestamp.split(" ");

    }

    private Drawable activityToDrawable(String activity){
        Drawable result = null;
        if(activity.equals("feeding")) {
            result = mContext.getResources().getDrawable(R.drawable.feeding);
        }
        else if(activity.equals("dressing")){
            result =  mContext.getResources().getDrawable(R.drawable.dressing);
        }else if(activity.equals("medicine")){
            result =  mContext.getResources().getDrawable(R.drawable.medicine);
        }else if(activity.equals("restroom")){
            result =  mContext.getResources().getDrawable(R.drawable.restroom);
        }else if(activity.equals("showering")){
            result =  mContext.getResources().getDrawable(R.drawable.showering);
        }else if(activity.equals("talking")){
            result =  mContext.getResources().getDrawable(R.drawable.talking);
        }
        return result;
    }
}
