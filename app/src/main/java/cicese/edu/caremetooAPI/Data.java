package cicese.edu.caremetooAPI;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dmiranda on 12/2/14.
 */
public class Data extends ApiObject implements  ApiInterface {
    int IDDATA;
    int IDCAREGIVER;

    public int getIDDATA() {
        return IDDATA;
    }

    public void setIDDATA(int IDDATA) {
        this.IDDATA = IDDATA;
    }

    public int getIDCAREGIVER() {
        return IDCAREGIVER;
    }

    public void setIDCAREGIVER(int IDCAREGIVER) {
        this.IDCAREGIVER = IDCAREGIVER;
    }

    public String getDATATYPE() {
        return DATATYPE;
    }

    public void setDATATYPE(String DATATYPE) {
        this.DATATYPE = DATATYPE;
    }

    public String getVALUE() {
        return VALUE;
    }

    public void setVALUE(String VALUE) {
        this.VALUE = VALUE;
    }

    public String getEXTRA() {
        return EXTRA;
    }

    public void setEXTRA(String EXTRA) {
        this.EXTRA = EXTRA;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getTIMESTAMP() {
        return TIMESTAMP;
    }

    public void setTIMESTAMP(String TIMESTAMP) {
        this.TIMESTAMP = TIMESTAMP;
    }

    String DATATYPE;
    String VALUE;
    String EXTRA;
    String LOCATION;
    String TIMESTAMP;


    public Data(){
        super();
    }
    public Data(int iddata, int idcaregiver, String dataType, String value, String extra, String location, String timestamp){
        super();
        this.IDDATA = iddata;
        this.IDCAREGIVER = idcaregiver;
        this.DATATYPE = dataType;
        this.VALUE = value;
        this.EXTRA = extra;
        this.LOCATION = location;
        this.TIMESTAMP = timestamp;
        ApiClient = new DefaultHttpClient();
    }
    @Override
    public ApiObject save(ApiObject apiObject) throws IOException, JSONException {
        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        Data dataObject = (Data) apiObject;
        HttpPost hpost = new HttpPost("http://158.97.91.58:5000/caregivers/1/data");

        postParameters.add(new BasicNameValuePair("datatype", dataObject.getDATATYPE()));
        postParameters.add(new BasicNameValuePair("value", dataObject.getVALUE()));
        postParameters.add(new BasicNameValuePair("extra", dataObject.getEXTRA()));
        postParameters.add(new BasicNameValuePair("location", dataObject.getLOCATION()));
        postParameters.add(new BasicNameValuePair("timestamp", dataObject.getTIMESTAMP()));
        hpost.setEntity(new UrlEncodedFormEntity(postParameters));
        HttpResponse response = ApiClient.execute(hpost);

        String sResponse = EntityUtils.toString(response.getEntity());

        JSONObject jo = new JSONObject(sResponse);
        Data d = new Data(jo.getInt("iddata"),jo.getInt("idcaregiver"),jo.getString("datatype"),jo.getString("value")
                ,jo.getString("extra"),jo.getString("location"),jo.getString("timestamp"));
        return d;

    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public ApiObject get() {

        return new Data();
    }

    @Override
    public ApiObject getById(int id) throws IOException, JSONException {
        HttpGet hget = new HttpGet("http://158.97.91.58:5000/caregivers/1/data/?".replace("?",String.valueOf(id)));
        HttpResponse response = ApiClient.execute(hget);

        String sResponse = EntityUtils.toString(response.getEntity());
        JSONObject jo = new JSONObject(sResponse);
        Data d = new Data(jo.getInt("iddata"),jo.getInt("idcaregiver"),jo.getString("datatype"),jo.getString("value")
            ,jo.getString("extra"),jo.getString("location"),jo.getString("timestamp"));
        return d;
    }
}
