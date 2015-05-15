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

package com.example.leonid.chatzilla;


import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 5/11/2015.
 */
public class GenerateUser {

    Context mContext;
    List<User>  mAppUsers;
    ParseQuery<ParseObject> mQuery;

    public GenerateUser(Context context) {
        mContext = context;
    }


    // get the phone number of all phone contacts
    public List<User> getContacts() {
        mQuery = ParseQuery.getQuery("Dude");
        mAppUsers = new ArrayList<>();
        ContentResolver cr = mContext.getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                if (Integer.parseInt(cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        //final contact number is added to array list
                        String contactNumber = pCur.getString(
                                pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contactParseChecker(contactNumber);
                        break;
                    }
                    pCur.close();
                }

            } while (cursor.moveToNext());
        }
        return  mAppUsers;
    }

    public void contactParseChecker(String phoneNum) {

        mQuery.whereEqualTo("phone", phoneNum.replaceAll("[\\D]", ""));
        mQuery.findInBackground(new FindCallback<ParseObject>() {
            // if there maching use in database crate new user
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {
                    for (ParseObject usersObject : objects) {
                        mAppUsers.add(new User(usersObject.getString("name"),
                                usersObject.getString("phone")));
                    }
                }

            }
        });

    }
}
