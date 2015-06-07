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

package com.example.leonid.chatzilla.Chat;

import com.example.leonid.chatzilla.Interfaces.FactoryInterface;
import com.example.leonid.chatzilla.Parse.ParseFactory;
import com.example.leonid.chatzilla.Utilities.UtilitiesFactory;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.ListView;

/**
 * class that checks for contacts size changes and if there any updates the sql for fitting number
 * with database
 */
final class ContactsSizeCheck implements FactoryInterface {

    private Context mContext;

    private ListView mFriendList;

    ContactsSizeCheck(Context context, ListView friendList) {
        mContext = context;
        mFriendList = friendList;
    }

    @Override
    public Object doTask() {
        if (((String) UtilitiesFactory.getFile(mContext, "friends").doTask()).length() == 0) {
            final ContentResolver cr = mContext.getContentResolver();
            Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            UtilitiesFactory.saveFile(mContext, "friends", Integer.toString(cursor.getCount()));
            ParseFactory.getUserList(mContext, null).doTask();
        } else {
            final ContentResolver cr = mContext.getContentResolver();
            Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

            if (Integer.parseInt((String) UtilitiesFactory.getFile(mContext, "friends").doTask())
                    != cursor.getCount()) {
                ParseFactory.getUserList(mContext, mFriendList).doTask();
            }
        }
        return null;

    }
}
