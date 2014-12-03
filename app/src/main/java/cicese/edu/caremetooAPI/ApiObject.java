package cicese.edu.caremetooAPI;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by dmiranda on 12/2/14.
 */
public class ApiObject {
    HttpClient ApiClient;
    ApiObject(){
        this.ApiClient = new DefaultHttpClient();
    }
}
