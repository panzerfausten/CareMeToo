package cicese.edu.caremetooAPI;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dmiranda on 12/2/14.
 */
public class Event extends ApiObject implements  ApiInterface {
    int IDEVENT;

    public int getIDEVENT() {
        return IDEVENT;
    }

    public void setIDEVENT(int IDEVENT) {
        this.IDEVENT = IDEVENT;
    }

    public int getIDCAREGIVER() {
        return IDCAREGIVER;
    }

    public void setIDCAREGIVER(int IDCAREGIVER) {
        this.IDCAREGIVER = IDCAREGIVER;
    }

    public int getIDMULTIMEDIA() {
        return IDMULTIMEDIA;
    }

    public void setIDMULTIMEDIA(int IDMULTIMEDIA) {
        this.IDMULTIMEDIA = IDMULTIMEDIA;
    }

    public String getACTIVITY() {
        return ACTIVITY;
    }

    public void setACTIVITY(String ACTIVITY) {
        this.ACTIVITY = ACTIVITY;
    }

    public String getMOOD() {
        return MOOD;
    }

    public void setMOOD(String MOOD) {
        this.MOOD = MOOD;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getTIMESTAMP() {
        return TIMESTAMP;
    }

    public void setTIMESTAMP(String TIMESTAMP) {
        this.TIMESTAMP = TIMESTAMP;
    }

    int IDCAREGIVER;
    int IDMULTIMEDIA;
    String ACTIVITY;
    String MOOD;
    String DESCRIPTION;
    String TIMESTAMP;
    public Event(){

    }
    public Event(int idevent, int idcaregiver, int idmultimedia, String activity, String mood,
                 String description, String timestamp){
            this.IDEVENT = idevent;
            this.IDCAREGIVER = idcaregiver;
            this.IDMULTIMEDIA = idmultimedia;
            this.ACTIVITY = activity;
            this.MOOD = mood;
            this.DESCRIPTION = description;
            this.TIMESTAMP = timestamp;
    }
    @Override
    public ApiObject save(ApiObject apiObject) throws IOException, JSONException {
        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        Event eventToSave = (Event) apiObject;
        HttpPost hpost = new HttpPost("http://158.97.91.58:5000/caregivers/1/event");

        postParameters.add(new BasicNameValuePair("activity", eventToSave.getACTIVITY()));
        postParameters.add(new BasicNameValuePair("mood", eventToSave.getMOOD()));
        postParameters.add(new BasicNameValuePair("description", eventToSave.getDESCRIPTION()));
        postParameters.add(new BasicNameValuePair("mdescription", eventToSave.getDESCRIPTION()));
        postParameters.add(new BasicNameValuePair("timestamp", eventToSave.getTIMESTAMP()));
        hpost.setEntity(new UrlEncodedFormEntity(postParameters));
        HttpResponse response = ApiClient.execute(hpost);

        String sResponse = EntityUtils.toString(response.getEntity());

        JSONObject jo = new JSONObject(sResponse);
        Log.i("CAREMETOOAPI",sResponse);
        Event e = new Event(jo.getInt("idevent"),jo.getInt("idcaregiver"),-1,jo.getString("activity")
                ,jo.getString("mood"),jo.getString("description"),jo.getString("timestamp"));
        return e;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public ApiObject get() {
        return null;
    }

    @Override
    public ApiObject getById(int id) throws IOException, JSONException {
        return null;
    }
}
