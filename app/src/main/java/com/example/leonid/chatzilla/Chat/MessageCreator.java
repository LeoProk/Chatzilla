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

import com.example.leonid.chatzilla.AppController;
import com.example.leonid.chatzilla.Interfaces.FactoryInterface;
import com.example.leonid.chatzilla.Utilities.UtilitiesFactory;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Create new JSONObject message and saves it in parse.
 */
final class MessageCreator implements FactoryInterface {

    private EditText mMessage;

    private Context mContext;

    private TextView mChatText;

    MessageCreator(Context context, EditText message, TextView textView) {
        mContext = context;
        mMessage = message;
        mChatText = textView;
    }

    @Override
    public Object doTask() {
        mChatText.append("\n" + "Me:" + "\n" + mMessage.getText().toString().trim());
        try {
            JSONObject data = new JSONObject();
            data.put("action", "com.example.leonid.chatzilla.PUSH_NOTIFICATION");
            data.put("message", mMessage.getText().toString());
            data.put("phone", UtilitiesFactory.getFile(mContext, "phone").doTask());
            data.put("name", UtilitiesFactory.getFile(mContext, "name").doTask());
            ParseQuery<ParseInstallation> query = ParseInstallation.getQuery();
            query.whereEqualTo("phoneNum", AppController.mPhoneNum);
            // Send push notification to query
            ParsePush push = new ParsePush();
            push.setData(data);
            push.setQuery(query); // Set our Installation query
            push.sendInBackground();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
