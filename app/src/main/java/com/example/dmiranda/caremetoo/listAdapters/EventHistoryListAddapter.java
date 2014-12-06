package com.example.dmiranda.caremetoo.listAdapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.dmiranda.caremetoo.EventItemFragment;

/**
 * Created by dmiranda on 12/5/14.
 */
public class EventHistoryListAddapter extends ArrayAdapter<EventItemFragment> {
    public EventHistoryListAddapter(Context context, int resource) {
        super(context, resource);
    }

}
