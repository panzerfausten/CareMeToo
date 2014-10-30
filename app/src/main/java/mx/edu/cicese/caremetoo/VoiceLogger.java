package mx.edu.cicese.caremetoo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import mx.edu.cicese.caremetoo.config.CareMeTooConfig;


public class VoiceLogger extends Activity {
    private MediaRecorder mRecorder = null;
    private static String mFileName = null;
    private static String LOG_TAG = "VOICELOGGER";
    private boolean isRecording = false;
    private TextView RecordCaptionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_logger);
        RecordCaptionTextView = (TextView) findViewById(R.id.txt_record);
    }
    public void on_recordbutton_click(View view) {
        if(!isRecording){
            startRecording();
            ((Button) view).setText(R.string.record_voice_button_recording);
            RecordCaptionTextView.setText(R.string.record_voice_text_recording);
        }else{
            stopRecording();
            ((Button) view).setText(R.string.record_voice_button);
            RecordCaptionTextView.setText(R.string.record_voice_text_to_record);
        }
    }

    private String generateFileName(){
        String Filename = String.valueOf(System.currentTimeMillis());
        return CareMeTooConfig.externalPath+"/voicelog-"+Filename+ ".m4a";
    }
    private void startRecording(){
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mFileName = generateFileName();
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecorder.setOutputFile(mFileName);
        try{
            mRecorder.prepare();
            mRecorder.start();

        }catch (IOException e){
            Log.e(LOG_TAG, "Error preparing recorder");

        }
        isRecording = true;

    }
    private void stopRecording(){
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        isRecording = false;
        mFileName = null;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.voice_logger, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void on_listenrecords_click(View view) {
        Intent i = new Intent(VoiceLogger.this,VoicePlayer.class);
        startActivity(i);
    }
}
