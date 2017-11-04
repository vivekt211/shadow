package in.coderszone.shadow.dao;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Vivek on 10/29/2017.
 */

@Database(name = AppDatabase.NAME, version = 1)
public class AppDatabase {

    public static final String NAME = "shadowdb";
}