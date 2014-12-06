package cicese.edu.cicese.edu.caremetoo.DAO;

import android.content.Context;

import java.util.List;

import cicese.edu.caremetooAPI.ApiObject;
import cicese.edu.caremetooAPI.Data;

/**
 * Created by dmiranda on 12/3/14.
 */
public interface CMDAOInterface {
    void save(ApiObject apiObject);
    void init(Context mContext);
    void delete(ApiObject apiObject);
    void get(ApiObject apiObject);
    List<ApiObject> getAll();

}
