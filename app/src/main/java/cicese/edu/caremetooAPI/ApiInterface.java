package cicese.edu.caremetooAPI;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import cicese.edu.cicese.edu.caremetoo.db.MySQLiteHelper;

/**
 * Created by dmiranda on 12/2/14.
 */
public interface ApiInterface {
    static String SERVER_URL = "";
    ApiObject save(ApiObject apiObject) throws IOException, JSONException;
    boolean delete();
    ApiObject get();
    ApiObject getById(int id) throws IOException, JSONException;
    MySQLiteHelper dbHelper = null;
}
