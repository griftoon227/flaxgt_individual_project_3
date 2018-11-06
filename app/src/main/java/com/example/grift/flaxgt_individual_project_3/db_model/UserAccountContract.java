package com.example.grift.flaxgt_individual_project_3.db_model;

import android.provider.BaseColumns;

public class UserAccountContract {
    private UserAccountContract() {}

    public static class UserAccountEntry implements BaseColumns {
        public static final String COL_PARENT_FIRST_NAME = "parent_first_name";
        public static final String COL_LAST_NAME = "last_name";
        public static final String COL_EMAIL = "email";
        public static final String COL_PARENT_USERNAME = "parent_username";
        public static final String COL_PARENT_PASSWORD = "parent_password";
        public static final String COL_CHILD_FIRST_NAME = "child_first_name";
        public static final String COL_CHILD_USERNAME = "child_username";
        public static final String COL_CHILD_PASSWORD = "child_password";
    }


    public static final String TABLE_NAME = "user_account_table";


    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    UserAccountEntry.COL_PARENT_FIRST_NAME + " TEXT," +
                    UserAccountEntry.COL_LAST_NAME + " TEXT," +
                    UserAccountEntry.COL_EMAIL + " TEXT," +
                    UserAccountEntry.COL_PARENT_USERNAME + " TEXT," +
                    UserAccountEntry.COL_PARENT_PASSWORD + " TEXT," +
                    UserAccountEntry.COL_CHILD_FIRST_NAME + " TEXT," +
                    UserAccountEntry.COL_CHILD_USERNAME + " TEXT," +
                    UserAccountEntry.COL_CHILD_PASSWORD + " TEXT )"
            ;

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

}
