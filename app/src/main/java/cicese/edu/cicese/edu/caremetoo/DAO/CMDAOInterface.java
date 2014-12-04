package cicese.edu.cicese.edu.caremetoo.DAO;

import cicese.edu.caremetooAPI.ApiObject;

/**
 * Created by dmiranda on 12/3/14.
 */
public interface CMDAOInterface {
    void save(ApiObject apiObject);
    void delete(ApiObject apiObject);
    void get(ApiObject apiObject);
    void getAll(ApiObject apiObject);
}
