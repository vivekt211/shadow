package in.coderszone.shadow.dao;

import android.database.sqlite.SQLiteDatabase;

import in.coderszone.shadow.App;
import in.coderszone.shadow.di.AppComponent;

/**
 * Created by Vivek on 10/29/2017.
 */

public class BaseModel {

    protected final SQLiteDatabase mSQLiteDatabase;
    protected final AppComponent mComponent;

    public BaseModel(App app, SQLiteDatabase database) {
        mComponent = app.getAppComponent();
        mSQLiteDatabase = database;
    }
}
