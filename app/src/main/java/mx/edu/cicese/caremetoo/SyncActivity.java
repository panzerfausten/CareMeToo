package mx.edu.cicese.caremetoo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class SyncActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sync, menu);
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
    public void sync(View v){
        HttpClient hc = new DefaultHttpClient();
        HttpPost hpost = new HttpPost();
        List<BasicNameValuePair> params  = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("idcaregiver","1"));
        params.add(new BasicNameValuePair("datatype","HR"));
        params.add(new BasicNameValuePair("value","80"));
        params.add(new BasicNameValuePair("extra","Sent from android"));
        params.add(new BasicNameValuePair("location","11,11"));
        params.add(new BasicNameValuePair("timestamp","2014-11-13 16:34:01"));

        try {
            hpost.setEntity(new UrlEncodedFormEntity(params));
            try {
                HttpResponse result = hc.execute(hpost);

                String s = result.getEntity().toString();
                Log.i("RESULT",s);
            } catch (IOException e) {
                Toast.makeText(this,"Error sincronizando",Toast.LENGTH_SHORT).show();
            }

        } catch (UnsupportedEncodingException e) {
            Toast.makeText(this,"Error en bundle",Toast.LENGTH_SHORT).show();
        }

    }
}
