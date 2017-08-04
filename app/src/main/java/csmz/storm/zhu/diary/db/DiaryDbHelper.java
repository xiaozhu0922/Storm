package csmz.storm.zhu.diary.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 日记数据库 2017/8/4.
 */

public class DiaryDbHelper extends SQLiteOpenHelper {
    private Context mContext;


    private static final String CREATE_DIARY = "create table Diary("
            + "id integer primary key autoincrement, "
            + "date text, "
            + "title text, "
            + "tag text, "
            + "content text)";

    private static final String DEL_DIARY = "drop table if exists Diary";

    public DiaryDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DIARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DEL_DIARY);
        onCreate(sqLiteDatabase);
    }
}
