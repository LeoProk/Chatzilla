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

import com.example.leonid.chatzilla.Chat.ChatFactory;
import com.example.leonid.chatzilla.Fragments.ChatFragment;
import com.example.leonid.chatzilla.Utilities.UtilitiesFactory;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ScrollView;

/**
 * Receiver that receive json object from parse creating notification from it also update the chat
 * text in char fragment if it's the active view. Also save the message to internal storage.
 */
public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {

            JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
            //Creates notification from json info
            ChatFactory
                    .createNotification(context, json.getString("name"), json.getString("message"),
                            json.getString("phone")).doTask();
            //Put in info in the chat fragment if it's the current fragment;
            UtilitiesFactory.appendFile(context, AppController.mPhoneNum, "\n" + "  " + json.getString("name") + ":" + " " + json.getString(
                    "message") + "   "+"/@newline@/").doTask();
            if (ChatFragment.mThis != null) {
                ChatFragment.mThis.mCheckNewText = true;
                ChatFragment.mThis.messageFromReceiver(
                        "\n" + "  " + json.getString("name") + ":" + " " + json.getString(
                                "message") + "   ");
                (ChatFragment.mThis.getView().findViewById(R.id.chat_ScrollView)).post(
                        new Runnable() {

                            @Override
                            public void run() {
                                ((ScrollView) ChatFragment.mThis.getView()
                                        .findViewById(R.id.chat_ScrollView))
                                        .fullScroll(ScrollView.FOCUS_DOWN);
                            }
                        });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
