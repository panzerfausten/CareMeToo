package com.example.dmiranda.caremetoo;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class EventDetailsActivity extends Activity {
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
