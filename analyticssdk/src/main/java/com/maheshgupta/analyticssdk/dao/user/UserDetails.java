package com.maheshgupta.analyticssdk.dao.user;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class UserDetails {
    private int auto_id;
    private String user_id;
    private String key;
    private String value;
    private String time_stamp;

    public UserDetails(String user_id, String key, String value, String time_stamp) {
        this.user_id = user_id;
        this.key = key;
        this.value = value;
        this.time_stamp = time_stamp;
    }

    public UserDetails(int auto_id, String user_id, String key, String value, String time_stamp) {
        this.auto_id = auto_id;
        this.user_id = user_id;
        this.key = key;
        this.value = value;
        this.time_stamp = time_stamp;
    }

    public int getAuto_id() {
        return auto_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "\nauto_id=" + auto_id +
                ",\n user_id='" + user_id + '\'' +
                ",\n key='" + key + '\'' +
                ",\n value='" + value + '\'' +
                ",\n time_stamp='" + time_stamp + '\'' +
                '}';
    }

    public static class UserDetailsRepo {
        public static final String TABLE_NAME = "user_details";
        public static final String AUTO_ID = "auto_id";
        public static final String USER_ID = "user_id";
        public static final String KEY = "key";
        public static final String VALUE = "value";
        public static final String TIME_STAMP = "time_stamp";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + AUTO_ID + " integer PRIMARY KEY autoincrement, " + USER_ID + " TEXT, "
                + KEY + " TEXT , " + VALUE + " TEXT, " + TIME_STAMP + " TEXT "
                + ")";

        public static ContentValues getContentValues(UserDetails userDetails) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_ID, userDetails.getUser_id());
            contentValues.put(KEY, userDetails.getKey());
            contentValues.put(VALUE, userDetails.getValue());
            contentValues.put(TIME_STAMP, userDetails.getValue());
            return contentValues;
        }

        public static List<UserDetails> getUserDetailsList(Cursor cursor) {
            if (cursor == null || cursor.getCount() <= 0)
                return null;

            List<UserDetails> userDetailsList = new ArrayList<>();
            while (cursor.moveToNext()) {
                userDetailsList.add(new UserDetails(
                        cursor.getInt(cursor.getColumnIndex(AUTO_ID)),
                        cursor.getString(cursor.getColumnIndex(USER_ID)),
                        cursor.getString(cursor.getColumnIndex(KEY)),
                        cursor.getString(cursor.getColumnIndex(VALUE)),
                        cursor.getString(cursor.getColumnIndex(TIME_STAMP))
                ));
            }
            cursor.close();
            return userDetailsList;
        }
    }
}
