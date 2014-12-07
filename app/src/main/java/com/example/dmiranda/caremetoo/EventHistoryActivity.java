package com.example.dmiranda.caremetoo;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dmiranda.caremetoo.listAdapters.EventHistoryAdapter;

import java.util.List;

import cicese.edu.caremetooAPI.Event;
import cicese.edu.cicese.edu.caremetoo.DAO.EventDAO;
import cicese.edu.cicese.edu.caremetoo.db.MySQLiteHelper;


public class EventHistoryActivity extends ListActivity {
    final static int CREATENEWEVENT = -1;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_history);

        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };
        mContext = getApplicationContext();
        EventDAO e = new EventDAO(mContext);
        List<Event> events =(List<Event>)(List<?>) e.getAll();
        setListAdapter(new EventHistoryAdapter(mContext,R.layout.events_history_item,events));
        registerForContextMenu(findViewById(R.id.event_list_container));


    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CREATENEWEVENT){
            EventDAO e = new EventDAO(mContext);
            List<Event> events =(List<Event>)(List<?>) e.getAll();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_history, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.action_event){
            startActivityForResult(new Intent(EventHistoryActivity.this,EventDetailsActivity.class),CREATENEWEVENT);
        }

        return super.onOptionsItemSelected(item);
    }
}
