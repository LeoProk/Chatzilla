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

package com.example.leonid.chatzilla.Parse;


import com.example.leonid.chatzilla.Interfaces.FactoryInterface;
import com.example.leonid.chatzilla.Utilities.UtilitiesFactory;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Gets all numbers in contacts list. And check if any number  much user number in parse datebase
 * and populates list view with them.
 */
final class ListViewCreator implements FactoryInterface {

    private List<String> mMatchingContacts;

    private Context mContext;

    private List<User> mAppUsers;

    private ListView mFriendList;

    private int numOfContacts;

    private ParseQuery mQuery;

    public ListViewCreator(Context context, ListView friendList) {
        mContext = context;
        mFriendList = friendList;
    }

    // get the phone number of all phone contacts
    @Override
    public Object doTask() {
        mAppUsers = new ArrayList<>();
        mMatchingContacts = new ArrayList<>();
        final ContentResolver cr = mContext.getContentResolver();
        final Cursor cursor = cr
                .query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
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
                        mMatchingContacts.add(contactNumber);
                        break;
                    }
                    pCur.close();
                }

            } while (cursor.moveToNext());
        }

        numOfContacts = 0;
        mQuery = ParseQuery.getQuery("Dude");
        if (mMatchingContacts.isEmpty()) {
            Toast.makeText(mContext, "Your contacts list is empty !",
                    Toast.LENGTH_LONG).show();
        } else {
            contactParseChecker(mMatchingContacts.get(numOfContacts));
        }

        return false;
    }

    final void queryNewUser() {
        if (numOfContacts != mMatchingContacts.size()) {
            contactParseChecker(mMatchingContacts.get(numOfContacts));
        } else {
            //Populate list view after getting all values
            ArrayAdapter<User> adapter = new ArrayAdapter<>(mContext,
                    android.R.layout.simple_list_item_1, mAppUsers);
            UtilitiesFactory.saveSQL(mContext, mAppUsers);
            mFriendList.setAdapter(adapter);
        }
    }

    final void contactParseChecker(String phoneNum) {

        numOfContacts++;
        mQuery.whereEqualTo("phone", phoneNum.replaceAll("[\\D]", ""));
        Log.e("Sleep", phoneNum.replaceAll("[\\D]", ""));
        mQuery.findInBackground(new FindCallback<ParseObject>() {
            // if there maching use in database crate new user
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject usersObject : objects) {
                        Log.e(usersObject.getString("name"), usersObject.getString("phone"));
                        mAppUsers.add(new User(usersObject.getString("name"),
                                usersObject.getString("phone")));


                    }
                    queryNewUser();
                } else {
                    queryNewUser();
                }

            }
        });


    }


}
