package com.example.dmiranda.caremetoo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import cicese.edu.caremetooAPI.ApiObject;
import cicese.edu.caremetooAPI.Data;
import cicese.edu.caremetooAPI.Event;


public class EventDetailsActivity extends Activity {
    Context mContext = null;
    final SeekBar SEEKBAR = null;
    final TextView SUDSTextView = null;
    final TextView SUDSValueTextView = null;
    ImageButton activity1ImageButton = null;
    ImageButton activity2ImageButton = null;
    ImageButton activity3ImageButton = null;
    ImageButton activity4ImageButton = null;
    ImageButton activity5ImageButton = null;
    ImageButton activity6ImageButton = null;

    List<ImageButton> activityButtons = new ArrayList<ImageButton>();
    ImageButton selectedActivity = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        final SeekBar SEEKBAR = (SeekBar) findViewById(R.id.suds_seekbar);
        final TextView SUDSTextView= (TextView) findViewById(R.id.SUDSTexview);
        final TextView SUDSValueTextView= (TextView) findViewById(R.id.SUDSTexviewValue);
        activity1ImageButton =  (ImageButton) findViewById(R.id.activity1);
        activity2ImageButton =  (ImageButton) findViewById(R.id.activity2);
        activity3ImageButton =  (ImageButton) findViewById(R.id.activity3);
        activity4ImageButton =  (ImageButton) findViewById(R.id.activity4);
        activity5ImageButton =  (ImageButton) findViewById(R.id.activity5);
        activity6ImageButton =  (ImageButton) findViewById(R.id.activity6);
        activityButtons.add(activity1ImageButton);
        activityButtons.add(activity2ImageButton);
        activityButtons.add(activity3ImageButton);
        activityButtons.add(activity4ImageButton);
        activityButtons.add(activity5ImageButton);
        activityButtons.add(activity6ImageButton);
        mContext = getApplicationContext();
        SEEKBAR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                Toast.makeText(getApplicationContext(),String.valueOf(progress),Toast.LENGTH_SHORT).show();
                  String SUDSLegendString = "";
                  String SUDSValueString = "";
                float[] hsvColor = {0, 1, 1};
                // generate only hue component in range [0, 360),
                // leaving saturation and brightness maximum possible



                  switch (  progress){
                      case 0:
                          SUDSLegendString = getString(R.string.suds_zero);
                          SUDSValueString = getString(R.string.suds_value_zero);
                          SUDSValueTextView.setTextColor(Color.GREEN);
                          break;
                      case 1:
                          SUDSLegendString = getString(R.string.suds_one);
                          SUDSValueString = getString(R.string.suds_value_one);
                          SUDSValueTextView.setTextColor(Color.GREEN);
                          break;
                      case 2:
                          SUDSLegendString = getString(R.string.suds_two);
                          SUDSValueString = getString(R.string.suds_value_two);
                          SUDSValueTextView.setTextColor(Color.GREEN);
                          break;
                      case 3:
                          SUDSLegendString = getString(R.string.suds_three);
                          SUDSValueString = getString(R.string.suds_value_three);
                          SUDSValueTextView.setTextColor(Color.YELLOW);
                          break;
                      case 4:
                          SUDSLegendString = getString(R.string.suds_four);
                          SUDSValueString = getString(R.string.suds_value_four);
                          SUDSValueTextView.setTextColor(Color.YELLOW);
                          break;
                      case 5:
                          SUDSLegendString = getString(R.string.suds_five);
                          SUDSValueString = getString(R.string.suds_value_five);
                          SUDSValueTextView.setTextColor(Color.parseColor("#ffa500"));
                          break;
                      case 6:
                          SUDSLegendString = getString(R.string.suds_six);
                          SUDSValueString = getString(R.string.suds_value_six);
                          SUDSValueTextView.setTextColor(Color.parseColor("#ffa500"));
                          break;
                      case 7:
                          SUDSLegendString = getString(R.string.suds_seven);
                          SUDSValueString = getString(R.string.suds_value_seven);
                          SUDSValueTextView.setTextColor(Color.parseColor("#ffa500"));
                          break;
                      case 8:
                          SUDSLegendString = getString(R.string.suds_eight);
                          SUDSValueString = getString(R.string.suds_value_eight);
                          SUDSValueTextView.setTextColor(Color.RED);
                          break;
                      case 9:
                          SUDSLegendString = getString(R.string.suds_nine);
                          SUDSValueString = getString(R.string.suds_value_nine);
                          SUDSValueTextView.setTextColor(Color.RED);
                          break;
                      case 10:
                          SUDSLegendString = getString(R.string.suds_ten);
                          SUDSValueString = getString(R.string.suds_value_ten);
                          SUDSValueTextView.setTextColor(Color.RED);
                          break;

                  }
                  SUDSTextView.setText(SUDSLegendString);
                SUDSValueTextView.setText(SUDSValueString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        setActivityclicks();
        initSeekBar();
        testService();
    }
    private void testService(){
        final android.os.Handler h = new android.os.Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                if (msg.obj != null) {
                    Event d = (Event) msg.obj;
                    Toast.makeText(mContext, d.getDESCRIPTION(), Toast.LENGTH_SHORT).show();
                }else{
                    switch (msg.what){
                        case -1:Toast.makeText(mContext,"IOERROR",Toast.LENGTH_LONG).show();
                        break;
                        case -2:Toast.makeText(mContext,"JSONERROR",Toast.LENGTH_LONG).show();
                        break;
                    }


                }
            }
        };
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Event ee = new Event();

                   // Data receivedData = (Data) d.getById(102050);
                    //Message msg = new Message();
                    //msg.obj = receivedData;
                    //h.sendMessage(msg);

                //Data dataToSave = new Data(1,1,"HR","100","Muy emocionado!!","31.842839, -116.600777","2014-12-01 18:30:00");
                Event eventToSave = new Event(1,1,-1,"Feeding","70","Estaba ayudando a papa cuando se puso de malas",
                        "2014-12-01 19:03:00");
                try {
                    Event receivedData = (Event) ee.save(eventToSave);
                    Message msg = new Message();
                    msg.obj = receivedData;
                    h.sendMessage(msg);
                } catch (IOException e) {
                    h.sendEmptyMessage(-1);

                } catch (JSONException e) {
                    h.sendEmptyMessage(-2);
                }

            }
        });
        t.start();

    }
    private void setActivityclicks(){

        for(final ImageButton i : activityButtons){
            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    v.setBackgroundColor(Color.parseColor("#0099cc"));

                    for(ImageButton ii : activityButtons){
                        if (i != ii){
                            ii.setBackgroundColor(Color.WHITE);
                            selectedActivity = i;
                        }
                    }
                }
            });
        }

    }
    private void initSeekBar(){
        /*String SUDSLegendString = getString(R.string.suds_zero);
        String SUDSValueString = getString(R.string.suds_value_zero);
        SUDSTextView.setText(SUDSLegendString);
        SUDSValueTextView.setText(SUDSValueString);
        SUDSValueTextView.setTextColor(Color.GREEN); */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_details, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }
}
