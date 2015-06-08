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
import com.example.leonid.chatzilla.Utilities.UtilitiesFactory;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;

import android.app.Application;
import android.util.Log;

/**
 * Application class, initialize parse with app info and run contacts check with parse database
 * on app first time user.
 */
public class AppController extends Application {

    // Friend phone number changes on user click in FriendList fragment
    public static String mPhoneNum;

    // Friend name  changes on user click in FriendList fragment
    public static String mFriendName;

    //
    public static int numForStart;

    // Height of text for correct text background placement in TextWithBackground
    public static int textHeight;

    // Length between messages for TextWithBackground
    public static int params;


    @Override
    public void onCreate() {
        //Initializing parse info with dev parse icon details
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "JH4PvyQBIfygc3ZpVPutfrrzXhZzBpg5aHyOXmUQ",
                "0WVeOSktZSQLf1deZQjH4bE7Z7Z8FVCYmSVQZZSs");
        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
        //On first time app run crate friend with contacts length and check it for matching parse
        //users
        if (((String) UtilitiesFactory.getFile(this, "friends").doTask()).length() == 0) {
            ChatFactory.checkNewContacts(this, null).doTask();

        }

    }

}
