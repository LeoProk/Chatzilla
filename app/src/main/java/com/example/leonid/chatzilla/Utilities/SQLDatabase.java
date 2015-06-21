/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.leonid.chatzilla.Utilities;

import com.example.leonid.chatzilla.Interfaces.FactoryInterface;
import com.example.leonid.chatzilla.Parse.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates new database and save the matching contracts there.
 */
final class SQLDatabase extends SQLiteOpenHelper implements FactoryInterface {

    private static final String TABLE_CONTACTS = "contacts";

    private static final String COLUMN_ID = "_id";

    private static final String DATABASE_NAME = "feeds.db";

    private static final String COLUMN_USER = "user";

    private static final int DATABASE_VERSION = 1;

    //List of contacts that much the parse database
    List<User> mUsers;

    private String mSaveRetrieveUpdate;

    public SQLDatabase(Context context, List<User> users, String command) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mSaveRetrieveUpdate = command;
        mUsers = users;
    }

    // Create users database from arraylist
    void addContacts() {
        //clean old database
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);

        ContentValues values = new ContentValues();
        for (int i = 0; i < mUsers.size(); i++) {
            values.put(COLUMN_USER, mUsers.get(i).toString());
            db.insert(TABLE_CONTACTS, null, values);

        }
        db.close(); // Closing database connection

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE "
                + TABLE_CONTACTS + "(" + COLUMN_ID
                + " INTEGER PRIMARY KEY," + COLUMN_USER
                + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    // Getting All Contacts
    public List<User> getAllContacts() {
        mUsers = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String userInfo[] = cursor.getString(1).split("\n");
                User contact = new User(userInfo[0], userInfo[1]);
                mUsers.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return mUsers;
    }

    @Override
    public Object doTask() {

        switch (mSaveRetrieveUpdate) {
            case "save":
                addContacts();
                break;
            case "retrieve":
                getAllContacts();
                break;
            default:
                break;

        }
        return mUsers;
    }
}
