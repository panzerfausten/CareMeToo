package cicese.edu.cicese.edu.caremetoo.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cicese.edu.caremetooAPI.ApiObject;
import cicese.edu.caremetooAPI.Data;
import cicese.edu.caremetooAPI.Event;
import cicese.edu.cicese.edu.caremetoo.db.MySQLiteHelper;

/**
 * Created by dmiranda on 12/4/14.
 */
public class EventDAO implements  CMDAOInterface{
    Context mContext;
    MySQLiteHelper dbHelper;

    public EventDAO(Context mContext){
        init(mContext);
    }
    @Override
    public void save(ApiObject apiObject) {
        Event d = (Event) apiObject;
        String[] values = new String[]{"1","", d.getACTIVITY(), d.getMOOD(),d.getDESCRIPTION(),
                d.getTIMESTAMP(),"0"};

        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
        //TODO: Caregiver ID
        String execString = "INSERT INTO event ( idcaregiver,idmultimedia,activity,mood,description,timestamp,synced)" +
                "VALUES (?,?,?,?,?,?,?)";
        writableDatabase.execSQL(execString,values);
        writableDatabase.close();
        dbHelper.close();
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
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
        List<ApiObject> events = new ArrayList<ApiObject>();
        Cursor cursor = readableDatabase.rawQuery("SELECT * FROM event ORDER by timestamp DESC", new String [] {});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            events.add(cursorToEvent(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        readableDatabase.close();
        dbHelper.close();


        return events;
    }
    private Event cursorToEvent(Cursor c){
       int idevent = c.getInt(0);
       int idcaregiver = c.getInt(1);
       int idmultimedia = c.getInt(2);
       String activity = c.getString(3);
       String mood = c.getString(4);
       String description = c.getString(5);
       String timestamp = c.getString(6);

       return new Event(idevent,idcaregiver,idmultimedia,activity,mood,description,timestamp);
    }

}
