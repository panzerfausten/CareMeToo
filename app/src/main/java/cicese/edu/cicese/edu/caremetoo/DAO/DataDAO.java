package cicese.edu.cicese.edu.caremetoo.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import cicese.edu.caremetooAPI.ApiObject;
import cicese.edu.caremetooAPI.Data;
import cicese.edu.cicese.edu.caremetoo.db.MySQLiteHelper;

/**
 * Created by dmiranda on 12/3/14.
 */
public class DataDAO implements  CMDAOInterface{
    Context mContext;
    MySQLiteHelper dbHelper;
    @Override
    public void save(ApiObject apiObject) {
        Data d = (Data) apiObject;
        String[] values = new String[]{"1",d.getDATATYPE(),d.getVALUE(), d.getEXTRA(), d.getLOCATION(),
                                        d.getTIMESTAMP(),"0"};

        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
        //TODO: Caregiver ID
        String execString = "INSERT INTO data ( idcaregiver,datatype,value,extra,location,timestamp,synced)" +
                "VALUES (?,?,?,?,?,?,?)";
        writableDatabase.execSQL(execString,values);
        writableDatabase.close();
        dbHelper.close();


    }
    public DataDAO(Context mContext){
        init(mContext);
    }
    @Override
    public void init(Context mContext) {
        this.mContext = mContext;
        dbHelper = new MySQLiteHelper(mContext);
    }


    @Override
    public void delete(ApiObject apiObject) {

    }

    @Override
    public void get(ApiObject apiObject) {

    }

    @Override
    public List<ApiObject> getAll() {
        return null;
    }


}
